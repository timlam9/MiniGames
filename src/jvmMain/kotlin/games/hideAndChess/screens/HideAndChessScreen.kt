package games.hideAndChess.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import games.hideAndChess.components.HNCBoard
import games.hideAndChess.model.HideAndChessCell
import games.hideAndChess.model.HideAndChessState

@Composable
fun HideAndChessScreen(
    state: HideAndChessState,
    onCellClick: (HideAndChessCell) -> Unit,
    onRevealClick: () -> Unit,
    onRandomChessPieceRevealClick: () -> Unit,
    onRandomXMarkRevealClick: () -> Unit,
    onResetClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        HNCBoard(
            board = state.board,
            shouldReveal = state.shouldReveal,
            onCellClick = onCellClick,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = onRevealClick) {
                Text(if (state.shouldReveal) "Hide" else "Reveal")
            }
            Button(onClick = onRandomChessPieceRevealClick) {
                Text("Random Chess piece reveal")
            }
            Button(onClick = onRandomXMarkRevealClick) {
                Text("Random X mark reveal")
            }
            Button(onClick = onResetClick) {
                Text("Reset")
            }
        }
    }
}
