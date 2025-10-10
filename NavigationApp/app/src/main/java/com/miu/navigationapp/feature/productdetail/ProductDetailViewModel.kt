package com.miu.navigationapp.feature.productdetail

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

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val productId: String
): ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailUIState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProductDetail()
    }

    fun loadProductDetail() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            runCatching {
                productRepository.getProductById(productId)
            }.onSuccess { product: Product? ->
                _uiState.update {
                    it.copy(
                        product = product,
                        isLoading = true,
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
}