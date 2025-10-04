package com.miu.lesson5_part2.ui.test

import com.miu.lesson5_part2.data.FakeRepository
import com.miu.lesson5_part2.ui.AlphabetViewModel
import org.junit.Before
import org.junit.Test

class AlphabetViewModelText {
    // Initialize later
    private lateinit var alphabetviewModel: AlphabetViewModel

    @Before
    fun setup() {
        alphabetviewModel = AlphabetViewModel(FakeRepository())
    }

    @Test
    fun testInitialUIState() {
        val uiState = alphabetviewModel.alphabetUIState.value
        assert(uiState.alphabet == 'A') //true
        assert(uiState.word == "Apple") //true
        assert(!uiState.isCompleted) //is it not completed? =? true
    }

    @Test
    fun testAlphabetUIStateAfterNextAlphabet() {
        alphabetviewModel.nextAlphabet()
        val uiState = alphabetviewModel.alphabetUIState.value
        assert(uiState.alphabet == 'B') //true
        assert(uiState.word == "Bravo") //true
        assert(!uiState.isCompleted)
    }

    @Test
    fun testAlphabetUIStateAfterNextAlphabetCompleted() {
        repeat(2) {
            alphabetviewModel.nextAlphabet()
        }
        val uiState = alphabetviewModel.alphabetUIState.value
        assert(uiState.alphabet == 'C') //true
        assert(uiState.word == "Charlie") //true
        assert(uiState.isCompleted)
    }
}