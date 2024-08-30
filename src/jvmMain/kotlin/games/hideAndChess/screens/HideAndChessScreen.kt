package games.hideAndChess.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.hideAndChess.components.HNCBoard
import games.hideAndChess.model.HideAndChessCell
import games.hideAndChess.model.HideAndChessState
import ui.design.DefaultButton

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
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                text = if (state.shouldReveal) "Hide" else "Reveal",
                onClick = onRevealClick,
            )
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                text = "Random Chess piece reveal",
                onClick = onRandomChessPieceRevealClick,
            )
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                text = "Random X mark reveal",
                onClick = onRandomXMarkRevealClick,
            )
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Reset",
                onClick = onResetClick,
            )
        }
    }
}
