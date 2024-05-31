package com.example.quoteplus.domain.model

sealed class QuoteUiState {
    data object Init:QuoteUiState()
    data object Loading:QuoteUiState()

    data class Data(val list : List<QuoteModel>): QuoteUiState()

    data class Error (val error: Throwable): QuoteUiState()
}