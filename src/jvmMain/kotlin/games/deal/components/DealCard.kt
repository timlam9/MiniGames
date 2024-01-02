package games.deal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DealCard(
    title: String,
    color: Color,
    isOpened: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .width(300.dp)
            .clickable(onClick = onClick),
        elevation = 4.dp,
        backgroundColor = if (isOpened) Color.DarkGray else color,
        border = BorderStroke(width = 2.dp, color = Color.White),
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                color = if (isOpened) Color.Gray else Color.White,
            )
        }
    }
}
