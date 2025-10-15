package com.miu.fsapp.feature.userprofile.domain

import com.miu.fsapp.feature.userprofile.data.remote.dto.request.UserRequestDto
import com.miu.fsapp.feature.userprofile.data.remote.dto.response.UserResponseDto

interface UserRepository {
    suspend fun createUser(userRequestDto: UserRequestDto): Result<UserResponseDto>
    suspend fun getAllUsers(): Result<List<UserResponseDto>>
}