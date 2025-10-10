package com.miu.navigationapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.navigationapp.data.Category
import com.miu.navigationapp.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val productRepository: ProductRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            //update isLoading status to true
            _uiState.update {
                it.copy(isLoading = true)
            }

            // fetch categories from repository
            runCatching {
                productRepository.getProductCategories()
            }.onSuccess { categories: List<Category> ->
                // update isLoading status to false
                _uiState.update {
                    it.copy(categories = categories, isLoading = false, errorMessage = null)
                }
            }.onFailure { error: Throwable ->
                // update isLoading status to false and set errorMessage
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = error.message)
                }
            }
        }
    }
}