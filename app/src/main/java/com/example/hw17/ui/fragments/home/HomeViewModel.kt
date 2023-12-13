package com.example.hw17.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw17.helpers.Constants
import com.example.hw17.helpers.UserDataStore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {


    suspend fun remove(key: String) {
        UserDataStore.remove(key)

    }

    fun readEmail() = flow {
        emit(UserDataStore.read(Constants.KEY_EMAIL))
    }
}