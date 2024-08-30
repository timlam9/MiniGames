package games.memo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import games.hideAndChess.model.HideAndChessCell
import ui.theme.*

@Composable
fun BgPhotoCard(
    id: Int,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.size(200.dp),
        elevation = 4.dp,
        contentColor = MaterialTheme.colors.primary,
    ) {
        val randomColor by remember { mutableStateOf(HideAndChessCell.Color.values().random()) }
        val backgroundColor = when (randomColor) {
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

        Column(
            modifier = textModifier.padding(0.dp).background(backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "MEMO CARD \n\n$id",
                textAlign = TextAlign.Center,
            )
        }
    }
}
