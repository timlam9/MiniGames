package games.mastermind.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.MiniGamesColor
import games.mastermind.model.MastermindCell
import games.toComposeColor

@Composable
fun MastermindCellUI(
    color: MiniGamesColor?,
    isRevealed: Boolean,
    isCode: Boolean,
    type: MastermindCell.Type,
    hints: List<MastermindCell.Hint>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(width = 1.dp, color = Color.Black)
            .fillMaxSize()
            .background(color = if (type is MastermindCell.Type.PlayerCell) Color.White else Color.LightGray)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
                enabled = type is MastermindCell.Type.PlayerCell && !isCode
            ),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(type is MastermindCell.Type.PlayerCell) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(color = if (isRevealed) color?.toComposeColor() ?: Color.LightGray else Color.Black)
            )
        }

        AnimatedVisibility(hints.isNotEmpty()) {
            Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                Column(modifier = Modifier.weight(1f).fillMaxSize()) {
                    if (hints.isNotEmpty()) {
                        HintBox(hints[0])
                    }
                    if (hints.size >= 2) {
                        Spacer(Modifier.size(4.dp))
                        HintBox(hints[1])
                    } else {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(color = Color.Transparent)
                        )
                    }
                }
                Spacer(Modifier.size(4.dp))
                Column(modifier = Modifier.weight(1f).fillMaxSize()) {
                    if (hints.size >= 3) {
                        HintBox(hints[2])
                    }
                    if (hints.size >= 4) {
                        Spacer(Modifier.size(4.dp))
                        HintBox(hints[3])
                    } else {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(color = Color.Transparent)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.HintBox(hint: MastermindCell.Hint) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            .clip(CircleShape)
            .background(color = if (hint == MastermindCell.Hint.RIGHT_COLOR) Color.Black else Color.White)
    )
}
