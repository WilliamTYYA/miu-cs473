package com.miu.datapersistence.feature.item.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.datapersistence.core.database.entity.Item
import com.miu.datapersistence.feature.item.domain.ItemRepository
import com.miu.datapersistence.feature.item.ui.state.ItemEditorUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemEditorViewModel(
    private val itemRepository: ItemRepository
): ViewModel() {
    private val _ItemEditorUiState = MutableStateFlow(ItemEditorUiState.Empty)
    val itemEditorUiState = _ItemEditorUiState.asStateFlow()

    fun updateItemName(name: String) {
        _ItemEditorUiState.update {
            it.copy(name = name)
        }
    }

    fun updateItemPrice(price: String) {
        _ItemEditorUiState.update {
            it.copy(price = price)
        }
    }

    fun updateQuantity(quantity: String) {
        _ItemEditorUiState.update {
            it.copy(quantity = quantity)
        }
    }

    fun insertItem() {
        viewModelScope.launch {
            _ItemEditorUiState.update {
                it.copy(isLoading = true)
            }
            val result = withContext(Dispatchers.IO) {
                itemRepository.insertItem(
                    Item(
                        name = _ItemEditorUiState.value.name,
                        price = _ItemEditorUiState.value.price.toDouble(),
                        quantity = _ItemEditorUiState.value.quantity.toInt()
                    )
                )
            }

            result.onSuccess {
                _ItemEditorUiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }
            }.onFailure { error ->
                _ItemEditorUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }
        }
    }

    fun updateItem() {
        viewModelScope.launch {
            _ItemEditorUiState.update {
                it.copy(isLoading = true)
            }
            val result = withContext(Dispatchers.IO) {
                itemRepository.updateItem(
                    Item(
                        id = _ItemEditorUiState.value.id,
                        name = _ItemEditorUiState.value.name,
                        price = _ItemEditorUiState.value.price.toDouble(),
                        quantity = _ItemEditorUiState.value.quantity.toInt()
                    )
                )
            }

            result.onSuccess {
                _ItemEditorUiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }
            }.onFailure { error ->
                _ItemEditorUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }
        }
    }

    fun resetCreateUiState() {
        _ItemEditorUiState.update {
            ItemEditorUiState.Empty
        }
    }

    fun setCurrentItemUiState(item: Item) {
        _ItemEditorUiState.update {
            it.copy(
                id = item.id,
                name = item.name,
                price = item.price.toString(),
                quantity = item.quantity.toString()
            )
        }
    }
}
