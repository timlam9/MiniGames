package games.hideAndChess.model

data class HideAndChessCell(
    val color: Color,
    val type: Type,
    val mark: Mark,
    val positionX: Int,
    val positionY: Int,
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

    enum class Color {

        PURPLE, // P
        ORANGE, // O
        BLUE, //  B
        GREEN, // E
        GRAY, // G
        PINK, // K
        CIEL, // C
        RED, // R
        BROWN, // W
        YELLOW,  // Y
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
        val colorCode = when (color) {
            Color.PURPLE -> 'p'
            Color.ORANGE -> 'O'
            Color.BLUE -> 'B'
            Color.GREEN -> 'E'
            Color.GRAY -> 'G'
            Color.PINK -> 'K'
            Color.CIEL -> 'C'
            Color.RED -> 'R'
            Color.BROWN -> 'W'
            Color.YELLOW -> 'Y'
        }

        val x = if (positionX == 10) 'X' else positionX
        val y = if (positionY == 10) 'X' else positionY

        return StringBuilder()
            .append(colorCode)
            .append(type.name.first())
            .append(mark.name.first())
            .append(x)
            .append(y)
            .toString()
    }

    companion object {
        fun decode(encodedCell: String): HideAndChessCell {
            val (color, type, mark, x, y) = encodedCell.toCharArray()

            val decodedColor = when (color) {
                'P' -> Color.PURPLE
                'O' -> Color.ORANGE
                'B' -> Color.BLUE
                'E' -> Color.GREEN
                'G' -> Color.GRAY
                'K' -> Color.PINK
                'C' -> Color.CIEL
                'R' -> Color.RED
                'W' -> Color.BROWN
                'Y' -> Color.YELLOW
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
                color = decodedColor,
                type = decodedType,
                mark = decodedMark,
                positionX = decodedPositionX,
                positionY = decodedPositionY,
            )
        }

        const val ENCODED_CELL_LENGTH = 5
    }
}
