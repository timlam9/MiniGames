package games.hangman

import GameEngine
import games.hangman.model.HangmanState
import games.hangman.model.Letter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HangmanGameEngine : GameEngine {

    private val _state: MutableStateFlow<HangmanState> = MutableStateFlow(HangmanState())
    val state: StateFlow<HangmanState> = _state.asStateFlow()

    fun onKeyClick(keyLetter: Letter) {
        _state.update {
            var isLetterWrong = true

            val updatedLetters = it.hiddenWord.letters.map { letter ->
                if (letter.char == keyLetter.char) {
                    isLetterWrong = false
                    letter.copy(state = Letter.State.SELECTED)
                } else {
                    letter
                }
            }
            val updatedWrongTries = if (isLetterWrong) it.wrongTries + 1 else it.wrongTries

            it.copy(
                hiddenWord = it.hiddenWord.copy(letters = updatedLetters),
                wrongTries = updatedWrongTries,
            )
        }
    }
}