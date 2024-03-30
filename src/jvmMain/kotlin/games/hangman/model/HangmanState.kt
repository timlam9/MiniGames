package games.hangman.model

data class HangmanState(
    val hiddenWord: HiddenWord = HiddenWord(),
    val wrongTries: Int = 0,
    val isGameOver: Boolean = true,
)
