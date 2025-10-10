package com.miu.navigationapp.data

class ProductRepositoryImpl: ProductRepository {
    private val products = listOf(
        Product(
            "1",
            "iPhone 17 Pro",
            "Apple Product",
            1100.0,
            Category.ELECTRONIC
        ),
        Product(
            "2",
            "Macbook Pro M3",
            "Apple Product",
            2000.0,
            Category.ELECTRONIC
        ),
        Product(
            "3",
            "iPhone Air",
            "Apple Product",
            1000.0,
            Category.ELECTRONIC
        ),
        Product(
            "4",
            "Polo T-Shirt",
            "T-Shirt",
            100.0,
            Category.CLOTHING
        ),
        Product(
            "5",
            "Living Water",
            "Reading Material",
            10.0,
            Category.BOOKS
        ),
        Product(
            "6",
            "Live your Life",
            "Motivation Reading",
            20.0,
            Category.BOOKS
        ),
    )

    /***
     * Returns a list of all the product categories for the Home Screen
     */
    override fun getProductCategories(): List<Category> = Category.entries.toList()

    /***
     * Return product detail by id for the ProductDetail Screen
     */
    override fun getProductById(id: String): Product? {
        return products.find { it.id == id }
    }

    /***
     * Return a list of products based on category for the ProductList Screen
     */
    override fun getProductByCategory(category: Category): List<Product> {
        return products.filter { it.category == category }
    }

}