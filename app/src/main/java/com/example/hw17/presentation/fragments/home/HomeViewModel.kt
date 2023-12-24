package com.example.hw17.presentation.fragments.home

import androidx.lifecycle.ViewModel
import com.example.hw17.domain.useCase.ReadFromDataStoreUseCase
import com.example.hw17.domain.useCase.RemoveFromDataStoreUseCase
import com.example.hw17.helpers.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val removeFromDataStoreUseCase: RemoveFromDataStoreUseCase,
    private val readFromDataStoreUseCase: ReadFromDataStoreUseCase
) : ViewModel() {

    suspend fun remove(key: String) {
        removeFromDataStoreUseCase(key)
    }

    fun readEmail() = flow {
        emit(readFromDataStoreUseCase(Constants.KEY_EMAIL))
    }
}