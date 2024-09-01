package games.batlleship.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.batlleship.model.BattleshipBoard
import games.batlleship.model.BattleshipCell
import games.batlleship.model.BoardXCoordinates
import games.batlleship.model.BoardYCoordinates

@Composable
fun BattleshipBoardUI(
    board: BattleshipBoard,
    onCellClick: (BattleshipCell) -> Unit,
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
        BoardYCoordinates.forEach { y ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                BoardXCoordinates.forEach { x ->
                    board.value.find { it.position.x == x && it.position.y == y }?.run {
                        BattleshipCellUI(
                            title = id.toString(),
                            gameColor = gameColor,
                            type = type,
                            isAttacked = isAttacked,
                            isRevealed = isRevealed,
                            shipDirection = shipDirection,
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
