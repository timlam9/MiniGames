package games.kim.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import games.kim.components.KimItemUI
import games.kim.model.KimState
import ui.design.DefaultButton

@Composable
fun KimGameScreen(
    state: KimState,
    onPlayAgainClick: () -> Unit,
    onRevealBoardClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
    ) {
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
        Column(modifier = Modifier.align(alignment = Alignment.BottomEnd)) {
            DefaultButton(text = if (state.shouldShowItems) "Hide" else "Show", onClick = onRevealBoardClick)
            DefaultButton(text = "Play again", onClick = onPlayAgainClick)
        }
    }
}

@Composable
fun Dp.dpToPx(): Float = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun Int.pxToDp(): Dp = with(LocalDensity.current) { this@pxToDp.toDp() }
