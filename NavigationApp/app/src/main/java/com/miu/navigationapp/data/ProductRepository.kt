package com.miu.navigationapp.data

interface ProductRepository {
    fun getProductCategories(): List<Category>
    fun getProductById(id: String): Product?
    fun getProductByCategory(category: Category): List<Product>
}