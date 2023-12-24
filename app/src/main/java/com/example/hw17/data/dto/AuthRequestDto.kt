package com.example.hw17.data.dto

import com.example.hw17.domain.models.AuthRequestModel

data class AuthRequestDto(
    val email: String,
    val password: String
)
