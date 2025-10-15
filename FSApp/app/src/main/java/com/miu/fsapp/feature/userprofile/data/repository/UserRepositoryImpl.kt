package com.miu.fsapp.feature.userprofile.data.repository

import com.miu.fsapp.feature.userprofile.data.remote.api.UserApiService
import com.miu.fsapp.feature.userprofile.data.remote.dto.request.UserRequestDto
import com.miu.fsapp.feature.userprofile.data.remote.dto.response.UserResponseDto
import com.miu.fsapp.feature.userprofile.domain.UserRepository

class UserRepositoryImpl(
    private val userApiService: UserApiService
): UserRepository {
    override suspend fun createUser(userRequestDto: UserRequestDto): Result<UserResponseDto> {
        return runCatching {
            userApiService.createUser(userRequestDto)
        }

    }

    override suspend fun getAllUsers(): Result<List<UserResponseDto>> {
        return runCatching {
            userApiService.getUsers()
        }
    }
}