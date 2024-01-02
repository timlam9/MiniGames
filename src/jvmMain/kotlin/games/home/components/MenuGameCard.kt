package games.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MenuGameCard(
    title: String,
    image: String,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    onInfoIconClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .size(300.dp)
            .clickable(onClick = onCardClick),
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth().height(240.dp),
                    painter = painterResource(image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = onInfoIconClick) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "",
                            tint = Color.DarkGray,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
            }
        }
    }
}
