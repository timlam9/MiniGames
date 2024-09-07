package games.memo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import games.getRandomColor
import ui.design.DefaultText

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
        val randomColor by remember { mutableStateOf(getRandomColor()) }

        Column(
            modifier = textModifier.padding(0.dp).background(randomColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultText(text = "MEMO CARD \n\n$id")
        }
    }
}
