package ui.model.deal

data class DealState (
    val list: DealList = initialList
)

val initialList = DealList(
    items = listOf(
        DealItem(id = 0, title = "prize 1", opened = false),
        DealItem(id = 1, title = "prize 2", opened = false),
        DealItem(id = 2, title = "prize 3", opened = false),
        DealItem(id = 3, title = "prize 4", opened = false),
        DealItem(id = 4, title = "prize 5", opened = false),
        DealItem(id = 5, title = "prize 6", opened = false),
    )
)
