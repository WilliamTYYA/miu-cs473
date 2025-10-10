package com.miu.navigationapp.feature.productdetail

import com.miu.navigationapp.data.Product

data class ProductDetailUIState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {

}
