package com.miu.fsapp.feature.userprofile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.fsapp.feature.userprofile.data.remote.dto.response.UserResponseDto
import com.miu.fsapp.feature.userprofile.domain.UserRepository
import com.miu.fsapp.feature.userprofile.ui.state.CreateUserUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateUserViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _createUserUiState = MutableStateFlow<CreateUserUiState>(CreateUserUiState())
    val createUserUiState = _createUserUiState.asStateFlow()

    fun updateUserName(username: String) {
        _createUserUiState.update {
            it.copy(
                userRequestDto = it.userRequestDto.copy(
                    username = username
                )
            )
        }
    }

    fun updatePassword(password: String) {
        _createUserUiState.update {
            it.copy(
                userRequestDto = it.userRequestDto.copy(
                    password = password
                )
            )
        }
    }

    fun updateFirstName(firstName: String) {
        _createUserUiState.update {
            it.copy(
                userRequestDto = it.userRequestDto.copy(
                    profileRequestDto = it.userRequestDto.profileRequestDto.copy(
                        firstName = firstName
                    )
                )
            )
        }
    }

    fun updateLastName(lastName: String) {
        _createUserUiState.update {
            it.copy(
                userRequestDto = it.userRequestDto.copy(
                    profileRequestDto = it.userRequestDto.profileRequestDto.copy(
                        lastName = lastName
                    )
                )
            )
        }
    }

    fun updateDateOfBirth(dateOfBirth: String) {
        _createUserUiState.update {
            it.copy(
                userRequestDto = it.userRequestDto.copy(
                    profileRequestDto = it.userRequestDto.profileRequestDto.copy(
                        dateOfBirth = dateOfBirth
                    )
                )
            )
        }
    }

    fun updateEmail(email: String) {
        _createUserUiState.update {
            it.copy(
                userRequestDto = it.userRequestDto.copy(
                    profileRequestDto = it.userRequestDto.profileRequestDto.copy(
                        email = email
                    )
                )
            )
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _createUserUiState.update {
            it.copy(
                userRequestDto = it.userRequestDto.copy(
                    profileRequestDto = it.userRequestDto.profileRequestDto.copy(
                        phoneNumber = phoneNumber
                    )
                )
            )
        }
    }

    fun updateBio(bio: String) {
        _createUserUiState.update {
            it.copy(
                userRequestDto = it.userRequestDto.copy(
                    profileRequestDto = it.userRequestDto.profileRequestDto.copy(
                        bio = bio
                    )
                )
            )
        }
    }

    fun createUser() {
        viewModelScope.launch {
            _createUserUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            //now, fetch the users: over the coroutine from main thread to worker thread
            val result = withContext(Dispatchers.IO) {
                Log.d("CreateUserViewModel", "createUser: ${createUserUiState.value.userRequestDto}")
                userRepository.createUser(createUserUiState.value.userRequestDto)
            }
            result.onSuccess { userResponseDto ->
                _createUserUiState.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        userResponseDto = userResponseDto
                    )
                }
            }.onFailure { error: Throwable ->
                _createUserUiState.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
        }
    }

    fun resetUserUiState() {
        _createUserUiState.value = CreateUserUiState.Empty
    }
}