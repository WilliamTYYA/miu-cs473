package com.miu.lesson5_part2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.lesson5_part2.data.AlphabetRepository
import com.miu.lesson5_part2.data.AlphabetRepositoryImp
import com.miu.lesson5_part2.ui.theme.Lesson5_Part2Theme

@Composable
fun AlphabetExpScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Column(
            modifier.padding(innerPadding)
        ) {
            //Get view model instance
//            val alphabetViewModel: AlphabetViewModel = viewModel {
//                AlphabetViewModel(AlphabetRepositoryImp())
//            }
            val alphabetViewModel: AlphabetViewModel = hiltViewModel()
            val alphabetUIState = alphabetViewModel.alphabetUIState.collectAsState()

            Text(text = "${alphabetUIState.value.alphabet} : ${alphabetUIState.value.word}")
            Button(
                onClick = {
                    alphabetViewModel.nextAlphabet()
                },
            ) {
                Text(text = "Next")
            }

            when {
                alphabetUIState.value.isCompleted -> {
                    Text(text="It's completed!")
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun AlphabetExpScreenPreview() {
    Lesson5_Part2Theme {
        AlphabetExpScreen()
    }
}