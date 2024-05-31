package com.example.quoteplus.data

import com.example.quoteplus.data.model.LoginRequest
import com.example.quoteplus.data.remote.UserLoginResponse
import com.example.quoteplus.data.remote.UserRemoteDataSource
import com.example.quoteplus.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor
    (private val userRemoteDataSource: UserRemoteDataSource
):
    UserRepository {

    override suspend fun login(loginRequest: LoginRequest): Flow<UserLoginResponse> {
        return userRemoteDataSource.login(loginRequest)
    }
}