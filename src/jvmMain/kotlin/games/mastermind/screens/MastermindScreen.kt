package games.mastermind.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.MiniGamesColor
import games.mastermind.components.MastermindBoardUI
import games.mastermind.model.MastermindCell
import games.mastermind.model.MastermindState
import games.mastermind.model.mastermindColors
import games.toComposeColor
import ui.design.DefaultButton
import ui.theme.Gray
import ui.theme.Purple

@Composable
fun MastermindScreen(
    state: MastermindState,
    onCellClick: (MastermindCell, MiniGamesColor) -> Unit,
    onPlayAgainClick: () -> Unit,
    onRevealBoardClick: () -> Unit,
) {
    var selectedColor by remember { mutableStateOf(MiniGamesColor.PURPLE) }

    Row(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalArrangement = Arrangement.Start,
    ) {
        if (state.board.value.isNotEmpty()) {
            MastermindBoardUI(
                board = state.board,
                onCellClick = { onCellClick(it, selectedColor) },
                modifier = Modifier,
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .aspectRatio(1f)
                    .fillMaxSize()
                    .border(
                        width = 5.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Gray)
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight().padding(start = 10.dp, end = 20.dp, top = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                repeat(mastermindColors.size) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .border(
                                if (selectedColor == mastermindColors[it]) 4.dp else 0.dp,
                                MaterialTheme.colors.primary,
                                shape = CircleShape
                            )
                            .clip(CircleShape)
                            .background(color = mastermindColors[it].toComposeColor())
                            .clickable(onClick = { selectedColor = mastermindColors[it] })
                            .padding(10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            DefaultButton(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Play again",
                onClick = onPlayAgainClick,
                color = Purple,
                textColor = MaterialTheme.colors.primaryVariant
            )
            DefaultButton(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Reveal code",
                onClick = onRevealBoardClick,
                color = Purple,
                textColor = MaterialTheme.colors.primaryVariant
            )
        }
    }
}
