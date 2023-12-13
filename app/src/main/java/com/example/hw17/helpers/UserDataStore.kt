package com.example.hw17.helpers

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.hw17.App
import kotlinx.coroutines.flow.first
import java.util.concurrent.CancellationException


object UserDataStore {

    private val Application.store: DataStore<Preferences> by preferencesDataStore(name = Constants.NAME)

    suspend fun save(key: String, value: String) {
        try {
            App.context.store.edit {
                it[stringPreferencesKey(key)] = value
            }
        } catch (e: CancellationException) {
            println(e.message.toString())
        }
    }

    suspend fun remove(key: String) {
        App.context.store.edit {
            it.remove(stringPreferencesKey(key))
        }
    }

    suspend fun read(key: String): String? {
        return App.context.store.data.first()[stringPreferencesKey(key)]
    }

}