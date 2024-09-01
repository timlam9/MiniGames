package games.batlleship.components

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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import games.GameColor
import games.batlleship.model.BattleshipCell
import ui.design.DefaultText
import ui.theme.*

@Composable
fun BattleshipCellUI(
    title: String,
    gameColor: GameColor,
    type: BattleshipCell.Type,
    isAttacked: Boolean,
    isRevealed: Boolean,
    shipDirection: BattleshipCell.ShipDirection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundGameColor = when (gameColor) {
        GameColor.PURPLE -> Purple
        GameColor.ORANGE -> Orange
        GameColor.BLUE -> Blue
        GameColor.GREEN -> Green
        GameColor.GRAY -> Gray
        GameColor.PINK -> Pink
        GameColor.CIEL -> Ciel
        GameColor.RED -> Red
        GameColor.BROWN -> Brown
        GameColor.YELLOW -> Yellow
    }

    val typeIcon = when (type) {
        BattleshipCell.Type.CARRIER_A -> "battleship/carrier_01.png"
        BattleshipCell.Type.CARRIER_B -> "battleship/carrier_02.png"
        BattleshipCell.Type.CARRIER_C -> "battleship/carrier_03.png"
        BattleshipCell.Type.CARRIER_D -> "battleship/carrier_04.png"
        BattleshipCell.Type.CARRIER_E -> "battleship/carrier_05.png"
        BattleshipCell.Type.BATTLESHIP_A -> "battleship/battleship_01.png"
        BattleshipCell.Type.BATTLESHIP_B -> "battleship/battleship_02.png"
        BattleshipCell.Type.BATTLESHIP_C -> "battleship/battleship_03.png"
        BattleshipCell.Type.BATTLESHIP_D -> "battleship/battleship_04.png"
        BattleshipCell.Type.DESTROYER_A -> "battleship/destroyer_01.png"
        BattleshipCell.Type.DESTROYER_B -> "battleship/destroyer_02.png"
        BattleshipCell.Type.DESTROYER_C -> "battleship/destroyer_03.png"
        BattleshipCell.Type.SUBMARINE_A -> "battleship/submarine_01.png"
        BattleshipCell.Type.SUBMARINE_B -> "battleship/submarine_02.png"
        BattleshipCell.Type.SUBMARINE_C -> "battleship/submarine_03.png"
        BattleshipCell.Type.PATROL_BOAT_A -> "battleship/patrol_boat_01.png"
        BattleshipCell.Type.PATROL_BOAT_B -> "battleship/patrol_boat_02.png"
        BattleshipCell.Type.SEA -> "battleship/sea_tile.png"
    }

    val attackIcon = when (type) {
        BattleshipCell.Type.SEA -> "battleship/ic_target.png"
        else -> "battleship/ic_explosion.png"
    }


    Box(
        modifier = modifier
            .border(
                width = 0.dp,
                color = Color.Black,
            )
            .fillMaxSize()
            .background(color = if (isRevealed) Gray else backgroundGameColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
    ) {
        AnimatedVisibility(isRevealed || isAttacked && type == BattleshipCell.Type.SEA) {
            Image(
                painter = painterResource(typeIcon),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(if (shipDirection == BattleshipCell.ShipDirection.DOWN) 90f else 0f),
            )
        }

        AnimatedVisibility(isAttacked && type != BattleshipCell.Type.SEA) {
            Image(
                painter = painterResource(attackIcon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().padding(8.dp),
            )
        }

        if (!isAttacked && !isRevealed) {
            DefaultText(text = title, modifier = Modifier.align(Alignment.Center))
        }
    }
}
