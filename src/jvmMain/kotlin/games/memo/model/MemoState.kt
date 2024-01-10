package games.memo.model

data class MemoState(
    val status: Status = Status.PLAYING,
    val photos: Photos = Photos(emptyList()),
) {

    enum class Status {

        PLAYING,
        LOADING,
    }
}