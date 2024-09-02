package games.kim.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import games.kim.model.KimItem

@Composable
fun KimItemUI(
    type: KimItem.Type,
    size: KimItem.Size,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(type.resource),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(8.dp).size(size = size.dps),
        )
    }
}
