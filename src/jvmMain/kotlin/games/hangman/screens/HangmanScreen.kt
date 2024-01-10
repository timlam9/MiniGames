package games.hangman.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import games.hangman.model.HangmanState
import games.hangman.model.Letter

@Composable
fun HangmanScreen(
    state: HangmanState,
    onAction: (Letter) -> Unit,
) {
    BoxWithConstraints {
        val imageHeight = maxHeight / 9
        Row(
            modifier = Modifier.fillMaxSize().background(Color.LightGray)
        ) {
            Column(
                modifier = Modifier
                    .width(imageHeight * 4)
                    .background(Color.Gray)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                HangmanImage(
                    imageRes = "noose.png",
                    imageHeight = imageHeight,
                    visible = true,
                )
                HangmanImage(
                    imageRes = "head.png",
                    imageHeight = imageHeight,
                    visible = true,
                    yOffset = imageHeight / 2,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight * 2),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    HangmanImage(
                        imageRes = "right_arm.png",
                        imageHeight = imageHeight * 2,
                        imageWidth = imageHeight,
                        visible = true,
                        xOffset = imageHeight / 2 - 10.dp,
                        yOffset = imageHeight / 2 - 10.dp,
                    )
                    HangmanImage(
                        imageRes = "body.png",
                        imageHeight = imageHeight * 2,
                        imageWidth = imageHeight * 2,
                        visible = true,
                        yOffset = imageHeight / 2,
                    )
                    HangmanImage(
                        imageRes = "left_arm.png",
                        imageHeight = imageHeight * 2,
                        imageWidth = imageHeight,
                        visible = true,
                        xOffset = -imageHeight / 2 + 10.dp,
                        yOffset = imageHeight / 2 - 10.dp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight * 2),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    HangmanImage(
                        imageRes = "right_leg.png",
                        imageHeight = imageHeight * 2,
                        visible = true,
                        xOffset = imageHeight / 2,
                        yOffset = imageHeight / 2 + 10.dp,
                    )
                    HangmanImage(
                        imageRes = "right_leg.png",
                        imageHeight = imageHeight * 2,
                        visible = true,
                        xOffset = -imageHeight / 2,
                        yOffset = imageHeight / 2 + 10.dp,
                    )
                }
                HangmanImage(
                    imageRes = "fire.png",
                    imageHeight = imageHeight * 2,
                    imageWidth = imageHeight * 2,
                    visible = true,
                    yOffset = imageHeight / 2,
                    contentScale = ContentScale.Crop,
                )
                HangmanImage(
                    imageRes = "firewood.png",
                    imageHeight = imageHeight,
                    imageWidth = imageHeight * 2,
                    visible = true,
                    yOffset = imageHeight,
                )
            }
            Column(modifier = Modifier.weight(3f).background(Color.Red)) {

            }
        }
    }
}

@Composable
fun HangmanImage(
    imageRes: String,
    imageHeight: Dp,
    imageWidth: Dp = imageHeight,
    visible: Boolean = false,
    xOffset: Dp = 0.dp,
    yOffset: Dp = 0.dp,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AnimatedVisibility(visible) {
        Image(
            modifier = Modifier.height(imageHeight).width(imageWidth).offset(x = xOffset, y = -yOffset),
            painter = painterResource(imageRes),
            contentDescription = "",
            contentScale = contentScale,
        )
    }
}