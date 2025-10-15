package com.miu.fsapp.feature.userprofile.ui.state

import com.miu.fsapp.feature.userprofile.data.remote.dto.response.UserResponseDto

data class UserListUiState(
    val users: List<UserResponseDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)