package com.miu.lesson5_part2.data

class FakeRepository (
    alphabets: List<Pair<Char, String>> = listOf(
        'A' to "Apple",
        'B' to "Bravo",
        'C' to "Charlie"
    )
): AlphabetRepository by AlphabetRepositoryImp(alphabets)