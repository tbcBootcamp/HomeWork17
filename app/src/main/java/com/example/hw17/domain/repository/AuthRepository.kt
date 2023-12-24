package com.example.hw17.domain.repository

import com.example.hw17.domain.models.AuthRequestModel
import com.example.hw17.helpers.Resource
import com.example.hw17.domain.models.LoginResponseModel
import com.example.hw17.domain.models.RegisterResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun register(authRequestModel: AuthRequestModel): Flow<Resource<RegisterResponseModel>>
    suspend fun login(authRequestModel: AuthRequestModel): Flow<Resource<LoginResponseModel>>
}