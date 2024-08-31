package games.findTheStar

import GameEngine
import games.findTheStar.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FindTheStarGameEngine : GameEngine {

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private val initialState = FindTheStarState(
        board = generateRandomFindTheStarBoard(level = Level.MEDIUM),
        level = Level.MEDIUM,
    )

    private val _state: MutableStateFlow<FindTheStarState> = MutableStateFlow(initialState)
    val state: StateFlow<FindTheStarState> = _state.asStateFlow()

    fun onLevelSelected(level: Level) {
        _state.update {
            it.copy(
                board = generateRandomFindTheStarBoard(level = level),
                level = level,
            )
        }
    }

    fun onCellClick(cell: FindTheStarCell) {
        _state.update { currentState ->
            currentState.copy(
                board = currentState.board.copy(
                    value = currentState.board.value.map {
                        if (it.id == cell.id) {
                            it.copy(
                                isDiscovered = true
                            )
                        } else {
                            it
                        }
                    }
                )
            )
        }

        if (cell.type == FindTheStarCell.Type.Planet.Sun) {
            onRevealBoardClick()
        }
    }

    fun onPlayAgainClick() {
        scope.launch {
            emptyBoard()
            delay(150)
            _state.update { currentState ->
                currentState.copy(board = generateRandomFindTheStarBoard(level = currentState.level))
            }
        }
    }

    private fun emptyBoard() {
        _state.update { currentState ->
            currentState.copy(
                board = FindTheStarBoard(emptyList())
            )
        }
    }

    fun onRevealBoardClick() {
        _state.update { currentState ->
            currentState.copy(
                board = currentState.board.copy(
                    value = currentState.board.value.map {
                        it.copy(isDiscovered = true)
                    }
                )
            )
        }
    }
}
