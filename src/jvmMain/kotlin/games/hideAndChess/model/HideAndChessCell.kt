package games.hideAndChess.model

import games.GameColor

data class HideAndChessCell(
    val gameColor: GameColor,
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
                currentCell.positionX == positionX - 1 && currentCell.positionY == positionY && currentCell.gameColor != gameColor -> borders.add(Border.NORTH)
                currentCell.positionX == positionX + 1 && currentCell.positionY == positionY && currentCell.gameColor != gameColor -> borders.add(Border.SOUTH)
                currentCell.positionX == positionX && currentCell.positionY == positionY - 1 && currentCell.gameColor != gameColor -> borders.add(Border.WEST)
                currentCell.positionX == positionX && currentCell.positionY == positionY + 1 && currentCell.gameColor != gameColor -> borders.add(Border.EAST)
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
        val gameColorCode = when (gameColor) {
            GameColor.PURPLE -> 'p'
            GameColor.ORANGE -> 'O'
            GameColor.BLUE -> 'B'
            GameColor.GREEN -> 'E'
            GameColor.GRAY -> 'G'
            GameColor.PINK -> 'K'
            GameColor.CIEL -> 'C'
            GameColor.RED -> 'R'
            GameColor.BROWN -> 'W'
            GameColor.YELLOW -> 'Y'
        }

        val x = if (positionX == 10) 'X' else positionX
        val y = if (positionY == 10) 'X' else positionY

        return StringBuilder()
            .append(gameColorCode)
            .append(type.name.first())
            .append(mark.name.first())
            .append(x)
            .append(y)
            .toString()
    }

    companion object {
        fun decode(encodedCell: String): HideAndChessCell {
            val (color, type, mark, x, y) = encodedCell.toCharArray()

            val decodedGameColor = when (color) {
                'P' -> GameColor.PURPLE
                'O' -> GameColor.ORANGE
                'B' -> GameColor.BLUE
                'E' -> GameColor.GREEN
                'G' -> GameColor.GRAY
                'K' -> GameColor.PINK
                'C' -> GameColor.CIEL
                'R' -> GameColor.RED
                'W' -> GameColor.BROWN
                'Y' -> GameColor.YELLOW
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
                gameColor = decodedGameColor,
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
