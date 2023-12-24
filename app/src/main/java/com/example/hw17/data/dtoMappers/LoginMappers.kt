package com.example.hw17.data.dtoMappers

import com.example.hw17.data.dto.LoginDto
import com.example.hw17.domain.models.LoginResponseModel

fun LoginDto.toDomain() = LoginResponseModel(token)