package games.hangman.model

data class HiddenWord(
    val letters: List<Letter> = emptyList()
) {

    fun toHangmanWord(): String {
        return letters.map {
            when {
                it.char == '-' -> ' '
                it.state == Letter.State.SELECTED -> it.char
                else -> '_'
            }
        }.joinToString(" ") {
            it.toString()
        }
    }
}