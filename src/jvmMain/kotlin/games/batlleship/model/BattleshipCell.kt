package games.batlleship.model

import games.hideAndChess.model.HideAndChessCell

data class BattleshipCell(
    val id: Int,
    val position: Position,
    val isAttacked: Boolean,
    val color: HideAndChessCell.Color,
    val type: Type,
    val shipDirection: ShipDirection,
) {

    enum class ShipDirection {

        RIGHT,
        DOWN,
    }

    data class Position(
        val x: Int,
        val y: Int,
    ) {

        fun isSame(position: Position) = position.x == x && position.y == y

        fun isInRangeHorizontally(position: Position, size: Int) =
            position.y == y && (x > position.x && x < position.x + size)

        fun isInRangeVertically(position: Position, size: Int) =
            position.x == x && (y > position.y && y < position.y + size)
    }

    enum class Type {

        CARRIER_A,
        CARRIER_B,
        CARRIER_C,
        CARRIER_D,
        CARRIER_E,
        BATTLESHIP_A,
        BATTLESHIP_B,
        BATTLESHIP_C,
        BATTLESHIP_D,
        DESTROYER_A,
        DESTROYER_B,
        DESTROYER_C,
        SUBMARINE_A,
        SUBMARINE_B,
        SUBMARINE_C,
        PATROL_BOAT_A,
        PATROL_BOAT_B,
        SEA,
    }
}
