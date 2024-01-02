package games.memo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import games.memo.components.ImagePickerItem

@Composable
fun MemoGameImagePickerScreen(
    onOkClick: () -> Unit,
    onImagePicked: (image: String, index: Int) -> Unit,
) {
    val gridState = rememberLazyGridState()
    val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Adaptive(minSize = 200.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(items = list, key = { it }) { index ->
                ImagePickerItem { image ->
                    onImagePicked(image, index)
                }
            }
        }
        Button(onClick = onOkClick) {
            Text("OK")
        }
    }
}
