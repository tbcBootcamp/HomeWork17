package com.example.hw17.domain.useCase

import com.example.hw17.domain.repository.UserDataStoreRepository
import javax.inject.Inject



class RemoveFromDataStoreUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend operator fun invoke(key: String) {
        userDataStoreRepository.remove(key)
    }
}