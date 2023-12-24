package com.example.hw17.data.network

import com.example.hw17.data.dto.AuthRequestDto
import com.example.hw17.data.dto.LoginDto
import com.example.hw17.data.dto.RegisterDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    suspend fun login(@Body body: AuthRequestDto): Response<LoginDto>

    @POST("register")
    suspend fun register(@Body body: AuthRequestDto): Response<RegisterDto>
}