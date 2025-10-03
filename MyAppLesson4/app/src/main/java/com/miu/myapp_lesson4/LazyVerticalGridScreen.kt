package com.miu.myapp_lesson4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miu.myapp_lesson4.data.DataSource

@Composable
fun LazyVerticalGridScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .padding(innerPadding)
        ) {
            items(DataSource.loadData()) {
                Image(
                    painter = painterResource(id = it.image),
                    contentDescription = stringResource(id = it.title),
                    modifier = Modifier
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun LazyVerticalGridScreenPreview() {
    LazyVerticalGridScreen()
}