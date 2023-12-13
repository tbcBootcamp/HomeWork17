package com.example.hw17.repository

import com.example.hw17.helpers.Resource
import com.example.hw17.models.AuthRequestModel
import com.example.hw17.network.RetrofitClient
import kotlinx.coroutines.flow.flow

object AuthRepository {
    fun register(email: String, password: String) = flow {
        try {
            val response =
                RetrofitClient.authService.register(AuthRequestModel(email, password))
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

    fun login(email: String, password: String) = flow {
        try {
            val response =
                RetrofitClient.authService.login(AuthRequestModel(email, password))
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }
}