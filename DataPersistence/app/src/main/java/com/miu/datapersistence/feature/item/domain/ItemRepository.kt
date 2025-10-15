package com.miu.datapersistence.feature.item.domain

import com.miu.datapersistence.core.database.entity.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun insertItem(item: Item): Result<Unit>
    suspend fun updateItem(item: Item): Result<Unit>
    suspend fun deleteItem(item: Item): Result<Unit>
    fun getAllItems(): Flow<List<Item>>
    fun getItem(id: Int): Flow<Item>
}