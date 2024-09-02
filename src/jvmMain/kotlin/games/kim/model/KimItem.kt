package games.kim.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class KimItem(
    val id: Int,
    val type: Type,
    val size: Size,
    val position: Position,
) {

    enum class Type(val resource: String) {

        ACORNS(resource = "kim/ic_acorns.png"),
        BOTTLE(resource = "kim/ic_bottle.png"),
        BREAD(resource = "kim/ic_bread.png"),
        CAR(resource = "kim/ic_car.png"),
        CHICKEN_LEG(resource = "kim/ic_chicken_leg.png"),
        CRAB(resource = "kim/ic_crab.png"),
        DOG_FOOD(resource = "kim/ic_dog_food.png"),
        DRUM(resource = "kim/ic_drum.png"),
        FLIP_FLOPS(resource = "kim/ic_flip_flops.png"),
        KETCHUP(resource = "kim/ic_ketchup.png"),
        KITCHEN_PAPER(resource = "kim/ic_kitchen_paper.png"),
        MAGNET(resource = "kim/ic_magnet.png"),
        MILK(resource = "kim/ic_milk.png"),
        ORANGE(resource = "kim/ic_orange_tree.png"),
        PANTS(resource = "kim/ic_pants.png"),
        PEAR(resource = "kim/ic_pear.png"),
        PIZZA(resource = "kim/ic_pizza.png"),
        POT(resource = "kim/ic_pot.png"),
        SCAFFOLDING(resource = "kim/ic_scaffolding.png"),
        SHOE(resource = "kim/ic_shoe.png"),
        SPIDER(resource = "kim/ic_spider.png"),
        SPOON(resource = "kim/ic_spoon.png"),
        STARFISH(resource = "kim/ic_starfish.png"),
        THREAD(resource = "kim/ic_thread.png"),
        TRIANGLE(resource = "kim/ic_triangle.png"),
        TOMATOES(resource = "kim/ic_tomatoes.png"),
        TREE(resource = "kim/ic_tree.png"),
        WATER_CAN(resource = "kim/ic_water_can.png"),
        WATER_ICE(resource = "kim/ic_water_ice.png"),
        WOOD_FIRE(resource = "kim/ic_woodfire.png"),
    }

    enum class Size(val dps: Dp) {

        X_SMALL(100.dp),
        SMALL(120.dp),
        MEDIUM(150.dp),
        LARGE(180.dp),
        X_LARGE(200.dp),
    }

    data class Position(
        val x: Float,
        val y: Float,
    ) {

        fun isNotCollideWith(position: Position, size: Size = Size.X_LARGE): Boolean {
            return position.x !in (x - size.dps.value..x + size.dps.value)
                    && position.x !in (y - size.dps.value..y + size.dps.value)
        }

        fun isInScreenBoundaries(windowSize: DpSize, size: Size = Size.X_LARGE): Boolean {
            return x < windowSize.width.value - size.dps.value && y < windowSize.height.value - size.dps.value
        }
    }
}
