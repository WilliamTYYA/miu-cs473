package com.miu.fsapp.feature.userprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.fsapp.feature.userprofile.data.remote.dto.response.UserResponseDto
import com.miu.fsapp.feature.userprofile.domain.UserRepository
import com.miu.fsapp.feature.userprofile.ui.state.UserListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class UserListViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _userListUiState = MutableStateFlow(UserListUiState())
    val userListUiState = _userListUiState.asStateFlow()

    init {
        getAllUsers()
    }
    fun getAllUsers() {
        // launch a coroutine using ViewModelScope
        viewModelScope.launch {
            _userListUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            //now, fetch the users: over the coroutine from main thread to worker thread
            val result: Result<List<UserResponseDto>> = withContext(Dispatchers.IO) {
                userRepository.getAllUsers()
            }
            result.onSuccess { users: List<UserResponseDto> ->
                        _userListUiState.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                users = users
                            )
                        }
                    }.onFailure { error: Throwable ->
                        _userListUiState.update {
                            it.copy(
                                isLoading = false,
                                error = error.message
                            )
                        }
                    }
        }
    }
}