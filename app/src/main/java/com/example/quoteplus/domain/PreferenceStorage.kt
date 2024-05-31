package com.example.quoteplus.domain

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    fun getToken() : Flow<String>
    suspend fun saveToken(token: String)

}