package com.example.hw17.domain.useCase

import com.example.hw17.domain.repository.UserDataStoreRepository
import javax.inject.Inject

class SaveToDataStoreUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(key: String, value: String) {
        userDataStoreRepository.save(key, value)
    }
}