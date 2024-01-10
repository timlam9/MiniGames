package games.hangman.model

data class HangmanState(
    val hiddenWord: HiddenWord = HiddenWord(
        listOf(
            Letter(1, 'Β', Letter.State.SELECTED),
            Letter(2, 'Ο', Letter.State.UNSELECTED),
            Letter(3, 'Υ', Letter.State.SELECTED),
            Letter(4, 'Κ', Letter.State.UNSELECTED),
            Letter(5, 'Ε', Letter.State.SELECTED),
            Letter(6, 'Φ', Letter.State.SELECTED),
            Letter(7, 'Α', Letter.State.UNSELECTED),
            Letter(8, 'Λ', Letter.State.UNSELECTED),
            Letter(9, 'Α', Letter.State.UNSELECTED),
            Letter(10, 'Σ', Letter.State.SELECTED),

            Letter(10, '-', Letter.State.SELECTED),

            Letter(1, 'Β', Letter.State.SELECTED),
            Letter(2, 'Ο', Letter.State.UNSELECTED),
            Letter(3, 'Υ', Letter.State.SELECTED),
            Letter(4, 'Κ', Letter.State.UNSELECTED),
            Letter(5, 'Ε', Letter.State.SELECTED),
            Letter(6, 'Φ', Letter.State.SELECTED),
            Letter(7, 'Α', Letter.State.UNSELECTED),
            Letter(8, 'Λ', Letter.State.UNSELECTED),
            Letter(9, 'Α', Letter.State.UNSELECTED),
            Letter(10, 'Σ', Letter.State.SELECTED),

            Letter(10, '-', Letter.State.SELECTED),

            Letter(1, 'Β', Letter.State.SELECTED),
            Letter(2, 'Ο', Letter.State.UNSELECTED),
            Letter(3, 'Υ', Letter.State.SELECTED),
            Letter(4, 'Κ', Letter.State.UNSELECTED),
            Letter(5, 'Ε', Letter.State.SELECTED),
            Letter(6, 'Φ', Letter.State.SELECTED),
            Letter(7, 'Α', Letter.State.UNSELECTED),
            Letter(8, 'Λ', Letter.State.UNSELECTED),
            Letter(9, 'Α', Letter.State.UNSELECTED),
            Letter(10, 'Σ', Letter.State.SELECTED),
        )
    )
)