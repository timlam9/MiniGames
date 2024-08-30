package games.findTheStar

import GameEngine
import games.findTheStar.model.FindTheStarBoard
import games.findTheStar.model.FindTheStarCell
import games.findTheStar.model.FindTheStarState
import games.findTheStar.model.generateRandomBoard
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FindTheStarGameEngine : GameEngine {

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private val initialState = FindTheStarState(board = generateRandomBoard())

    private val _state: MutableStateFlow<FindTheStarState> = MutableStateFlow(initialState)
    val state: StateFlow<FindTheStarState> = _state.asStateFlow()

    fun onCellClick(cell: FindTheStarCell) {
        _state.update { currentState ->
            currentState.copy(
                board = currentState.board.copy(
                    value = currentState.board.value.map {
                        if (it.id == cell.id) {
                            it.copy(
                                isDiscovered = true
                            )
                        } else {
                            it
                        }
                    }
                )
            )
        }

        if (cell.type == FindTheStarCell.Type.Planet.Sun) {
            onRevealBoardClick()
        }
    }

    fun onPlayAgainClick() {
        scope.launch {
            emptyBoard()
            delay(150)
            _state.update { currentState ->
                currentState.copy(board = generateRandomBoard())
            }
        }
    }

    private fun emptyBoard() {
        _state.update { currentState ->
            currentState.copy(
                board = FindTheStarBoard(emptyList())
            )
        }
    }

    fun onRevealBoardClick() {
        _state.update { currentState ->
            currentState.copy(
                board = currentState.board.copy(
                    value = currentState.board.value.map {
                        it.copy(isDiscovered = true)
                    }
                )
            )
        }
    }
}
