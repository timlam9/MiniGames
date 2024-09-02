package games.findTheStar.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import games.MiniGamesColor
import games.findTheStar.model.FindTheStarCell
import games.toComposeColor
import ui.design.DefaultText

@Composable
fun StarCell(
    id: String,
    color: MiniGamesColor,
    type: FindTheStarCell.Type,
    isDiscovered: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val typeIcon = when (type) {
        FindTheStarCell.Type.EmptySpace -> null
        FindTheStarCell.Type.Rocket.Down -> "hide/ic_rocket_down.png"
        FindTheStarCell.Type.Rocket.Left -> "hide/ic_rocket_left.png"
        FindTheStarCell.Type.Rocket.Right -> "hide/ic_rocket_right.png"
        FindTheStarCell.Type.Rocket.Up -> "hide/ic_rocket_up.png"
        FindTheStarCell.Type.Planet.Sun -> "hide/ic_sun.png"
        FindTheStarCell.Type.Planet.Mercury -> "hide/ic_mercury.png"
        FindTheStarCell.Type.Planet.Earth -> "hide/ic_earth.png"
        FindTheStarCell.Type.Planet.Jupiter -> "hide/ic_jupiter.png"
        FindTheStarCell.Type.Planet.Mars -> "hide/ic_mars.png"
        FindTheStarCell.Type.Planet.Moon -> "hide/ic_moon.png"
        FindTheStarCell.Type.Planet.Neptune -> "hide/ic_neptune.png"
        FindTheStarCell.Type.Planet.Saturn -> "hide/ic_saturn.png"
        FindTheStarCell.Type.Planet.Uranus -> "hide/ic_uranus.png"
        FindTheStarCell.Type.Planet.Venus -> "hide/ic_venus.png"
    }

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black,
            )
            .fillMaxSize()
            .background(color = color.toComposeColor())
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
    ) {
        AnimatedVisibility(isDiscovered) {
            typeIcon?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                )
            }
        }
        if (!isDiscovered) {
            DefaultText(
                text = id,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
