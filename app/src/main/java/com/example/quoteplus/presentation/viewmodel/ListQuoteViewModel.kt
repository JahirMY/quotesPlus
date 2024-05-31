package com.example.quoteplus.presentation.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteplus.data.local.DataStoreManager
import com.example.quoteplus.data.model.QuoteResponse
import com.example.quoteplus.domain.model.LoginUiState
import com.example.quoteplus.domain.model.QuoteModel
import com.example.quoteplus.domain.model.QuoteUiState
import com.example.quoteplus.domain.usecase.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListQuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<QuoteUiState>(QuoteUiState.Init)
    val uiState: StateFlow<QuoteUiState> = _uiState

    init {
        viewModelScope.launch {
            getQuotes()
        }
    }


    suspend fun getQuotes() {
        _uiState.value = QuoteUiState.Loading

        viewModelScope.launch {
            val token = DataStoreManager.token.first()
            Log.d("TOKEN IN VIEWMODEL: ", token)
            val uiState = getQuotesUseCase.getQuotes("Bearer $token").first()
            Log.d("DATA:", uiState.data.toString())
            notifyDataState(uiState.data)
            }

        }
    private fun notifyInitState() {
        _uiState.value= QuoteUiState.Init
    }
    private fun notifyLoadingState() {
        _uiState.value = QuoteUiState.Loading
    }

    private fun notifyDataState(data: List<QuoteModel>) {
        _uiState.value= QuoteUiState.Data(data)
    }

    private fun notifyErrorState(error: Throwable) {
        _uiState.value= QuoteUiState.Error(error)
    }
}