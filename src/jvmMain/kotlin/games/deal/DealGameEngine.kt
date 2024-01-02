package games.deal

import GameEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import games.deal.model.DealItem
import games.deal.model.DealList
import games.deal.model.DealState

class DealGameEngine : GameEngine {

    private val _state: MutableStateFlow<DealState> = MutableStateFlow(DealState())
    val state: StateFlow<DealState> = _state.asStateFlow()

    fun onKeyPressed(key: Int, type: DealItem.Type) {
        try {
            println("Key pressed: $key, type: $type")
        } catch (e: Exception) {
            println(e)
        }
    }

    fun onItemClick(id: String) {
        _state.update { currentState ->
            val currentList = currentState.list.items.toMutableList()
            val clickedItem = currentList.first { it.id == id }
            val clickedItemPosition = currentList.indexOf(clickedItem)
            val newItem = clickedItem.copy(opened = !clickedItem.opened)
            currentList[clickedItemPosition] = newItem

            currentState.copy(list = DealList(currentList))
        }
    }

    fun onAddClick(item: DealItem) {
        _state.update { currentState ->
            val currentList = currentState.list.items.toMutableList()

            println("Remove: $currentList")
            currentList.add(item)

            currentState.copy(list = DealList(currentList))
        }
    }

    fun onDeleteClick(id: String) {
        _state.update { currentState ->
            val newList = currentState.list.items.toMutableList()
            newList.removeIf { it.id == id }

            println("Remove: $newList")

            currentState.copy(list = DealList(newList))
        }
    }
}
