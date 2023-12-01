package ui.components.memo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.model.Photo
import java.io.File

@Composable
fun PhotoCard(photo: Photo, modifier: Modifier) = with(photo) {
    Card(
        modifier = modifier.size(200.dp),
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                if (res == null) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(defaultImage),
                        contentDescription = id,
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    AsyncImage(
                        load = { loadImageBitmap(File(res)) },
                        painterFor = { remember { BitmapPainter(it) } },
                        contentDescription = id,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            if (photo.isMatched()) {
                Box(modifier = Modifier.fillMaxSize().background(color = Color.Black.copy(alpha = 0.6f))) {
                    Text(
                        text = "Matched",
                        modifier = Modifier.rotate(-22.5f).align(Alignment.Center),
                        color = Color.White,
                        fontSize = 24.sp,
                    )
                }
            }
        }
    }
}
