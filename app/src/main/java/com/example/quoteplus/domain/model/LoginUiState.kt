package com.example.quoteplus.domain.model

data class LoginUiState(
    val account: String = "",
    val password: String = "",
    val user: UserModel? = null,
    val isLoggedIn: Boolean = false
)