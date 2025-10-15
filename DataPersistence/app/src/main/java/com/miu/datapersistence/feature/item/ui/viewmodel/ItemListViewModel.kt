package com.miu.datapersistence.feature.item.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.datapersistence.core.database.entity.Item
import com.miu.datapersistence.feature.item.domain.ItemRepository
import com.miu.datapersistence.feature.item.ui.state.ItemListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemListViewModel(
    private val itemRepository: ItemRepository
): ViewModel() {
    val itemListUiState: StateFlow<ItemListUiState> = itemRepository.getAllItems()
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .map { items: List<Item> -> //Flow
                    ItemListUiState(
                        items = items
                    )
                }
                .onStart { //Flow<ItemListUiState>
                    ItemListUiState(
                        isLoading = true
                    )
                }
                .catch { exception ->
                    ItemListUiState(
                        errorMessage = exception.message
                    )
                }
                .stateIn( //its StateFlow
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = ItemListUiState()
                )

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                itemRepository.deleteItem(item)
            }
        }
    }
}