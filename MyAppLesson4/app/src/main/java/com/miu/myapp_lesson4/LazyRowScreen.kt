package com.miu.myapp_lesson4

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miu.myapp_lesson4.data.DataSource

@Composable
fun LazyRowScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        LazyRow(
            modifier = modifier.padding(innerPadding)
        ) {
            items(DataSource.loadData()) { item ->
                Card(
                    modifier = Modifier.
                    padding(end = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id=item.image),
                        contentDescription = stringResource(id=item.title),
                        modifier = Modifier
                            .fillMaxWidth()
//                            .width(400.dp)
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = stringResource(id=item.title)
                    )
                }
            }
//            item {
//                Text("MIU")
//            }
//
//            items (
//                 listOf("Android", "Kotlin", "Swift", "Java")
//            ) { item ->
//                Text(text = item)
//            }
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun LazyRowScreenPreview() {
    LazyRowScreen()
}