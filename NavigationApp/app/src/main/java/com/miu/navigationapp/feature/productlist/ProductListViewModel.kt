package com.miu.navigationapp.feature.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.navigationapp.data.Category
import com.miu.navigationapp.data.Product
import com.miu.navigationapp.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class ProductListViewModel(
    private val productRepository: ProductRepository,
    private val category: Category
): ViewModel() {
    private val _uiState = MutableStateFlow(ProductListUIState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProductByCategory()
    }

    fun loadProductByCategory() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
        }

        runCatching {
            productRepository.getProductByCategory(category)
        }.onSuccess { products: List<Product> ->
            _uiState.update {
                it.copy(
                    products = products, // do not put it.product
                    isLoading = false,
                    errorMessage = null
                )
            }

        }.onFailure { error: Throwable ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = error.message
                )
            }
        }
    }
}