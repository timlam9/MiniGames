package games.batlleship

import GameEngine
import games.batlleship.model.BattleshipBoard
import games.batlleship.model.BattleshipCell
import games.batlleship.model.BattleshipState
import games.batlleship.model.generateRandomBattleshipBoard
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BattleshipGameEngine : GameEngine {

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private val initialState = BattleshipState(
        board = generateRandomBattleshipBoard(),
        isBoardRevealed = false,
    )

    private val _state: MutableStateFlow<BattleshipState> = MutableStateFlow(initialState)
    val state: StateFlow<BattleshipState> = _state.asStateFlow()

    fun onCellClick(cell: BattleshipCell) {
        _state.update { currentState ->
            currentState.copy(
                board = currentState.board.copy(
                    value = currentState.board.value.map {
                        if (it.id == cell.id) {
                            it.copy(isAttacked = true)
                        } else {
                            it
                        }
                    }
                )
            )
        }
    }

    fun onPlayAgainClick() {
        scope.launch {
            emptyBoard()
            delay(150)
            _state.update { currentState ->
                currentState.copy(board = generateRandomBattleshipBoard())
            }
        }
    }

    private fun emptyBoard() {
        _state.update { currentState ->
            currentState.copy(
                board = BattleshipBoard(value = emptyList())
            )
        }
    }

    fun onRevealBoardClick() {
        _state.update { currentState ->
            currentState.copy(
                board = currentState.board.copy(
                    value = currentState.board.value.map {
                        it.copy(isAttacked = true)
                    }
                )
            )
        }
    }
}
