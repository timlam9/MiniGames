package games.batlleship.model

import games.hideAndChess.model.HideAndChessCell
import ui.next

data class BattleshipBoard(
    val value: List<BattleshipCell>
)

/**
 * A to J
 */
val BoardXCoordinates = List(10) {
    it
}

/**
 * 1 to 10
 */
val BoardYCoordinates = List(10) {
    it
}

fun generateRandomBattleshipBoard(): BattleshipBoard {
    val board = mutableListOf<BattleshipCell>()
    var id = 1
    var color = HideAndChessCell.Color.PURPLE


    for (y in BoardYCoordinates.indices) {
        for (x in BoardXCoordinates.indices) {
            board.add(
                BattleshipCell(
                    id = id,
                    position = BattleshipCell.Position(x = x, y = y),
                    color = color,
                    type = BattleshipCell.Type.SEA,
                    isAttacked = false,
                    isRevealed = false,
                    shipDirection = BattleshipCell.ShipDirection.RIGHT,
                )
            )

            id++
            color = color.next()
        }
    }

    val boardWithShips = board.addShips()

    return BattleshipBoard(value = boardWithShips)
}

private fun List<BattleshipCell>.addShips(): MutableList<BattleshipCell> {
    var board = this

    repeat(5) { number ->
        val direction = BattleshipCell.ShipDirection.values().random()
        var randomCell = this.random()
        println("Random cell: $randomCell")

        val ship = BattleshipCell.Ship[number + 1]!!
        var shipCell = when (ship) {
            BattleshipCell.Ship.CARRIER -> BattleshipCell.Type.CARRIER_A
            BattleshipCell.Ship.BATTLESHIP -> BattleshipCell.Type.BATTLESHIP_A
            BattleshipCell.Ship.DESTROYER -> BattleshipCell.Type.DESTROYER_A
            BattleshipCell.Ship.SUBMARINE -> BattleshipCell.Type.SUBMARINE_A
            BattleshipCell.Ship.PATROL_BOAT -> BattleshipCell.Type.PATROL_BOAT_A
        }

        when (direction) {
            BattleshipCell.ShipDirection.RIGHT -> {
                while (
                    randomCell.position.x + ship.size > BoardXCoordinates.size
                    || board
                        .filter { it.position.y == randomCell.position.y && it.position.x in (randomCell.position.x..(randomCell.position.x + ship.size)) }
                        .any { it.type != BattleshipCell.Type.SEA }
                    || randomCell.type != BattleshipCell.Type.SEA
                ) {
                    randomCell = this.random()
                }
                board = board.map { boardCell ->
                    if (
                        boardCell.position.isSame(position = randomCell.position)
                        || boardCell.position.isInRangeHorizontally(
                            position = randomCell.position,
                            size = ship.size,
                        )
                    ) {
                        println("Current cell: $boardCell | ship cell: $shipCell")
                        val cell = boardCell.copy(type = shipCell)
                        shipCell = shipCell.next()

                        cell
                    } else {
                        boardCell
                    }
                }
            }

            BattleshipCell.ShipDirection.DOWN -> {
                while (
                    randomCell.position.y + ship.size > BoardYCoordinates.size
                    || board
                        .filter { it.position.x == randomCell.position.x && it.position.y in (randomCell.position.y..(randomCell.position.y + ship.size)) }
                        .any { it.type != BattleshipCell.Type.SEA }
                    || randomCell.type != BattleshipCell.Type.SEA
                ) {
                    randomCell = this.random()
                }

                board = board.map { boardCell ->
                    if (boardCell.position.isSame(position = randomCell.position)
                        || boardCell.position.isInRangeVertically(
                            position = randomCell.position,
                            size = ship.size,
                        )
                    ) {
                        val cell = boardCell.copy(
                            type = shipCell,
                            shipDirection = BattleshipCell.ShipDirection.DOWN,
                        )
                        shipCell = shipCell.next()

                        cell
                    } else {
                        boardCell
                    }
                }
            }
        }
    }
    return board.toMutableList()
}
