package com.miu.lesson5_part2.ui.test

import com.miu.lesson5_part2.data.FakeRepository
import com.miu.lesson5_part2.ui.AlphabetViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class AlphabetViewModelText {
    // Initialize later
    private lateinit var alphabetviewModel: AlphabetViewModel
    private lateinit var testDispatcher: TestDispatcher

    @Before
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
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
    fun testAlphabetUIStateAfterNextAlphabet() = runTest {
        alphabetviewModel.nextAlphabet()
        advanceUntilIdle()
        val uiState = alphabetviewModel.alphabetUIState.value
        assert(uiState.alphabet == 'B') //true
        assert(uiState.word == "Bravo") //true
        assert(!uiState.isCompleted)
    }

    @Test
    fun testAlphabetUIStateAfterNextAlphabetCompleted() = runTest {
        repeat(2) {
            alphabetviewModel.nextAlphabet()
            advanceUntilIdle()
        }
        val uiState = alphabetviewModel.alphabetUIState.value
        assert(uiState.alphabet == 'C') //true
        assert(uiState.word == "Charlie") //true
        assert(uiState.isCompleted)
    }
}