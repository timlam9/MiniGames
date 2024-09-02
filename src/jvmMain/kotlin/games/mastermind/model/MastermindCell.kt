package games.mastermind.model

import games.MiniGamesColor

data class MastermindCell(
    val id: Int,
    val position: Position,
    val color: MiniGamesColor?,
    val isRevealed: Boolean,
    val type: Type,
) {

    data class Position(
        val x: Int,
        val y: Int,
    )

    sealed interface Type {

        object PlayerCell: Type

        data class HintsCell(val hints: List<Hint>): Type
    }

    enum class Hint {

        RIGHT_COLOR,
        RIGHT_POSITION,
    }
}
