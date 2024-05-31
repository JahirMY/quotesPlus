package com.example.quoteplus.domain.usecase

import com.example.quoteplus.data.model.LoginRequest
import com.example.quoteplus.data.remote.UserLoginResponse
import com.example.quoteplus.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun login(loginRequest: LoginRequest): Flow<UserLoginResponse> =
         userRepository.login(loginRequest)
}