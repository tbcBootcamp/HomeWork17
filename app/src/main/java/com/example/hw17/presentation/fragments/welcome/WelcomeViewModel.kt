package com.example.hw17.presentation.fragments.welcome

import androidx.lifecycle.ViewModel
import com.example.hw17.domain.useCase.ReadFromDataStoreUseCase
import com.example.hw17.helpers.Constants.KEY_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val readFromDataStoreUseCase: ReadFromDataStoreUseCase
) : ViewModel() {

    fun isUserLoggedIn() = flow {
        val token = readFromDataStoreUseCase(KEY_TOKEN)
        if (token != null) {
            emit(true)
        } else {
            emit(false)
        }
    }
}