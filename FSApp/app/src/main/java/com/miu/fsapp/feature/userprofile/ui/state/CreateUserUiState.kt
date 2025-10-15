package com.miu.fsapp.feature.userprofile.ui.state

import com.miu.fsapp.feature.userprofile.data.remote.dto.request.ProfileRequestDto
import com.miu.fsapp.feature.userprofile.data.remote.dto.request.UserRequestDto
import com.miu.fsapp.feature.userprofile.data.remote.dto.response.UserResponseDto

data class CreateUserUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userRequestDto: UserRequestDto = UserRequestDto(
        username = "",
        password = "",
        profileRequestDto = ProfileRequestDto(
            firstName = "",
            lastName = "",
            email = "",
            phoneNumber = "",
            bio = "",
            dateOfBirth = ""
        )
    ),
    val userResponseDto: UserResponseDto? = null
) {
    companion object {
        val Empty = CreateUserUiState()
    }
}
