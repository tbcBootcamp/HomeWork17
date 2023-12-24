package com.example.hw17.presentation.fragments.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw17.domain.models.AuthRequestModel
import com.example.hw17.helpers.Resource
import com.example.hw17.domain.models.RegisterResponseModel
import com.example.hw17.domain.useCase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterViewState())
    val registerState = _registerState.asStateFlow()

    fun register(email: String, password: String) {
        resetViewState()
        viewModelScope.launch {
            _registerState.value = _registerState.value.copy(isLoading = true)
            registerUseCase(AuthRequestModel(email, password)).collect {
                when (it) {
                    is Resource.Error -> {
                        _registerState.value =
                            _registerState.value.copy(isLoading = false, error = it.message ?: "")
                    }

                    is Resource.Success -> {
                        _registerState.value =
                            _registerState.value.copy(isLoading = false, data = it.data)
                    }
                }
            }
        }
    }

    private fun resetViewState() {
        _registerState.value = RegisterViewState()
    }

    data class RegisterViewState(
        val data: RegisterResponseModel? = null,
        val error: String = "",
        val isLoading: Boolean = false
    )
}