package games

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ui.model.deal.DealList
import ui.model.deal.DealState

class DealGameEngine {

    private val _state: MutableStateFlow<DealState> = MutableStateFlow(DealState())
    val state: StateFlow<DealState> = _state.asStateFlow()

    fun onItemClick(id: Int) {
        _state.update { currentState ->
            val currentList = currentState.list.items.toMutableList()
            val clickedItem = currentList.first { it.id == id }
            val newItem = clickedItem.copy(opened = !clickedItem.opened)
            currentList[id] = newItem

            currentState.copy(list = DealList(currentList.toList()))
        }
    }
}
