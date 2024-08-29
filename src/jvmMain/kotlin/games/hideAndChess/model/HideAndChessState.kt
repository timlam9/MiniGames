package games.hideAndChess.model

data class HideAndChessState(
    val board: HideAndChessBoard,
    val shouldReveal: Boolean,
)
