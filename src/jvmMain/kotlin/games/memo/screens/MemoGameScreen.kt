package games.memo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import games.memo.components.BgPhotoCard
import games.memo.components.FlipCard
import games.memo.components.PhotoCard
import games.memo.model.Photo
import games.memo.model.MemoState

@Composable
fun MemoGameScreen(
    state: MemoState,
    onGameStart: () -> Unit,
    onCardClick: (id: String) -> Unit,
) {
    val gridState = rememberLazyGridState()

    LaunchedEffect(null) {
        onGameStart()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Adaptive(minSize = 200.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            itemsIndexed(
                items = state.photos.value,
                key = { _: Int, item: Photo -> item.id }
            ) { index, photo ->
                FlipCard(
                    status = photo.status,
                    isClickable = state.status == MemoState.Status.PLAYING,
                    photoCard = { modifier ->
                        PhotoCard(
                            photo = photo,
                            modifier = modifier,
                        )
                    },
                    bgCard = { textModifier ->
                        BgPhotoCard(
                            id = index + 1,
                            textModifier = textModifier,
                        )
                    }
                ) {
                    onCardClick(photo.id)
                }
            }
        }
    }
}
