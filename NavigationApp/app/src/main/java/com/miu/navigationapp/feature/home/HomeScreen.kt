package com.miu.navigationapp.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.navigationapp.data.Category
import com.miu.navigationapp.data.ProductRepository
import com.miu.navigationapp.data.ProductRepositoryImpl
import com.miu.navigationapp.nav.ProductList

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               navigateToProductList: (Category) -> Unit,
               navigateToSettings: () -> Unit) {
    val viewModel: HomeViewModel = viewModel {
        HomeViewModel(ProductRepositoryImpl())
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn {
        items(uiState.categories) { category ->
//            Text(text=it.name)
            ListItem(
                headlineContent = {
                    Text(category.name)
                },
                modifier = Modifier
                    .clickable {
                        // add ProductList key to backstack
                        navigateToProductList(category)
                    }
//                trailingContent = {
//                    Image(
//                        imageVector = Icons.Default.Face,
//                        contentDescription = "Face"
//                    )
//                }
            )
            HorizontalDivider()
        }
        item {
            ListItem(
                headlineContent = {
                    Text(text = "Settings")
                },
                modifier = Modifier
                    .clickable {
                        navigateToSettings()
                    }
            )
        }
    }
}