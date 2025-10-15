package com.miu.fsapp.feature.userprofile.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class UserResponseDto(
    val username: String,
// val profileResponseDto: ProfileResponseDto
    @SerializedName("profileResponseDto")
    val profile: ProfileResponseDto
)