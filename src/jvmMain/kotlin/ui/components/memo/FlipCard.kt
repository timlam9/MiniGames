package ui.components.memo

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ui.model.Photo

@Composable
fun FlipCard(
    status: Photo.Status,
    isClickable: Boolean,
    photoCard: @Composable (textModifier: Modifier) -> Unit,
    bgCard: @Composable (textModifier: Modifier) -> Unit,
    onClick: () -> Unit,
) {
    val isRotated by remember(status) {
        mutableStateOf(
            status == Photo.Status.REVEALED || status == Photo.Status.MATCHED
        )
    }
    val rotation by animateFloatAsState(
        targetValue = if (isRotated) 180f else 0f,
        animationSpec = tween(500)
    )
    val animateFront by animateFloatAsState(
        targetValue = if (!isRotated) 1f else 0f,
        animationSpec = tween(500)
    )
    val animateBack by animateFloatAsState(
        targetValue = if (isRotated) 1f else 0f,
        animationSpec = tween(500)
    )

    Card(
        modifier = Modifier
            .size(200.dp)
            .fillMaxWidth()
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable(enabled = status != Photo.Status.MATCHED && isClickable && status != Photo.Status.REVEALED) {
                onClick()
            },
        shape = RoundedCornerShape(14.dp),
        elevation = 4.dp,
        contentColor = Color.White
    ) {
        if (isRotated) {
            photoCard(
                Modifier
                    .graphicsLayer {
                        alpha = animateBack
                        rotationY = 180f
                    }
            )
        } else {
            bgCard(
                Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        alpha = animateFront
                    }
            )
        }
    }
}
