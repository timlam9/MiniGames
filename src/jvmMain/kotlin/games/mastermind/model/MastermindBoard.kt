package games.mastermind.model

import games.MiniGamesColor

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

val mastermindColors = MiniGamesColor.values().take(6)
val mastermindCode: MutableList<CodeCell> = mutableListOf()

data class CodeCell(
    val id: Int,
    val color: MiniGamesColor?,
)

fun generateRandomMastermindBoard(): MastermindBoard {
    val board = mutableListOf<MastermindCell>()
    var id = 1

    mastermindCode.clear()

    mastermindColors.shuffled().take(4).forEachIndexed { index, miniGamesColor ->
        mastermindCode.add(CodeCell(id = index, color = miniGamesColor))
    }

    for (y in BoardYCoordinates.indices) {
        for (x in BoardXCoordinates.indices) {
            board.add(
                MastermindCell(
                    id = id,
                    position = MastermindCell.Position(x, y),
                    color = if (y == 0 && x < 4) mastermindCode[x].color else null,
                    isRevealed = !(y == 0 && x < 4),
                    type = if (x < 4) MastermindCell.Type.PlayerCell else MastermindCell.Type.HintsCell(emptyList())
                )
            )

            id++
        }
    }

    return MastermindBoard(value = board)
}

fun List<MastermindCell>.toCodeCells(): List<CodeCell> = mapIndexed { index, mastermindCell ->
    CodeCell(id = index, color = mastermindCell.color ?: MiniGamesColor.PINK)
}

fun List<CodeCell>.getHints(code: List<CodeCell>): List<MastermindCell.Hint> {
    val groupedCells = groupBy { it.color }
    val hints = mutableListOf<MastermindCell.Hint>()

    groupedCells.forEach { groupedCell ->
        val color = groupedCell.key
        val cells = groupedCell.value

        code.forEach { codeCell ->
            if (codeCell.color == color) {
                val hint = if (cells.contains(codeCell)) {
                    MastermindCell.Hint.RIGHT_POSITION
                } else {
                    MastermindCell.Hint.RIGHT_COLOR
                }
                hints.add(hint)
            }
        }
    }

    return hints.shuffled()
}
