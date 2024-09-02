package games.hideAndChess.model

import games.MiniGamesColor

data class HideAndChessCell(
    val color: MiniGamesColor,
    val type: Type,
    val mark: Mark,
    val positionX: Int,
    val positionY: Int,
    val isRevealed: Boolean,
) {

    fun getBorders(board: HideAndChessBoard): Set<Border> {
        val borders = mutableListOf<Border>()

        board.value.forEach { currentCell ->
            when {
                currentCell.positionX == positionX - 1 && currentCell.positionY == positionY && currentCell.color != color -> borders.add(Border.NORTH)
                currentCell.positionX == positionX + 1 && currentCell.positionY == positionY && currentCell.color != color -> borders.add(Border.SOUTH)
                currentCell.positionX == positionX && currentCell.positionY == positionY - 1 && currentCell.color != color -> borders.add(Border.WEST)
                currentCell.positionX == positionX && currentCell.positionY == positionY + 1 && currentCell.color != color -> borders.add(Border.EAST)
            }
        }

        return borders.toSet()
    }

    enum class Border {

        NORTH,
        WEST,
        EAST,
        SOUTH,
    }

    enum class Type {

        NONE,
        KING,
    }

    enum class Mark {

        NONE,
        X,
        TIC,
    }

    fun encode(): String {
        val miniGamesColorCode = when (color) {
            MiniGamesColor.PURPLE -> 'p'
            MiniGamesColor.ORANGE -> 'O'
            MiniGamesColor.BLUE -> 'B'
            MiniGamesColor.GREEN -> 'E'
            MiniGamesColor.GRAY -> 'G'
            MiniGamesColor.PINK -> 'K'
            MiniGamesColor.CIEL -> 'C'
            MiniGamesColor.RED -> 'R'
            MiniGamesColor.BROWN -> 'W'
            MiniGamesColor.YELLOW -> 'Y'
        }

        val x = if (positionX == 10) 'X' else positionX
        val y = if (positionY == 10) 'X' else positionY

        return StringBuilder()
            .append(miniGamesColorCode)
            .append(type.name.first())
            .append(mark.name.first())
            .append(x)
            .append(y)
            .toString()
    }

    companion object {
        fun decode(encodedCell: String): HideAndChessCell {
            val (color, type, mark, x, y) = encodedCell.toCharArray()

            val decodedMiniGamesColor = when (color) {
                'P' -> MiniGamesColor.PURPLE
                'O' -> MiniGamesColor.ORANGE
                'B' -> MiniGamesColor.BLUE
                'E' -> MiniGamesColor.GREEN
                'G' -> MiniGamesColor.GRAY
                'K' -> MiniGamesColor.PINK
                'C' -> MiniGamesColor.CIEL
                'R' -> MiniGamesColor.RED
                'W' -> MiniGamesColor.BROWN
                'Y' -> MiniGamesColor.YELLOW
                else -> throw IllegalArgumentException("Invalid cell color!")
            }

            val decodedType = when (type) {
                'N' -> Type.NONE
                'K' -> Type.KING
                else -> throw IllegalArgumentException("Invalid cell type!")
            }

            val decodedMark = when (mark) {
                'N' -> Mark.NONE
                'X' -> Mark.X
                'T' -> Mark.TIC
                else -> throw IllegalArgumentException("Invalid cell mark!")
            }

            val decodedPositionX = when {
                x == 'X' -> 10
                x.isDigit() -> x.digitToInt()
                else -> throw IllegalArgumentException("Invalid position X mark!")
            }

            val decodedPositionY = when {
                y == 'X' -> 10
                y.isDigit() -> y.digitToInt()
                else -> throw IllegalArgumentException("Invalid position Y mark!")
            }

            return HideAndChessCell(
                color = decodedMiniGamesColor,
                type = decodedType,
                mark = decodedMark,
                positionX = decodedPositionX,
                positionY = decodedPositionY,
                isRevealed = false,
            )
        }

        const val ENCODED_CELL_LENGTH = 5
    }
}
