package com.miu.myapplesson5

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel: ViewModel() {
    private val _counterUIState = MutableStateFlow(CounterUIState(count = 0, isLoading = false)) // emits the data
    val counterUIState = _counterUIState.asStateFlow()

    fun increment() {
        _counterUIState.update { counterUIState ->
            counterUIState.copy(
                count = counterUIState.count + 1
            )
        }
    }

    fun decrement() {
        if (counterUIState.value.count == 0) {
            _counterUIState.update { counterUIState ->
                counterUIState.copy(
                    errorMessage = "Can't decrement below 0"
                )
            }
        } else {
            _counterUIState.update { counterUIState ->
                counterUIState.copy(
                    count = counterUIState.count - 1
                )
            }
        }
    }
}