package com.miu.navigationapp.feature.productlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.navigationapp.data.Category
import com.miu.navigationapp.data.ProductRepositoryImpl

@Composable
fun ProductListScreen(category: Category,
                      navigateToProductDetail: (String) -> Unit,
                      modifier: Modifier = Modifier) {
    val viewModel: ProductListViewModel = viewModel {
        ProductListViewModel(ProductRepositoryImpl(), category)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LazyColumn {
        items(uiState.products) {
            ListItem(
                headlineContent = {
                    Text(text = it.name)
                },
                modifier = Modifier
                    .clickable {
                        navigateToProductDetail(it.id)
                    }
            )
        }
    }
}