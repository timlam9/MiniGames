package games.batlleship.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.batlleship.components.BattleshipBoardUI
import games.batlleship.model.BattleshipCell
import games.batlleship.model.BattleshipState
import ui.design.DefaultButton
import ui.theme.Blue
import ui.theme.Purple

@Composable
fun BattleshipScreen(
    state: BattleshipState,
    onCellClick: (BattleshipCell) -> Unit,
    onPlayAgainClick: () -> Unit,
    onRevealBoardClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalArrangement = Arrangement.Start,
    ) {
        if (state.board.value.isNotEmpty()) {
            BattleshipBoardUI(
                board = state.board,
                onCellClick = onCellClick,
                modifier = Modifier,
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .border(
                        width = 5.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Blue)
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight().padding(start = 10.dp, end = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultButton(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Play again",
                onClick = onPlayAgainClick,
                color = Purple,
                textColor = MaterialTheme.colors.primaryVariant
            )
            DefaultButton(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Reveal all ",
                onClick = onRevealBoardClick,
                color = Purple,
                textColor = MaterialTheme.colors.primaryVariant
            )
        }
    }
}
