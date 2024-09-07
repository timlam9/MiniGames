package games.hideAndChess.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.hideAndChess.components.HNCBoard
import games.hideAndChess.model.HideAndChessCell
import games.hideAndChess.model.HideAndChessState
import ui.design.DefaultButton
import ui.theme.*
import kotlin.reflect.KFunction0

@Composable
fun HideAndChessScreen(
    state: HideAndChessState,
    onCellClick: (HideAndChessCell) -> Unit,
    onCellLongClick: (HideAndChessCell) -> Unit,
    onRevealClick: () -> Unit,
    onRandomChessPieceRevealClick: () -> Unit,
    onRandomXMarkRevealClick: () -> Unit,
    onResetClick: () -> Unit,
    onChooseLevelClick: KFunction0<Unit>,
) {
    Row(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        HNCBoard(
            board = state.board,
            shouldReveal = state.shouldReveal,
            onCellClick = onCellClick,
            onCellLongClick = onCellLongClick,
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                text = if (state.shouldReveal) "Hide chess pieces" else "Reveal all chess pieces",
                onClick = onRevealClick,
                color = Purple,
                textColor = MaterialTheme.colors.primaryVariant,
            )
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                text = "Reveal random chess piece",
                onClick = onRandomChessPieceRevealClick,
                color = Orange,
                textColor = MaterialTheme.colors.primaryVariant,
            )
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                text = "Reveal random X mark",
                onClick = onRandomXMarkRevealClick,
                color = Blue,
                textColor = MaterialTheme.colors.primaryVariant,
            )
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                text = "Reset",
                onClick = onResetClick,
                color = Green,
                textColor = MaterialTheme.colors.primaryVariant,
            )
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Choose level",
                onClick = onChooseLevelClick,
                color = Red,
                textColor = MaterialTheme.colors.primaryVariant,
            )
        }
    }
}
