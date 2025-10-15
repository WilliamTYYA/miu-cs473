package com.miu.datapersistence.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
//    val description: String,
    val price: Double,
    val quantity: Int
//    val imageUrl: String
)
