package com.example.quoteplus.domain.usecase

import com.example.quoteplus.data.model.QuoteResponse
import com.example.quoteplus.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
){
    suspend fun getQuotes(token: String): Flow<QuoteResponse> {
        return  quoteRepository.getQuotes(token)
    }


}