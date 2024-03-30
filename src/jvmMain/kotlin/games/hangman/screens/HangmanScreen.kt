package games.hangman.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import games.hangman.components.PasswordTextField
import games.hangman.model.HangmanState
import games.hangman.model.Letter

@Composable
fun HangmanScreen(
    state: HangmanState,
    onAction: (Letter) -> Unit,
    onPlayAgainClick: (String) -> Unit,
) {
    BoxWithConstraints {
        Row(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
            Hangman(
                wrongTries = state.wrongTries,
                imageHeight = this@BoxWithConstraints.maxHeight / 9,
            )
            AnimatedVisibility(state.isGameOver) {
                GameOverScreen(
                    hiddenWord = state.previousHiddenWord.toHangmanWord(),
                    modifier = Modifier.fillMaxSize().weight(3f),
                    onPlayAgainClick = onPlayAgainClick,
                )
            }
            AnimatedVisibility(!state.isGameOver) {
                Column(modifier = Modifier.weight(3f)) {
                    HangmanWord(text = state.hiddenWord.toHangmanWord(), modifier = Modifier.weight(2f))
                    HangmanKeyboard(state.keyLetters, onAction)
                }
            }
        }
    }
}

@Composable
private fun GameOverScreen(
    modifier: Modifier = Modifier,
    hiddenWord: String,
    onPlayAgainClick: (String) -> Unit,
) {
    var hiddenWordString by rememberSaveable { mutableStateOf("") }

    val hiddenWordText = buildAnnotatedString {
        append("Hidden word: ")
        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
            append(hiddenWord)
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Game Over", style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = hiddenWordText, style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(60.dp))
        PasswordTextField(text = hiddenWordString) { hiddenWordString = it }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { onPlayAgainClick(hiddenWordString) }) {
            Text(text = "Play again")
        }
    }
}

@Composable
private fun ColumnScope.HangmanKeyboard(keyLetters: List<Letter>, onAction: (Letter) -> Unit) {
    Column(
        modifier = Modifier.Companion.weight(1f).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        val rowLetters = keyLetters.chunked(12)

        rowLetters.forEach { row ->
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                row.forEach {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .clickable(enabled = it.state == Letter.State.UNSELECTED) {
                                onAction(it)
                            },
                        backgroundColor = if (it.state == Letter.State.UNSELECTED) Color.White else Color.Black,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                modifier = Modifier,
                                text = it.char.toString(),
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HangmanWord(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier,
            text = text,
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun Hangman(
    wrongTries: Int,
    imageHeight: Dp,
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
        AnimatedVisibility(wrongTries >= 1) {
            HangmanImage(
                imageRes = "head.png",
                imageHeight = imageHeight,
                visible = true,
                yOffset = imageHeight / 2,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight * 2),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            AnimatedVisibility(wrongTries >= 3) {
                HangmanImage(
                    imageRes = "right_arm.png",
                    imageHeight = imageHeight * 2,
                    imageWidth = imageHeight,
                    visible = true,
                    xOffset = imageHeight / 2 - 10.dp,
                    yOffset = imageHeight / 2 - 10.dp,
                )
            }
            AnimatedVisibility(wrongTries >= 2) {
                HangmanImage(
                    imageRes = "body.png",
                    imageHeight = imageHeight * 2,
                    imageWidth = imageHeight * 2,
                    visible = true,
                    yOffset = imageHeight / 2,
                )
            }
            AnimatedVisibility(wrongTries >= 4) {
                HangmanImage(
                    imageRes = "left_arm.png",
                    imageHeight = imageHeight * 2,
                    imageWidth = imageHeight,
                    visible = true,
                    xOffset = -imageHeight / 2 + 10.dp,
                    yOffset = imageHeight / 2 - 10.dp,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight * 2),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            AnimatedVisibility(wrongTries >= 5) {
                HangmanImage(
                    imageRes = "right_leg.png",
                    imageHeight = imageHeight * 2,
                    visible = true,
                    xOffset = imageHeight / 2,
                    yOffset = imageHeight / 2 + 10.dp,
                )
            }
            AnimatedVisibility(wrongTries >= 6) {
                HangmanImage(
                    imageRes = "right_leg.png",
                    imageHeight = imageHeight * 2,
                    visible = true,
                    xOffset = -imageHeight / 2,
                    yOffset = imageHeight / 2 + 10.dp,
                )
            }
        }
        AnimatedVisibility(wrongTries >= 8) {
            HangmanImage(
                imageRes = "fire.png",
                imageHeight = imageHeight * 2,
                imageWidth = imageHeight * 2,
                visible = true,
                yOffset = imageHeight / 2,
                contentScale = ContentScale.Crop,
            )
        }
        AnimatedVisibility(wrongTries >= 7) {
            HangmanImage(
                imageRes = "firewood.png",
                imageHeight = imageHeight,
                imageWidth = imageHeight * 2,
                visible = true,
                yOffset = imageHeight,
            )
        }
    }
}

@Composable
private fun HangmanImage(
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