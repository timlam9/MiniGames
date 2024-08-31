package home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import home.components.MenuGameCard
import ui.navigation.GameScreen

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
        GamesGrid(
            games = games,
            onGameCardClick = onGameCardClick,
            onInfoIconClick = onInfoIconClick,
        )
    }
}


@Composable
private fun GamesGrid(
    games: List<GameScreen>,
    onGameCardClick: (game: GameScreen) -> Unit,
    onInfoIconClick: (game: GameScreen) -> Unit,
) {
    val state = rememberLazyGridState()

    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        state = state,
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(30.dp),
        content = {
            items(games) {
                MenuGameCard(
                    title = it.title,
                    image = it.image,
                    onCardClick = { onGameCardClick(it) },
                    onInfoIconClick = { onInfoIconClick(it) }
                )
            }
        }
    )
}
