package games.findTheStar.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.findTheStar.model.BoardXCoordinates
import games.findTheStar.model.BoardYCoordinates
import games.findTheStar.model.FindTheStarBoard
import games.findTheStar.model.FindTheStarCell

@Composable
fun StarBoard(
    board: FindTheStarBoard,
    onCellClick: (FindTheStarCell) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .border(
                width = 5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))

    ) {
        BoardXCoordinates.forEach { x ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                BoardYCoordinates.forEach { y ->
                    board.value.find { it.position.x == x && it.position.y == y }?.run {
                        StarCell(
                            id = id.toString(),
                            color = color,
                            type = type,
                            isDiscovered = isDiscovered,
                            onClick = { onCellClick(this) },
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
