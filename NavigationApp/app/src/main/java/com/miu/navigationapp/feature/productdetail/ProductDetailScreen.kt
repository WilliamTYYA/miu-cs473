package com.miu.navigationapp.feature.productdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.navigationapp.data.Category
import com.miu.navigationapp.data.ProductRepositoryImpl
import com.miu.navigationapp.feature.productlist.ProductListViewModel

@Composable
fun ProductDetailScreen(productId: String,
                      modifier: Modifier = Modifier) {
    val viewModel: ProductDetailViewModel = viewModel {
        ProductDetailViewModel(ProductRepositoryImpl(), productId)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        ListItem(
            headlineContent = {
                Text(text=uiState.product?.name ?: "Loading")
            },
            supportingContent = {
                Text(text=uiState.product?.description ?: "Loading...")
            },
            trailingContent = {
                Text(text="$${uiState.product?.price ?: "Loading..."}")
            }
        )
    }
}