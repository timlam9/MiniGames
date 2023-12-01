package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.components.home.MenuGameCard

@Composable
fun MenuScreen(
    games: List<GameScreen>,
    onGameCardClick: (game: GameScreen) -> Unit,
    onInfoIconClick: (game: GameScreen) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Legit Games",
            modifier = Modifier.padding(40.dp),
            style = MaterialTheme.typography.h2,
            color = Color.DarkGray,
        )
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 20.dp),
            contentPadding = PaddingValues(horizontal = 100.dp)
        ) {
            items(
                items = games,
                key = { it.ordinal },
            ) {
                MenuGameCard(
                    title = it.title,
                    image = it.image,
                    onCardClick = { onGameCardClick(it) },
                    onInfoIconClick = { onInfoIconClick(it) }
                )
            }
        }
    }
}
