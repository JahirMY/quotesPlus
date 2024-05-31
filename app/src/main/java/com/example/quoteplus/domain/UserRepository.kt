package com.example.quoteplus.domain

import com.example.quoteplus.data.model.LoginRequest
import com.example.quoteplus.data.remote.UserLoginResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(loginRequest: LoginRequest): Flow<UserLoginResponse>
}