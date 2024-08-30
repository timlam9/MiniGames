package games.findTheStar.model

import games.hideAndChess.model.HideAndChessCell
import ui.next

data class FindTheStarBoard(
    val value: List<FindTheStarCell>
)

/**
 * 1 to 10
 */
val BoardYCoordinates = List(12) {
    it + 1
}

/**
 * 1 to 10
 */
val BoardXCoordinates = List(8) {
    it + 1
}

fun generateRandomBoard(): FindTheStarBoard {
    val board = mutableListOf<FindTheStarCell>()
    var id = 1
    var color = HideAndChessCell.Color.PURPLE

    for (x in 1..BoardXCoordinates.size) {
        for (y in 1..BoardYCoordinates.size) {
            board.add(
                FindTheStarCell(
                    id = id,
                    position = FindTheStarCell.Position(x, y),
                    color = color,
                    type = FindTheStarCell.Type.EmptySpace,
                    isDiscovered = false,
                )
            )

            id++
            color = color.next()
        }
    }

    val boardWithPlanets = board.shuffled().mapIndexed { index, cell ->
        when (index) {
            0 -> cell.copy(type = FindTheStarCell.Type.Planet.Sun)
            1 -> cell.copy(type = FindTheStarCell.Type.Planet.Mercury)
            2 -> cell.copy(type = FindTheStarCell.Type.Planet.Venus)
            3 -> cell.copy(type = FindTheStarCell.Type.Planet.Earth)
            4 -> cell.copy(type = FindTheStarCell.Type.Planet.Moon)
            5 -> cell.copy(type = FindTheStarCell.Type.Planet.Mars)
            6 -> cell.copy(type = FindTheStarCell.Type.Planet.Jupiter)
            7 -> cell.copy(type = FindTheStarCell.Type.Planet.Saturn)
            8 -> cell.copy(type = FindTheStarCell.Type.Planet.Uranus)
            9 -> cell.copy(type = FindTheStarCell.Type.Planet.Neptune)
            else -> cell
        }
    }
    var sunCell = boardWithPlanets.first()
    var boardWithDirections = boardWithPlanets

    repeat(50) {
        val directionCell = boardWithDirections
            .filter { it.type == FindTheStarCell.Type.EmptySpace }
            .random()

        boardWithDirections = boardWithDirections.map {
            if (it.id == directionCell.id) {
                it.copy(
                    type = when {
                        directionCell.position.y < sunCell.position.y -> FindTheStarCell.Type.Rocket.Right
                        directionCell.position.y > sunCell.position.y -> FindTheStarCell.Type.Rocket.Left
                        directionCell.position.x < sunCell.position.x -> FindTheStarCell.Type.Rocket.Down
                        directionCell.position.x > sunCell.position.x -> FindTheStarCell.Type.Rocket.Up
                        else -> FindTheStarCell.Type.EmptySpace
                    }
                )
            } else {
                it
            }
        }
    }

    return FindTheStarBoard(value = boardWithDirections)
}
