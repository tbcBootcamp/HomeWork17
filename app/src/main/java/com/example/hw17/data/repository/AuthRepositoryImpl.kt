package com.example.hw17.data.repository

import com.example.hw17.data.dto.AuthRequestDto
import com.example.hw17.data.dtoMappers.toDomain
import com.example.hw17.data.dtoMappers.toDto
import com.example.hw17.data.network.AuthService
import com.example.hw17.helpers.Resource
import com.example.hw17.domain.models.AuthRequestModel
import com.example.hw17.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {
    override suspend fun register(authRequestModel: AuthRequestModel) = flow {
        try {
            val response =
                authService.register(authRequestModel.toDto())
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.toDomain()))
            } else {
                emit(Resource.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

    override suspend fun login(authRequestModel: AuthRequestModel) = flow {
        try {
            val response =
                authService.login(authRequestModel.toDto())
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.toDomain()))
            } else {
                emit(Resource.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}