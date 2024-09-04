package games.kim.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import games.kim.components.KimItemUI
import games.kim.model.KimItem
import games.kim.model.KimState
import ui.design.DefaultButton
import ui.theme.Blue
import ui.theme.Purple
import ui.theme.Red

@Composable
fun KimGameScreen(
    state: KimState,
    onPlayAgainClick: () -> Unit,
    onRevealBoardClick: () -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
    ) {

        fun getRandomPosition(): KimItem.Position {
            val randomX = (0..(maxWidth - 100.dp).value.toInt()).random().toFloat()
            val randomY = (0..(maxHeight - 100.dp).value.toInt()).random().toFloat()

            return KimItem.Position(x = randomX, y = randomY)
        }

        AnimatedVisibility(state.shouldShowItems) {
            state.items.forEach {
                println("Position: ${it.position}")
                KimItemUI(
                    type = it.type,
                    size = it.size,
                    modifier = Modifier.offset(x = it.position.x.dp, y = it.position.y.dp)
                )
            }
        }
        DefaultButton(
            text = if (state.shouldShowItems) "Hide" else "Show",
            color = Blue,
            textColor = MaterialTheme.colors.primary,
            onClick = onRevealBoardClick,
            modifier = Modifier.offset(x = getRandomPosition().x.dp, y = getRandomPosition().y.dp),
        )
        DefaultButton(
            text = "Play again",
            color = Red,
            textColor = MaterialTheme.colors.primary,
            onClick = onPlayAgainClick,
            modifier = Modifier.offset(x = getRandomPosition().x.dp, y = getRandomPosition().y.dp),
        )
    }
}

@Composable
fun Dp.dpToPx(): Float = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun Int.pxToDp(): Dp = with(LocalDensity.current) { this@pxToDp.toDp() }
