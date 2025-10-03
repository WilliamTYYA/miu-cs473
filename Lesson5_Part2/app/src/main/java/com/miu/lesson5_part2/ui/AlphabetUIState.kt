package com.miu.lesson5_part2.ui

data class AlphabetUIState(
    val alphabet: Char,
    val word: String,
    val isCompleted: Boolean = false,
//    val isLoading: Boolean = false,
//    val isError: String? = null
)
