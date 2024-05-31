package com.example.quoteplus.data.remote

import com.example.quoteplus.data.model.LoginRequest
import com.example.quoteplus.data.model.QuoteResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface QuoteService {
    //@Headers("Content-Type: application/json; " +
    //        "charset=utf-8","Accept: */*; charset=utf-8")
    @GET("quotes")
    suspend fun getQuotes(@Header("Authorization") token:String): QuoteResponse
}