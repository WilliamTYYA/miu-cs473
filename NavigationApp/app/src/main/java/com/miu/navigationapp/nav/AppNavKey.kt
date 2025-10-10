package com.miu.navigationapp.nav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import com.miu.navigationapp.data.Category

interface AppNavKey: NavKey
@Serializable
object Home: AppNavKey
@Serializable
data class ProductList(val category: Category): AppNavKey // Modify later => Enum
@Serializable
data class ProductDetail(val productId: String): AppNavKey
@Serializable
object Settings: AppNavKey

//@Serializable
//sealed interface AppNavKey {
//    object Home: AppNavKey
//    data class ProductList(val categoryId: Int): AppNavKey // Modify later => Enum
//    data class ProductDetail(val productId: Int): AppNavKey
//    object Settings: AppNavKey
//}