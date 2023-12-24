package com.example.hw17.domain.useCase

import com.example.hw17.domain.models.AuthRequestModel
import com.example.hw17.domain.models.LoginResponseModel
import com.example.hw17.domain.repository.AuthRepository
import com.example.hw17.helpers.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(authRequestModel: AuthRequestModel): Flow<Resource<LoginResponseModel>> {
        return authRepository.login(authRequestModel)
    }
}
