package ui.model

data class UiState(
    val status: Status = Status.PLAYING,
    val photos: Photos = Photos(emptyList()),
) {

    enum class Status {

        PLAYING,
        LOADING,
    }
}