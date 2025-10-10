package com.miu.navigationapp.feature.home

import com.miu.navigationapp.data.Category

data class HomeUIState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
