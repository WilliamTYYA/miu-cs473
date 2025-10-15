package com.miu.fsapp.feature.userprofile.data.remote.api

import com.miu.fsapp.feature.userprofile.data.remote.dto.request.UserRequestDto
import com.miu.fsapp.feature.userprofile.data.remote.dto.response.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("users")
    suspend fun createUser(@Body user: UserRequestDto): UserResponseDto
    @GET("users")
    suspend fun getUsers(): List<UserResponseDto>
}