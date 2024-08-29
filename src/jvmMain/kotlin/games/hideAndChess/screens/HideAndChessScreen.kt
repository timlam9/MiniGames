package games.hideAndChess.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import games.hideAndChess.components.HNCBoard
import games.hideAndChess.model.HideAndChessCell
import games.hideAndChess.model.HideAndChessState

@Composable
fun HideAndChessScreen(state: HideAndChessState, onCellClick: (HideAndChessCell) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        HNCBoard(
            board = state.board,
            onCellClick = onCellClick,
        )
    }
}
