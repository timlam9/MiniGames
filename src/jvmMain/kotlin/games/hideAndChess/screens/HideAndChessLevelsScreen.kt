package games.hideAndChess.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import games.getRandomColor
import games.hideAndChess.model.Level
import ui.design.DefaultText

@Composable
fun HideAndChessLevelsScreen(
    levels: List<Level>,
    onLevelClick: (Level) -> Unit,
) {
    LazyColumn {
        item {
            DefaultText(
                text = "Levels",
                fontSize = 50.sp,
                modifier = Modifier.padding(20.dp),
            )
        }
        items(
            items = levels,
            key = { it.id },
        ) { level ->
            Box(
                modifier = Modifier
                    .clickable(onClick = { onLevelClick(level) })
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(8.dp)
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colors.primaryVariant,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = getRandomColor()),
                contentAlignment = Alignment.Center,
            ) {
                DefaultText(text = "Level: ${level.id}")
            }
        }
    }
}
