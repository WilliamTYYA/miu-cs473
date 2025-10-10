package com.miu.navigationapp.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.miu.navigationapp.data.Category
import com.miu.navigationapp.data.Setting
import com.miu.navigationapp.feature.home.HomeScreen
import com.miu.navigationapp.feature.productdetail.ProductDetailScreen
import com.miu.navigationapp.feature.productlist.ProductListScreen
import com.miu.navigationapp.feature.setting.SettingsScreen

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {

    // create a back stack object
    val backStack = rememberNavBackStack<AppNavKey>(Home)

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    label = { Text("Home") },
                    selected = backStack.lastOrNull() == Home,
                    onClick = {
                        backStack.clear()
                        backStack.add(Home)
                    },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (backStack.lastOrNull() == ProductList(Category.ELECTRONIC)) {
                                    Badge {
                                        val badgeNumber = "999+"
                                        Text(
                                            badgeNumber
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                        }
                    }
                )
                NavigationBarItem(
                    label = { Text("Settings") },
                    selected = backStack.lastOrNull() == Settings,
                    onClick = {
                        backStack.clear()
                        backStack.add(Settings)
                    },
                    icon = {
                        Icon(Icons.Default.Settings, contentDescription = "Home")
                    }
                )
            }
        }
    ) { innerPadding ->

        fun navigateToProductList(category: Category) {
            backStack.add(ProductList(category))
        }

        fun navigateToProductDetail(id: String) {
            backStack.add(ProductDetail(id))
        }

        fun navigateToSetting() {
            backStack.add(Settings)
        }

        NavDisplay(
            backStack = backStack,
            onBack  = {backStack.removeLastOrNull()},
//        entryProvider = { key ->
//            when(key) {
//                is Home -> NavEntry(Home) {
//                    // content for home goes here // HomeScreen()
//                }
//                is ProductList -> NavEntry(ProductList(key.category)) {
//
//                }
//                is ProductDetail -> NavEntry(ProductDetail(key.productId)) {
//
//                }
//                is Settings -> NavEntry(Settings) {
//
//                }
//                else -> error("Unknown key: $key")
//            }
//        },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Home> {
                    HomeScreen(
                        navigateToProductList = ::navigateToProductList,
                        navigateToSettings = {
                            backStack.add(Settings)
                        },
                        modifier = modifier
                    )
                }
                entry<ProductList> { productList: ProductList ->
                    val category: Category = productList.category
                    ProductListScreen(
                        category = category,
                        navigateToProductDetail = ::navigateToProductDetail,
                        modifier = modifier
                    )
                }
                entry<ProductDetail> { productDetail: ProductDetail ->
                    var id: String = productDetail.productId
                    ProductDetailScreen(
                        id,
                        modifier
                    )
                }
                entry<Settings> {
                    SettingsScreen(modifier)
                }
            },
            modifier = modifier
                .padding(innerPadding)
        )
    }
}