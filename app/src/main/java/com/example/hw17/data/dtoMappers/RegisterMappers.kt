package com.example.hw17.data.dtoMappers

import com.example.hw17.data.dto.RegisterDto
import com.example.hw17.domain.models.RegisterResponseModel


fun RegisterDto.toDomain() = RegisterResponseModel(id, token)