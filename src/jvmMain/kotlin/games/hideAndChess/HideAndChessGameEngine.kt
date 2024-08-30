package games.hideAndChess

import GameEngine
import games.hideAndChess.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HideAndChessGameEngine : GameEngine {

    private val _state: MutableStateFlow<HideAndChessState> = MutableStateFlow(
        HideAndChessState(
            board = HideAndChessBoard(value = decodeBoard(InitialEncodedBoard)),
            shouldReveal = false,
        )
    )
    val state: StateFlow<HideAndChessState> = _state.asStateFlow()

    fun onCellClick(cell: HideAndChessCell) {
        val newMark = when (cell.mark) {
            HideAndChessCell.Mark.NONE -> HideAndChessCell.Mark.X
            HideAndChessCell.Mark.X -> HideAndChessCell.Mark.TIC
            HideAndChessCell.Mark.TIC -> HideAndChessCell.Mark.NONE
        }

        _state.update { currentState ->
            val newBoard = currentState.board.copy(
                value = currentState.board.value.map { currentCell ->
                    if (cell == currentCell) currentCell.copy(mark = newMark) else currentCell
                }
            )
            currentState.copy(
                board = newBoard,
                shouldReveal = newBoard.hasWon()
            )
        }
    }

    private fun HideAndChessBoard.hasWon() =
        value.filter { it.type == HideAndChessCell.Type.KING && it.mark == HideAndChessCell.Mark.TIC }.size == 10


    fun onRevealClick() {
        _state.update { currentState ->
            currentState.copy(
                shouldReveal = !currentState.shouldReveal
            )
        }
    }

    fun onRandomChessPieceRevealClick() {
        _state.update { currentState ->
            var reveal = true
            val newBoard = currentState.board.copy(
                value = currentState.board.value
                    .shuffled()
                    .map { currentCell ->
                        if (
                            reveal
                            && currentCell.type == HideAndChessCell.Type.KING
                            && currentCell.mark != HideAndChessCell.Mark.TIC
                            && !currentCell.isRevealed
                        ) {
                            reveal = false
                            currentCell.copy(isRevealed = true)
                        } else {
                            currentCell
                        }
                    }
            )
            currentState.copy(
                board = newBoard,
                shouldReveal = newBoard.hasWon()
            )
        }
    }

    fun onRandomXMarkRevealClick() {
        _state.update { currentState ->
            var reveal = true
            val newBoard = currentState.board.copy(
                value = currentState.board.value
                    .shuffled()
                    .map { currentCell ->
                        if (
                            reveal
                            && currentCell.type == HideAndChessCell.Type.NONE
                            && currentCell.mark != HideAndChessCell.Mark.X
                            && !currentCell.isRevealed
                        ) {
                            reveal = false
                            currentCell.copy(mark = HideAndChessCell.Mark.X)
                        } else {
                            currentCell
                        }
                    }
            )
            currentState.copy(
                board = newBoard,
                shouldReveal = newBoard.hasWon()
            )
        }
    }

    fun onResetClick() {
        _state.update { currentState ->
            currentState.copy(
                board = HideAndChessBoard(value = decodeBoard(InitialEncodedBoard)),
                shouldReveal = false,
            )
        }
    }
}
