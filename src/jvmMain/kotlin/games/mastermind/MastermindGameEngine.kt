package games.mastermind

import GameEngine
import games.MiniGamesColor
import games.mastermind.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MastermindGameEngine : GameEngine {

    private val initialState = MastermindState(board = generateRandomMastermindBoard())

    private val _state: MutableStateFlow<MastermindState> = MutableStateFlow(initialState)
    val state: StateFlow<MastermindState> = _state.asStateFlow()

    fun onCellClick(cell: MastermindCell, color: MiniGamesColor) {
        _state.update { currentState ->
            val newBoard = currentState.board.copy(
                value = currentState.board.value.map { currentCell ->
                    if (cell.id == currentCell.id) {
                        currentCell.copy(
                            color = color,
                        )
                    } else {
                        currentCell
                    }
                }
            )
            val currentRow = newBoard.value
                .filter { it.position.y == cell.position.y }
                .filter { it.type is MastermindCell.Type.PlayerCell }

            if (currentRow.all { it.color != null }) {
                val rowCodeCells = currentRow.toCodeCells()
                val code = mastermindCode.toMutableList()
                val hints = rowCodeCells.getHints(code = code)

                if (rowCodeCells == code) {
                    currentState.copy(
                        board = newBoard.copy(
                            value = newBoard.value.map { currentCell ->
                                if (currentCell.position.y == cell.position.y && currentCell.position.x == 4) {
                                    currentCell.copy(
                                        type = MastermindCell.Type.HintsCell(hints = hints),
                                        isRevealed = true
                                    )
                                } else {
                                    currentCell.copy(isRevealed = true)
                                }
                            }
                        )
                    )
                } else {
                    currentState.copy(
                        board = newBoard.copy(
                            value = newBoard.value.map { currentCell ->
                                if (currentCell.position.y == cell.position.y && currentCell.position.x == 4) {
                                    currentCell.copy(
                                        type = MastermindCell.Type.HintsCell(hints = hints)
                                    )
                                } else {
                                    currentCell
                                }
                            }
                        )
                    )
                }
            } else {
                currentState.copy(board = newBoard)
            }
        }

        if (_state.value.board.value
                .filterNot { it.position.y == 0 }
                .filter { it.type == MastermindCell.Type.PlayerCell }
                .all { it.color != null }
        ) {
            onRevealBoardClick()
        }
    }

    fun onPlayAgainClick() {
        _state.update { currentState ->
            currentState.copy(
                board = generateRandomMastermindBoard()
            )
        }
    }

    fun onRevealBoardClick() {
        _state.update { currentState ->
            currentState.copy(
                board = currentState.board.copy(
                    value = currentState.board.value.map { currentCell ->
                        currentCell.copy(isRevealed = true)
                    }
                )
            )
        }
    }
}
