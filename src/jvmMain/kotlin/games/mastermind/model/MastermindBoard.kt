package games.mastermind.model

import games.GameColor

data class MastermindBoard(
    val value: List<MastermindCell>
)

/**
 * 0 to 10
 */
val BoardYCoordinates = List(11) {
    it
}

/**
 * 1 to 5
 */
val BoardXCoordinates = List(5) {
    it
}

val mastermindColors = GameColor.values().take(6)
var mastermindCode: List<GameColor> = emptyList()

fun generateRandomMastermindBoard(): MastermindBoard {
    val board = mutableListOf<MastermindCell>()
    var id = 1

    mastermindCode = mastermindColors.shuffled().take(4)

    for (y in BoardYCoordinates.indices) {
        for (x in BoardXCoordinates.indices) {
            board.add(
                MastermindCell(
                    id = id,
                    position = MastermindCell.Position(x, y),
                    gameColor = if (y == 0 && x < 4) mastermindCode[x] else null,
                    isRevealed = !(y == 0 && x < 4),
                    type = if (x < 4) MastermindCell.Type.PlayerCell else MastermindCell.Type.HintsCell(emptyList())
                )
            )

            id++
        }
    }

    return MastermindBoard(value = board)
}
