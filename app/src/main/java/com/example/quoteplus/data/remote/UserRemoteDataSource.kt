package com.example.quoteplus.data.remote

import com.example.quoteplus.data.model.LoginRequest
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
   suspend fun login(loginRequest: LoginRequest): Flow<UserLoginResponse>
}