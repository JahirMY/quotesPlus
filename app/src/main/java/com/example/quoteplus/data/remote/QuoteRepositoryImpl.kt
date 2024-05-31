package com.example.quoteplus.data.remote

import com.example.quoteplus.data.model.QuoteResponse
import com.example.quoteplus.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteDataSource: QuoteRemoteDataSource): QuoteRepository {
    override suspend fun  getQuotes(token: String): Flow<QuoteResponse> {
        return quoteDataSource.getQuotes(token)
    }
}

