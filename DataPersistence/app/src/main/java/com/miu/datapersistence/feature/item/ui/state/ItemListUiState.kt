package com.miu.datapersistence.feature.item.ui.state

import com.miu.datapersistence.core.database.entity.Item

data class ItemListUiState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
