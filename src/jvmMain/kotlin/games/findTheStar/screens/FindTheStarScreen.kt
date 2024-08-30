package games.findTheStar.screens

import androidx.compose.animation.AnimatedVisibility
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
import games.findTheStar.components.StarBoard
import games.findTheStar.model.FindTheStarCell
import games.findTheStar.model.FindTheStarState
import ui.design.DefaultButton
import ui.theme.Purple

@Composable
fun FindTheStarScreen(
    state: FindTheStarState,
    onCellClick: (FindTheStarCell) -> Unit,
    onPlayAgainClick: () -> Unit,
    onRevealBoardClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalArrangement = Arrangement.Start,
    ) {
        if (state.board.value.isNotEmpty()) {
            StarBoard(
                board = state.board,
                onCellClick = onCellClick,
                modifier = Modifier.weight(1f),
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
                    .background(Purple)
                    .weight(1f)
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
