package com.miu.fsapp.feature.userprofile.data.remote.dto.request

data class ProfileRequestDto(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val email: String,
    val phoneNumber: String,
    val bio: String
)
