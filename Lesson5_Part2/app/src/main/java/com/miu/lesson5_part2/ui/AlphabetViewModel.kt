package com.miu.lesson5_part2.ui

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.lesson5_part2.data.AlphabetData
import com.miu.lesson5_part2.data.AlphabetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlphabetViewModel @Inject constructor (
    private val alphabetRepository: AlphabetRepository
): ViewModel() {
//    private val alphabetData = AlphabetData.alphabetData
    private val alphabetData = alphabetRepository.getAlphabetData()

    // create MutableStateFlow
    private val _alphabetUIState = MutableStateFlow(
        AlphabetUIState(
            alphabet = alphabetData[0].first,
            word = alphabetData[0].second
        )
    )

    //Expose it as read only state
    val alphabetUIState = _alphabetUIState.asStateFlow()

    fun nextAlphabet() {
        viewModelScope.launch {
            // set loading as true
            _alphabetUIState.update {
                it.copy(
                    isLoading = true
                )
            }
            val nextAlphabet =
                alphabetRepository.getNextAlphabet(_alphabetUIState.value.alphabet to _alphabetUIState.value.word)
            if (nextAlphabet == alphabetData.last()) {
                _alphabetUIState.update {
                    it.copy(
                        nextAlphabet.first,
                        nextAlphabet.second,
                        isCompleted = true,
                        isLoading = false
                    )
                }
            } else {
                _alphabetUIState.update {
                    it.copy(
                        nextAlphabet.first,
                        nextAlphabet.second,
                        isCompleted = false,
                        isLoading = false
                    )
                }
            }
        }
//        val currentIndex = alphabetData.indexOfFirst { it.first == _alphabetUIState.value.alphabet }
//        val currentIndex = alphabetData.indexOf(_alphabetUIState.value.alphabet to _alphabetUIState.value.word)
//        if (currentIndex < alphabetData.size - 1) {
//            val nextAlphabet = alphabetData[currentIndex + 1]
//            _alphabetUIState.update {
//                it.copy(
//                    alphabet = nextAlphabet.first,
//                    word = nextAlphabet.second
//                )
//            }
//        } else {
//            _alphabetUIState.update {
//                it.copy(
//                    alphabet = alphabetData[0].first,
//                    word = alphabetData[0].second
//                )
//            }
//        }
//
//        if (currentIndex == alphabetData.size - 2) {
//            _alphabetUIState.update {
//                it.copy(
//                    isCompleted = true
//                )
//            }
//        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}