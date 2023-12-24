package com.example.hw17.presentation.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw17.domain.models.AuthRequestModel
import com.example.hw17.domain.models.LoginResponseModel
import com.example.hw17.domain.useCase.LoginUseCase
import com.example.hw17.domain.useCase.SaveToDataStoreUseCase
import com.example.hw17.helpers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveToDataStoreUseCase: SaveToDataStoreUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginViewState())
    val loginState = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        resetViewState()
        viewModelScope.launch {
            _loginState.value = _loginState.value.copy(isLoading = true)
            loginUseCase(AuthRequestModel(email, password)).collect {
                when (it) {
                    is Resource.Error -> {
                        _loginState.value =
                            _loginState.value.copy(isLoading = false, error = it.message ?: "")
                    }

                    is Resource.Success -> {
                        _loginState.value =
                            _loginState.value.copy(isLoading = false, data = it.data)
                    }
                }
            }
        }
    }

    private fun resetViewState() {
        _loginState.value = LoginViewState()
    }

    data class LoginViewState(
        val data: LoginResponseModel? = null,
        val error: String = "",
        val isLoading: Boolean = false
    )

    suspend fun save(key: String, value: String) {
        saveToDataStoreUseCase(key, value)
    }
}