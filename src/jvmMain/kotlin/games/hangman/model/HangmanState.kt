package games.hangman.model

val testHiddenWord: List<Letter> = listOf(
    Letter(1, 'Β', Letter.State.UNSELECTED),
    Letter(2, 'Ο', Letter.State.UNSELECTED),
    Letter(3, 'Υ', Letter.State.UNSELECTED),
    Letter(4, 'Κ', Letter.State.UNSELECTED),
    Letter(5, 'Ε', Letter.State.UNSELECTED),
    Letter(6, 'Φ', Letter.State.UNSELECTED),
    Letter(7, 'Α', Letter.State.UNSELECTED),
    Letter(8, 'Λ', Letter.State.UNSELECTED),
    Letter(9, 'Α', Letter.State.UNSELECTED),
    Letter(10, 'Σ', Letter.State.UNSELECTED),
)

data class HangmanState(
    val hiddenWord: HiddenWord = HiddenWord(letters = testHiddenWord),
    val wrongTries: Int = 0,
)
