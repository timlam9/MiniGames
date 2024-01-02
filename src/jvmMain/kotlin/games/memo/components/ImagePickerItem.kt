package games.memo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.loadImageBitmap
import java.io.File

@Composable
fun ImagePickerItem(onImagePicked: (image: String) -> Unit) {
    var image: File? by remember { mutableStateOf(null) }
    var isFilePickerDialogVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(160.dp)
            .clickable {
                image = null
                isFilePickerDialogVisible = true
            },
        contentAlignment = Alignment.Center,
    ) {

        if (image != null) {
            AsyncImage(
                load = { loadImageBitmap(image!!) },
                painterFor = { remember { BitmapPainter(it) } },
                contentDescription = "image picker item",
                modifier = Modifier.width(400.dp)
            )
        } else {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource("ic_add.png"),
                contentDescription = "image picker item placeholder",
                contentScale = ContentScale.Inside,
            )
        }

        if (isFilePickerDialogVisible) {
            FileChooserDialog("Choose image") {
                isFilePickerDialogVisible = false
                image = it
                onImagePicked(it.absolutePath)
            }
        }
    }
}
