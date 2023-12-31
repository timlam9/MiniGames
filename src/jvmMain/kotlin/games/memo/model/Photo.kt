package games.memo.model

data class Photo(
    val id: String,
    val res: String? = null,
    val status: Status,
    val name: String,
    val defaultImage: String,
) {

    enum class Status {

        CLOSED,
        REVEALED,
        MATCHED,
    }

    fun isRevealed(): Boolean = status == Status.REVEALED

    fun isMatched(): Boolean = status == Status.MATCHED
}
