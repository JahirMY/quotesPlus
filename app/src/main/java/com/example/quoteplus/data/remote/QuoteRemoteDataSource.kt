package com.example.quoteplus.data.remote

import com.example.quoteplus.data.model.QuoteResponse
import kotlinx.coroutines.flow.Flow

interface QuoteRemoteDataSource {
    suspend fun getQuotes(token: String): Flow<QuoteResponse>
}

