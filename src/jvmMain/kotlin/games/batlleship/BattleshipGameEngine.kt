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
    private val initialState = BattleshipState(board = generateRandomBattleshipBoard())

    private val _state: MutableStateFlow<BattleshipState> = MutableStateFlow(initialState)
    val state: StateFlow<BattleshipState> = _state.asStateFlow()

    fun onCellClick(cell: BattleshipCell) {
        _state.update { currentState ->
            val newBoard = currentState.board.copy(
                value = currentState.board.value.map {
                    if (it.id == cell.id) {
                        it.copy(isAttacked = true)
                    } else {
                        it
                    }
                }
            )
            currentState.copy(board = newBoard.revealShipIfSunk(type = cell.type).revealBoardIfGameOver())
        }
    }

    private fun BattleshipBoard.revealShipIfSunk(type: BattleshipCell.Type): BattleshipBoard {
        val shouldRevealShip = value.filter { it.type.ship == type.ship }.all { it.isAttacked }

        return if (shouldRevealShip) {
            copy(
                value = value.map { cell ->
                    if (cell.type.ship == type.ship) {
                        cell.copy(
                            isRevealed = true,
                            isAttacked = false,
                        )
                    } else {
                        cell
                    }
                }
            )
        } else {
            this
        }
    }

    private fun BattleshipBoard.revealBoardIfGameOver(): BattleshipBoard {
        val shouldRevealBoard = value.filter { it.type != BattleshipCell.Type.SEA }.all { it.isRevealed }

        return if (shouldRevealBoard) {
            copy(
                value = value.map { cell ->
                    cell.copy(
                        isRevealed = true,
                        isAttacked = false,
                    )
                }
            )
        } else {
            this
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
                        it.copy(
                            isRevealed = true,
                            isAttacked = false,
                        )
                    }
                )
            )
        }
    }
}
