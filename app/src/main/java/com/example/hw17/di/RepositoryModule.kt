package com.example.hw17.di

import com.example.hw17.data.repository.AuthRepositoryImpl
import com.example.hw17.data.repository.UserDataStoreRepositoryImpl
import com.example.hw17.domain.repository.AuthRepository
import com.example.hw17.domain.repository.UserDataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindDataStoreRepository(dataStoreRepositoryImpl: UserDataStoreRepositoryImpl): UserDataStoreRepository
}