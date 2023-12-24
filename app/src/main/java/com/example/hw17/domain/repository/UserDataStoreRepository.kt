package com.example.hw17.domain.repository

interface UserDataStoreRepository {
    suspend fun save(key: String, value: String)
    suspend fun remove(key: String)
    suspend fun read(key: String): String?
}