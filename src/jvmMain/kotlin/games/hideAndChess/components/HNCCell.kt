package games.hideAndChess.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import games.hideAndChess.model.HideAndChessCell
import ui.theme.*

@Composable
fun HNCCell(
    color: HideAndChessCell.Color,
    type: HideAndChessCell.Type,
    mark: HideAndChessCell.Mark,
    isGameOver: Boolean = false,
    borders: Set<HideAndChessCell.Border>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = when (color) {
        HideAndChessCell.Color.PURPLE -> Purple
        HideAndChessCell.Color.ORANGE -> Orange
        HideAndChessCell.Color.BLUE -> Blue
        HideAndChessCell.Color.GREEN -> Green
        HideAndChessCell.Color.GRAY -> Gray
        HideAndChessCell.Color.PINK -> Pink
        HideAndChessCell.Color.CIEL -> Ciel
        HideAndChessCell.Color.RED -> Red
        HideAndChessCell.Color.BROWN -> Brown
        HideAndChessCell.Color.YELLOW -> Yellow
    }

    val typeIcon = when (type) {
        HideAndChessCell.Type.NONE -> null
        HideAndChessCell.Type.KING -> "hide/ic_black_king.png"
    }

    val markIcon = when (mark) {
        HideAndChessCell.Mark.NONE -> null
        HideAndChessCell.Mark.X -> "hide/ic_x.png"
        HideAndChessCell.Mark.TIC -> "hide/ic_tic.png"
    }

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black,
            )
            .fillMaxSize()
            .background(color = backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .drawBehind {
                val strokeWidthPx = density.run { 5.dp.toPx() }
                val width = size.width
                val height = size.height
                val borderColor = Color.Black


                if (borders.contains(HideAndChessCell.Border.NORTH)) {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = width, y = 0f),
                        strokeWidth = strokeWidthPx
                    )
                }

                if (borders.contains(HideAndChessCell.Border.WEST)) {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = 0f, y = height),
                        strokeWidth = strokeWidthPx
                    )
                }
            },
    ) {
        AnimatedVisibility(isGameOver) {
            typeIcon?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                )
            }
        }
        markIcon?.let {
            Image(
                painter = painterResource(it),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            )
        }
    }
}
