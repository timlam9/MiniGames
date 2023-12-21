package games

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ui.getImagePath
import ui.model.memo.Photo
import ui.model.memo.UiState

private const val DELAY_TIME: Long = 1_000

class MemoGameEngine : GameEngine {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private var lastRevealedIndex = -1

    fun onGameStart() {
        initPhotos()
        startGame()
    }

    private fun initPhotos() {
        val list = mutableListOf<Photo>()
        val size = 12

        repeat(size) {
            list.add(
                Photo(
                    id = it.toString(),
                    status = Photo.Status.CLOSED,
                    name = it.toString(),
                    res = getImagePath(it + 1),
                )
            )
        }

        _state.update { it.copy(photos = it.photos.copy(value = list)) }
    }

    private fun startGame() {
        val list = _state.value.photos.value.toMutableList()
        val size = list.size

        // add doubles and shuffle the list
        _state.value.photos.value.forEachIndexed { index, photo ->
            list.add(
                Photo(
                    id = (index + size).toString(),
                    res = photo.res,
                    status = Photo.Status.CLOSED,
                    name = index.toString(),
                )
            )
        }

        _state.update { it.copy(photos = it.photos.copy(value = list.shuffled())) }
    }

    fun resetGame() {
        _state.update { UiState() }
    }

    fun onCardClick(photoId: String) {
        val updatedList = _state.value.photos.value.updateRevealedStatus(photoId)
        val indexOfPhoto = _state.value.photos.value.findIndexFrom(photoId)

        _state.update { it.copy(photos = it.photos.copy(value = updatedList)) }
        _state.value.photos.value[indexOfPhoto].checkIfFound()
    }

    private fun List<Photo>.findIndexFrom(id: String): Int {
        val item = find { it.id == id }
        return indexOf(item)
    }

    private fun List<Photo>.updateRevealedStatus(photoId: String, isMatch: Boolean = false): List<Photo> {
        val list = toMutableList()
        val photoToChange = list.find { it.id == photoId } ?: return this
        val indexToChange = list.indexOf(photoToChange)
        val newPhoto = photoToChange.copy(status = photoToChange.changeStatus(isMatch))

        list[indexToChange] = newPhoto

        return list
    }

    private fun Photo.changeStatus(isMatch: Boolean): Photo.Status = when {
        isMatch -> Photo.Status.MATCHED
        isRevealed() -> Photo.Status.CLOSED
        else -> Photo.Status.REVEALED
    }

    private fun Photo.checkIfFound() {
        if (lastRevealedIndex >= 0) {
            val lastRevealedPhoto = _state.value.photos.value[lastRevealedIndex]

            if (lastRevealedPhoto.name != name) {
                scope.launch {
                    _state.update { it.copy(status = UiState.Status.LOADING) }
                    delay(DELAY_TIME)

                    // close cards
                    val updatedList = _state.value.photos.value.updateRevealedStatus(lastRevealedPhoto.id)
                    val updatedListFinal = updatedList.updateRevealedStatus(id)

                    _state.update { it.copy(photos = _state.value.photos.copy(value = updatedListFinal)) }
                    lastRevealedIndex = -1
                    _state.update { it.copy(status = UiState.Status.PLAYING) }
                }
            } else {
                scope.launch {
                    _state.update { it.copy(status = UiState.Status.LOADING) }
                    delay(DELAY_TIME)

                    // match cards
                    val updatedList = _state.value.photos.value.updateRevealedStatus(lastRevealedPhoto.id, true)
                    val updatedListFinal = updatedList.updateRevealedStatus(id, true)

                    _state.update { it.copy(photos = _state.value.photos.copy(value = updatedListFinal)) }
                    lastRevealedIndex = -1
                    _state.update { it.copy(status = UiState.Status.PLAYING) }
                }
            }
        } else {
            lastRevealedIndex = _state.value.photos.value.findIndexFrom(id)
        }
    }

    fun onImagePicked(image: String, index: Int) {
        val updatedList = _state.value.photos.value.updateRes(index.toString(), image)

        _state.update { it.copy(photos = _state.value.photos.copy(value = updatedList)) }
    }

    private fun List<Photo>.updateRes(photoId: String, res: String): List<Photo> {
        val list = toMutableList()
        val photoToChange = list.find { it.id == photoId } ?: return this
        val indexToChange = list.indexOf(photoToChange)
        val newPhoto = photoToChange.copy(res = res)

        list[indexToChange] = newPhoto

        return list
    }
}