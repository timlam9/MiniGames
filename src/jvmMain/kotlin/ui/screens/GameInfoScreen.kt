package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource


@Composable
fun GameInfoScreen(
    title: String,
    content: String,
    image: String,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),

        ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.DarkGray).padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                style = MaterialTheme.typography.h3,
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(34.dp)
                )
            }
        }
        Text(
            text = content,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(20.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.fillMaxWidth().height(240.dp),
            painter = painterResource(image),
            contentDescription = "",
            contentScale = ContentScale.Fit,
        )
        Spacer(modifier = Modifier.weight(1f))

    }
}
