package com.miu.datapersistence.feature.item.data

import com.miu.datapersistence.core.database.dao.ItemDao
import com.miu.datapersistence.core.database.entity.Item
import com.miu.datapersistence.feature.item.domain.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val itemDao: ItemDao
): ItemRepository {
    override suspend fun insertItem(item: Item): Result<Unit> {
        return runCatching {
            itemDao.insertItem(item)
        }
    }

    override suspend fun updateItem(item: Item): Result<Unit> {
        return runCatching {
            itemDao.updateItem(item)
        }
    }

    override suspend fun deleteItem(item: Item): Result<Unit> {
        return runCatching {
            itemDao.deleteItem(item)
        }
    }

    override fun getAllItems(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItem(id: Int): Flow<Item> = itemDao.getItem(id)
}