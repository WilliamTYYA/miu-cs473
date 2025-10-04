package com.miu.lesson5_part2.data

class AlphabetRepositoryImp(
    private val alphabetData: List<Pair<Char, String>> = AlphabetData.alphabetData
): AlphabetRepository {
    override fun getAlphabetData(): List<Pair<Char, String>> = alphabetData

    override fun getNextAlphabet(currentAlphabet: Pair<Char, String>): Pair<Char, String> {
        val alphabetData = getAlphabetData()
        val currentIndex = alphabetData.indexOf(currentAlphabet)
        if (currentIndex < getAlphabetData().size - 1) {
            return alphabetData[currentIndex + 1]
        } else {
            return alphabetData.first()
        }
    }
}