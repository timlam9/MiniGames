package games.deal.model

data class DealItem(
    val id: String,
    val title: String,
    val opened: Boolean,
    val type: Type,
) {

    enum class Type {

        BLUE,
        RED
    }
}
