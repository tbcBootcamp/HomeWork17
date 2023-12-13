package com.example.hw17.ui.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw17.helpers.Resource
import com.example.hw17.helpers.UserDataStore
import com.example.hw17.models.AuthRequestModel
import com.example.hw17.models.LoginResponseModel
import com.example.hw17.models.RegisterResponseModel
import com.example.hw17.network.RetrofitClient
import com.example.hw17.repository.AuthRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

//    private val _loginState = MutableStateFlow<Resource<LoginResponseModel>>(Resource.Success())
//    val loginState = _loginState.asStateFlow()

    private val _loginState = MutableStateFlow(LoginViewState())
    val loginState = _loginState.asStateFlow()

    //    fun login(email: String, password: String) {
////        _loginState.value = Resource.reset()
//
//        viewModelScope.launch {
//            _loginState.value = Resource.Loader(true)
//
//            try {
//                val response =
//                    RetrofitClient.authService.login(AuthRequestModel(email = email, password = password))
//                when {
//                    response.isSuccessful -> {
//                        val body = response.body()
//                        _loginState.value = Resource.Success(body)
//                    }
//                    else -> {
//                        val errorBody = response.errorBody()
//                        _loginState.value = Resource.Error(errorBody?.string() ?: "Unknown error")
//                    }
//                }
//            } catch (e: Throwable) {
//                _loginState.value = Resource.Error(message = e.message ?: "Unknown error")
//            } finally {
//                _loginState.value = Resource.Loader(false)
//            }
//        }
//    }
    fun login(email: String, password: String) {
        resetViewState()
        viewModelScope.launch {
            _loginState.value = _loginState.value.copy(isLoading = true)
            AuthRepository.login(email, password).collect {
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
        UserDataStore.save(key, value)
    }
}