package com.flutterjunction.junctionshop.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.flutterjunction.junctionshop.Graph
import com.flutterjunction.junctionshop.data.room.models.ItemsWithStoreAndList
import com.flutterjunction.junctionshop.ui.Category
import com.flutterjunction.junctionshop.ui.Utils
import com.flutterjunction.junctionshop.ui.repository.Repository


import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.flutterjunction.junctionshop.data.room.models.Item
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository = Graph.repository) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            repository.getItemsWithListAndStore.collectLatest {
                state = state.copy(items = it)
            }
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun onCategoryChanged(category: Category) {
        state = state.copy(category = category)
        filterBy(category.id)
    }

    private fun filterBy(shoppingListId: Int) {
        if (shoppingListId != 10001) {
            viewModelScope.launch {
                repository.getItemsWithStoreAndListFilteredById(
                    shoppingListId
                )
                    .collectLatest {
                        state = state.copy(items = it)
                    }
            }
        } else {
            getItems()
        }
    }

    fun onItemCheckedChanged(item: Item, isChecked: Boolean) {
        viewModelScope.launch {
            repository.updateItem(
                item = item.copy(
                    isChecked = isChecked
                )
            )
        }
    }

}

data class HomeState(
    val items: List<ItemsWithStoreAndList> = emptyList(),
    val category: Category = Utils.category.last(),
    val itemChecked: Boolean = false
)