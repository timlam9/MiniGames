package games.hideAndChess

import GameEngine
import games.hideAndChess.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HideAndChessGameEngine : GameEngine {

    private val _state: MutableStateFlow<HideAndChessState> = MutableStateFlow(
        HideAndChessState(board = HideAndChessBoard(value = decodeBoard(InitialEncodedBoard)))
    )
    val state: StateFlow<HideAndChessState> = _state.asStateFlow()

    fun onCellClick(cell: HideAndChessCell) {
        val newMark = when(cell.mark) {
            HideAndChessCell.Mark.NONE -> HideAndChessCell.Mark.X
            HideAndChessCell.Mark.X -> HideAndChessCell.Mark.TIC
            HideAndChessCell.Mark.TIC -> HideAndChessCell.Mark.NONE
        }

        _state.update { currentState ->
            currentState.copy(
                board = currentState.board.copy(
                    value = currentState.board.value.map { currentCell ->
                        if (cell == currentCell) {
                            currentCell.copy(mark = newMark)
                        } else {
                            currentCell
                        }
                    }
                )
            )
        }
    }
}