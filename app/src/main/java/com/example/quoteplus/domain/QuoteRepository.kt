package com.example.quoteplus.domain

import com.example.quoteplus.data.model.QuoteResponse
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend  fun getQuotes(token: String): Flow<QuoteResponse>
}