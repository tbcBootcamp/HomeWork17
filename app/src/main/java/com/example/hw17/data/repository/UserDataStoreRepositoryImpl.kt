package com.example.hw17.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.hw17.domain.repository.UserDataStoreRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class UserDataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) :
    UserDataStoreRepository {
    override suspend fun save(key: String, value: String) {
        dataStore.edit { it[stringPreferencesKey(key)] = value }
    }

    override suspend fun remove(key: String) {
        dataStore.edit { it.remove(stringPreferencesKey(key)) }
    }

    override suspend fun read(key: String): String? {
        return dataStore.data.firstOrNull()?.get(stringPreferencesKey(key))
    }
}