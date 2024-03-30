package games.hangman.model

data class HangmanState(
    val hiddenWord: HiddenWord = HiddenWord(),
    val previousHiddenWord: HiddenWord = HiddenWord(),
    val keyLetters: List<Letter> = keyLetters(),
    val wrongTries: Int = 0,
    val isGameOver: Boolean = true,
)

internal fun keyLetters(): List<Letter> = listOf(
    Letter(0, 'Α'),
    Letter(1, 'Β'),
    Letter(2, 'Γ'),
    Letter(3, 'Δ'),
    Letter(4, 'Ε'),
    Letter(5, 'Ζ'),
    Letter(6, 'Η'),
    Letter(7, 'Θ'),
    Letter(8, 'Ι'),
    Letter(9, 'Κ'),
    Letter(10, 'Λ'),
    Letter(11, 'Μ'),
    Letter(12, 'Ν'),
    Letter(13, 'Ξ'),
    Letter(14, 'Ο'),
    Letter(15, 'Π'),
    Letter(16, 'Ρ'),
    Letter(17, 'Σ'),
    Letter(18, 'Τ'),
    Letter(19, 'Υ'),
    Letter(20, 'Φ'),
    Letter(21, 'Χ'),
    Letter(22, 'Ψ'),
    Letter(23, 'Ω'),
)
