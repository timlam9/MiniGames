package games.hangman

import GameEngine
import games.hangman.model.HangmanState
import games.hangman.model.HiddenWord
import games.hangman.model.Letter
import games.hangman.model.keyLetters
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
        val hiddenWord = HiddenWord(
            hiddenWordString.toList().mapIndexed { index, char ->
                Letter(index, char, state = if (char == ' ') Letter.State.SELECTED else Letter.State.UNSELECTED)
            }
        )
        _state.update {
            it.copy(
                hiddenWord = hiddenWord,
                wrongTries = 0,
                isGameOver = false,
                keyLetters = keyLetters()
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