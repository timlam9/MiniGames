package ui.components.memo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun BgPhotoCard(
    id: Int,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .size(200.dp)
            .padding(0.dp)
            .background(Color.Gray),
        elevation = 4.dp,
        contentColor = MaterialTheme.colors.primary,
    ) {
        Column(
            modifier = textModifier.padding(0.dp),
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
