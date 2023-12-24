package com.example.hw17.data.dtoMappers

import com.example.hw17.data.dto.AuthRequestDto
import com.example.hw17.domain.models.AuthRequestModel


fun AuthRequestModel.toDto() = AuthRequestDto(email, password)