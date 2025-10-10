package com.miu.navigationapp.feature.productlist

import com.miu.navigationapp.data.Product

data class ProductListUIState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
