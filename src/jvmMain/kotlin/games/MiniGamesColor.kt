package games

import ui.theme.*

enum class MiniGamesColor {

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

fun getRandomColor() = MiniGamesColor.values().random().toComposeColor()


fun MiniGamesColor.toComposeColor() = when (this) {
    MiniGamesColor.PURPLE -> Purple
    MiniGamesColor.ORANGE -> Orange
    MiniGamesColor.BLUE -> Blue
    MiniGamesColor.GREEN -> Green
    MiniGamesColor.GRAY -> Gray
    MiniGamesColor.PINK -> Pink
    MiniGamesColor.CIEL -> Ciel
    MiniGamesColor.RED -> Red
    MiniGamesColor.BROWN -> Brown
    MiniGamesColor.YELLOW -> Yellow
}
