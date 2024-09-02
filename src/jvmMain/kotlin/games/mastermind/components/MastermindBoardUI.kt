package games.mastermind.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.mastermind.model.BoardXCoordinates
import games.mastermind.model.BoardYCoordinates
import games.mastermind.model.MastermindBoard
import games.mastermind.model.MastermindCell

@Composable
fun MastermindBoardUI(
    board: MastermindBoard,
    onCellClick: (MastermindCell) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxHeight()
            .aspectRatio(0.46f, matchHeightConstraintsFirst = true)
            .border(
                width = 5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))

    ) {
        BoardYCoordinates.forEach { y ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                BoardXCoordinates.forEach { x ->
                    board.value.find { it.position.x == x && it.position.y == y }
                        ?.run {
                            MastermindCellUI(
                                color = color,
                                onClick = { onCellClick(this) },
                                isRevealed = isRevealed,
                                isCode = position.y == 0,
                                type = type,
                                hints = if (type is MastermindCell.Type.HintsCell) type.hints else emptyList(),
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                            )
                        }
                }
            }
        }
    }
}
