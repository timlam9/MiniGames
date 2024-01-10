package games.hangman.model

data class Letter(
    val id: Int,
    val char: Char,
    val state: State = State.UNSELECTED,
) {

    enum class State {

        UNSELECTED,
        SELECTED,
    }
}