package com.miu.fsapp.feature.userprofile.data.remote.dto.request

data class UserRequestDto(
    val username: String,
    val password: String,
    val profileRequestDto: ProfileRequestDto
)