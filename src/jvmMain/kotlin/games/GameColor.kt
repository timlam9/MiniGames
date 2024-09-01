package games

import ui.theme.*

enum class GameColor {

    PURPLE, // P
    ORANGE, // O
    BLUE, //  B
    GREEN, // E
    RED, // R
    YELLOW,  // Y
    BROWN, // W
    GRAY, // G
    PINK, // K
    CIEL, // C
}


fun GameColor.toComposeColor() = when (this) {
    GameColor.PURPLE -> Purple
    GameColor.ORANGE -> Orange
    GameColor.BLUE -> Blue
    GameColor.GREEN -> Green
    GameColor.GRAY -> Gray
    GameColor.PINK -> Pink
    GameColor.CIEL -> Ciel
    GameColor.RED -> Red
    GameColor.BROWN -> Brown
    GameColor.YELLOW -> Yellow
}
