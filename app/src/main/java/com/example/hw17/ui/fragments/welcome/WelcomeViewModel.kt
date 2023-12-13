package com.example.hw17.ui.fragments.welcome

import androidx.lifecycle.ViewModel
import com.example.hw17.helpers.Constants.KEY_TOKEN
import com.example.hw17.helpers.UserDataStore
import kotlinx.coroutines.flow.flow

class WelcomeViewModel : ViewModel() {

    fun isUserLoggedIn() = flow {
        val token = UserDataStore.read(KEY_TOKEN)
        if (token != null) {
            emit(true)
        } else {
            emit(false)
        }
    }
}