package games.hideAndChess.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.hideAndChess.model.BoardCoordinates
import games.hideAndChess.model.HideAndChessBoard
import games.hideAndChess.model.HideAndChessCell

@Composable
fun HNCBoard(
    board: HideAndChessBoard,
    onCellClick: (HideAndChessCell) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .aspectRatio(1f)
            .fillMaxSize()
            .border(
                width = 5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))

    ) {
        BoardCoordinates.forEach { x ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                BoardCoordinates.forEach { y ->
                    board.value.find { it.positionX == x && it.positionY == y }
                        ?.run {
                            HNCCell(
                                color = color,
                                type = type,
                                mark = mark,
                                onClick = { onCellClick(this) },
                                borders = getBorders(board),
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
