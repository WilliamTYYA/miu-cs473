package com.miu.lesson5_part2.data

interface AlphabetRepository {
    fun getAlphabetData(): List<Pair<Char, String>>
    suspend fun getNextAlphabet(currentAlphabet: Pair<Char, String>): Pair<Char, String>
}