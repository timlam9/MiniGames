package games.batlleship.model

import games.GameColor

data class BattleshipCell(
    val id: Int,
    val position: Position,
    val isAttacked: Boolean,
    val isRevealed: Boolean,
    val gameColor: GameColor,
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

        fun isInRangeHorizontally(position: Position, size: Int): Boolean {
            return position.y == y && (x > position.x && x < position.x + size)
        }

        fun isInRangeVertically(position: Position, size: Int): Boolean {
            return position.x == x && (y > position.y && y < position.y + size)
        }
    }

    enum class Ship(val id: Int, val size: Int) {

        CARRIER(1, 5),
        BATTLESHIP(2, 4),
        DESTROYER(3, 3),
        SUBMARINE(4, 3),
        PATROL_BOAT(5, 2);

        companion object {

            private val map = Ship.values().associateBy { it.id }
            operator fun get(id: Int) = map[id]
        }
    }

    enum class Type(val ship: Ship?) {

        CARRIER_A(ship = Ship.CARRIER),
        CARRIER_B(ship = Ship.CARRIER),
        CARRIER_C(ship = Ship.CARRIER),
        CARRIER_D(ship = Ship.CARRIER),
        CARRIER_E(ship = Ship.CARRIER),
        BATTLESHIP_A(ship = Ship.BATTLESHIP),
        BATTLESHIP_B(ship = Ship.BATTLESHIP),
        BATTLESHIP_C(ship = Ship.BATTLESHIP),
        BATTLESHIP_D(ship = Ship.BATTLESHIP),
        DESTROYER_A(ship = Ship.DESTROYER),
        DESTROYER_B(ship = Ship.DESTROYER),
        DESTROYER_C(ship = Ship.DESTROYER),
        SUBMARINE_A(ship = Ship.SUBMARINE),
        SUBMARINE_B(ship = Ship.SUBMARINE),
        SUBMARINE_C(ship = Ship.SUBMARINE),
        PATROL_BOAT_A(ship = Ship.PATROL_BOAT),
        PATROL_BOAT_B(ship = Ship.PATROL_BOAT),
        SEA(ship = null),
    }
}
