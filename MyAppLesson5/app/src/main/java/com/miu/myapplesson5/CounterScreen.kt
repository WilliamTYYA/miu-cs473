package com.miu.myapplesson5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            val counterViewModel: CounterViewModel = viewModel()
            val counterViewModel: CounterViewModel = viewModel {
                CounterViewModel()
            }
            val counterUIState: CounterUIState by counterViewModel.counterUIState.collectAsState()
            Text(text="${counterUIState.count}")

            Spacer(modifier=Modifier.padding(20.dp))

            Row {
                Button(
                    onClick = { counterViewModel.decrement() }
                ) {
                    Text(text = "Decrement")
                }
                Spacer(modifier=Modifier.padding(10.dp))
                Button(
                    onClick = { counterViewModel.increment() }
                ) {
                    Text(text = "Increment")
                }
            }

            when {
                counterUIState.isLoading -> {
                    CircularProgressIndicator()
                }

                counterUIState.isLoading -> {
                    Text(text = "Completed")
                }

                counterUIState.errorMessage != null -> {
                    Text(text = counterUIState.errorMessage!!)
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun CounterScreenPreview() {
    CounterScreen()
}