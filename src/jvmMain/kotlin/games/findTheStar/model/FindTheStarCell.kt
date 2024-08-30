package games.findTheStar.model

import games.hideAndChess.model.HideAndChessCell

data class FindTheStarCell(
    val id: Int,
    val position: Position,
    val type: Type,
    val isDiscovered: Boolean,
    val color: HideAndChessCell.Color
) {

    data class Position(
        val x: Int,
        val y: Int,
    )

    sealed interface Type {

        object EmptySpace: Type

        sealed interface Rocket: Type {
            object Up: Rocket
            object Down: Rocket
            object Left: Rocket
            object Right: Rocket
        }

        sealed interface Planet: Type {
            object Sun: Planet
            object Mercury: Planet
            object Venus: Planet
            object Earth: Planet
            object Moon: Planet
            object Mars: Planet
            object Jupiter: Planet
            object Saturn: Planet
            object Uranus: Planet
            object Neptune: Planet
        }
    }
}
