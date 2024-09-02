package games.kim

import GameEngine
import androidx.compose.ui.unit.DpSize
import games.kim.model.KimItem
import games.kim.model.KimState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KimGameEngine(private val windowSize: DpSize) : GameEngine {

    private val initialState = KimState(
        items = generateItems(),
        shouldShowItems = true,
    )

    private fun generateItems(size: Int = 10): List<KimItem> = KimItem.Type.values()
        .toList()
        .shuffled()
        .take(size)
        .mapIndexed { index, type ->
            KimItem(
                id = index,
                type = type,
                size = KimItem.Size.values().random(),
                position = KimItem.Position(0f, 0f)
            )
        }

    private val _state: MutableStateFlow<KimState> = MutableStateFlow(initialState)
    val state: StateFlow<KimState> = _state.asStateFlow()

    init {
        val positions = generatePositions()

        _state.update { currentState ->
            currentState.copy(
                items = currentState.items.mapIndexed { index, kimItem ->
                    kimItem.copy(
                        position = positions[index]
                    )
                }
            )

        }
    }

    private fun generatePositions(size: Int = 10): List<KimItem.Position> {
        val positions = mutableListOf<KimItem.Position>()

        repeat(size) {
            fun getRandomPosition(): KimItem.Position {
                val randomX = (0..windowSize.width.value.toLong()).random().toFloat()
                val randomY = (0..windowSize.height.value.toLong()).random().toFloat()
                return KimItem.Position(x = randomX, y = randomY)
            }

            var newPosition = getRandomPosition()

            while (
                !newPosition.isInScreenBoundaries(windowSize = windowSize)
                && positions.any { it.isNotCollideWith(newPosition) }
            ) {
                newPosition = getRandomPosition()
            }

            positions.add(newPosition)
        }
        return positions.toList()
    }

    fun onRevealBoardClick() {
        _state.update { it.copy(shouldShowItems = !it.shouldShowItems) }
    }

    fun onPlayAgainClick() {
        val newPositions = generatePositions()

        _state.update { currentState ->
            currentState.copy(
                items = generateItems().mapIndexed { index, kimItem ->
                    kimItem.copy(position = newPositions[index])
                }
            )

        }
    }
}
