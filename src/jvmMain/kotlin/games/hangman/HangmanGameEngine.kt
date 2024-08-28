package games.hangman

import GameEngine
import games.hangman.model.*
import games.hangman.model.englishKeyLetters
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HangmanGameEngine : GameEngine {

    private val scope = CoroutineScope(Dispatchers.Default + Job())

    private val _state: MutableStateFlow<HangmanState> = MutableStateFlow(HangmanState())
    val state: StateFlow<HangmanState> = _state.asStateFlow()

    fun onKeyClick(keyLetter: Letter) {
        _state.update {
            var isLetterWrong = true

            val updatedLetters = it.hiddenWord.letters.map { letter ->
                if (letter.char.equals(keyLetter.char, ignoreCase = true)) {
                    isLetterWrong = false
                    letter.copy(state = Letter.State.SELECTED)
                } else {
                    letter
                }
            }

            val updatedKeyLetters = it.keyLetters.map { letter ->
                if (letter.char.equals(keyLetter.char, ignoreCase = true)) {
                    letter.copy(state = Letter.State.SELECTED)
                } else {
                    letter
                }
            }

            val updatedWrongTries = if (isLetterWrong) it.wrongTries + 1 else it.wrongTries

            val updatedState = it.copy(
                hiddenWord = it.hiddenWord.copy(letters = updatedLetters),
                wrongTries = updatedWrongTries,
                keyLetters = updatedKeyLetters,
            )

            updatedState.isGameOver()

            updatedState
        }
    }

    fun onPlayAgainClick(hiddenWordString: String) {
        if (hiddenWordString.isBlank()) return

        val hiddenWord = HiddenWord(
            hiddenWordString.trim().toList().mapIndexed { index, char ->
                Letter(index, char, state = if (char == ' ') Letter.State.SELECTED else Letter.State.UNSELECTED)
            }
        )

        val isFirstLetterEnglish = englishKeyLetters().any {
            it.char.equals(
                other = hiddenWord.letters.first().char,
                ignoreCase = true
            )
        }

        _state.update {
            it.copy(
                hiddenWord = hiddenWord,
                wrongTries = 0,
                isGameOver = false,
                keyLetters = if (isFirstLetterEnglish) englishKeyLetters() else greekKeyLetters()
            )
        }
    }

    private fun HangmanState.isGameOver() {
        val isGameOver = wrongTries == 8 || hiddenWord.letters.all { it.state == Letter.State.SELECTED }
        if (isGameOver) {
            scope.launch {
                _state.update {
                    it.copy(
                        previousHiddenWord = it.hiddenWord.revealed(),
                        isGameOver = true,
                    )
                }
                delay(200)
            }
        } else {
            _state
        }
    }
}