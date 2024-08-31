package games.findTheStar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import games.findTheStar.components.StarBoard
import games.findTheStar.model.FindTheStarCell
import games.findTheStar.model.FindTheStarState
import games.findTheStar.model.Level
import ui.design.DefaultButton
import ui.design.DefaultText
import ui.theme.Purple

@Composable
fun FindTheStarScreen(
    state: FindTheStarState,
    onCellClick: (FindTheStarCell) -> Unit,
    onLevelSelected: (Level) -> Unit,
    onPlayAgainClick: () -> Unit,
    onRevealBoardClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalArrangement = Arrangement.Start,
    ) {
        if (state.board.value.isNotEmpty()) {
            StarBoard(
                board = state.board,
                onCellClick = onCellClick,
                modifier = Modifier.weight(1f),
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .border(
                        width = 5.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Purple)
                    .weight(1f)
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight().padding(start = 10.dp, end = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LevelSlider(
                level = state.level,
                modifier = Modifier.padding(bottom = 20.dp),
                onLevelSelected = onLevelSelected,
            )
            DefaultButton(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Play again",
                onClick = onPlayAgainClick,
                color = Purple,
                textColor = MaterialTheme.colors.primaryVariant
            )
            DefaultButton(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Reveal all ",
                onClick = onRevealBoardClick,
                color = Purple,
                textColor = MaterialTheme.colors.primaryVariant
            )
        }
    }
}


@Composable
fun LevelSlider(
    level: Level,
    onLevelSelected: (Level) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedLevel by rememberSaveable(level) { mutableStateOf(level) }
    var sliderPosition by rememberSaveable(level) {
        mutableStateOf(
            when (level) {
                Level.TOO_EASY -> 0f
                Level.EASY -> 1f
                Level.MEDIUM -> 2f
                Level.HARD -> 3f
                Level.TOO_HARD -> 4f
            }
        )
    }

    Column(
        modifier = modifier.width(200.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DefaultText(
            text = "Level: ${selectedLevel.name}",
            textColor = MaterialTheme.colors.secondaryVariant,
        )
        Spacer(Modifier.height(10.dp))
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
                selectedLevel = when (it) {
                    0f -> Level.TOO_EASY
                    1f -> Level.EASY
                    2f -> Level.MEDIUM
                    3f -> Level.HARD
                    4f -> Level.TOO_HARD
                    else -> Level.MEDIUM
                }

                onLevelSelected(selectedLevel)
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTrackColor = MaterialTheme.colors.secondary,
                inactiveTrackColor = MaterialTheme.colors.secondaryVariant,
            ),
            steps = 3,
            valueRange = 0f..4f
        )
    }
}
