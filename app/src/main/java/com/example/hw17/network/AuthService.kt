package com.example.hw17.network

import com.example.hw17.models.AuthRequestModel
import com.example.hw17.models.LoginResponseModel
import com.example.hw17.models.RegisterResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    suspend fun login(@Body body: AuthRequestModel): Response<LoginResponseModel>

    @POST("register")
    suspend fun register(@Body body: AuthRequestModel): Response<RegisterResponseModel>
}