package com.miu.myapplesson5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CounterWithMixedLogicScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            var count: MutableIntState = remember { mutableIntStateOf(0) }
            var count: Int by rememberSaveable { mutableIntStateOf(0) }
            Text(text="$count")

            Spacer(modifier=Modifier.padding(20.dp))

            Row {
                Button(
                    onClick = { count = decrement(count) }
                ) {
                    Text(text = "Decrement")
                }
                Spacer(modifier=Modifier.padding(10.dp))
                Button(
                    onClick = { count = increment(count) }
                ) {
                    Text(text = "Increment")
                }
            }
        }
    }
}

fun increment(count: Int) = count + 1
fun decrement(count: Int) = count - 1

@Preview(
    showSystemUi = true
)
@Composable
private fun CounterWithMixedLogicScreenPreview() {
    CounterWithMixedLogicScreen()
}