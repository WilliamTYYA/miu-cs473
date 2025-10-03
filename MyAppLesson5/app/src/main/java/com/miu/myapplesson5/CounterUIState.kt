package com.miu.myapplesson5

data class CounterUIState (
    val count: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isCompleted: Boolean = false,
)