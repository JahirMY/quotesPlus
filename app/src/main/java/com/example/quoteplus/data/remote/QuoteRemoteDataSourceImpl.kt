package com.example.quoteplus.data.remote

import android.util.Log
import com.example.quoteplus.data.model.QuoteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class QuoteRemoteDataSourceImpl @Inject constructor(
   private val quoteService: QuoteService

): QuoteRemoteDataSource {
    override suspend fun getQuotes(token: String): Flow<QuoteResponse> {
        try{
            val quoteResponse =  quoteService.getQuotes(token)
            return flow{emit(quoteResponse)}
        } catch (ex: Exception){
            Log.e("ERROR:", ex.message.toString())
            val userLoginResponse = QuoteResponse(
                success = false,
                message = ex.message.toString(),
                data= emptyList()
            )
            return flow{emit(userLoginResponse)}
        }
    }
}