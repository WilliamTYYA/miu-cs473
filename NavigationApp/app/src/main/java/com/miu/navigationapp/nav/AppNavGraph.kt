package com.miu.navigationapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    // create a back stack object
    val backStack = rememberNavBackStack<AppNavKey>(Home)

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
        entryProvider = entryProvider {
            entry<Home> {

            }
            entry<ProductList> {

            }
            entry<ProductDetail> {

            }
            entry<Settings> {

            }
        },
        modifier = modifier
    )
}