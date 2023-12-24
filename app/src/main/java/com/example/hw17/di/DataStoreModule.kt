package com.example.hw17.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.hw17.App
import com.example.hw17.helpers.Constants.DATA_STORE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Application.store: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun provideUserDataStore(): DataStore<Preferences> {
        return App.context.store
    }
}

