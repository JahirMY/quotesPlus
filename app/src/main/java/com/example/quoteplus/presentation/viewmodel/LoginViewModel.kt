package com.example.quoteplus.presentation.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteplus.data.local.DataStoreManager
import com.example.quoteplus.data.model.LoginRequest
import com.example.quoteplus.domain.model.LoginUiState
import com.example.quoteplus.domain.model.UserModel
import com.example.quoteplus.domain.usecase.LoginUserUseCase
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val loginUserUseCase: LoginUserUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onAccountChange(account: String) {
        _uiState.value = _uiState.value.copy(account = account)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        viewModelScope.launch {
            val userLoginResponse = loginUserUseCase.login(LoginRequest(account =_uiState.value.account,
                password = _uiState.value.password)).first()
            val token = userLoginResponse.data
            if (token.isEmpty()){
                _uiState.value = _uiState.value.copy(
                    user = UserModel(id=0, account = "notfound", password="notfound"),
                    isLoggedIn = false)
            } else {
                DataStoreManager.saveTokenToDataStore(token)
                val user = UserModel(id=0, account = _uiState.value.account,
                    password = _uiState.value.password)
                _uiState.value = _uiState.value.copy(user = user, isLoggedIn = true )

            }
        }
    }


}