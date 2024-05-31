package com.example.quoteplus.data.remote

import com.example.quoteplus.data.model.LoginRequest
import com.example.quoteplus.domain.model.UserModel
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type: application/json;" +
            " charset=utf-8","Accept: */*;" +
            " charset=utf-8")
    @POST("users/login")
suspend fun login(
        @Body loginRequest: LoginRequest)
        : JsonObject
}