package com.example.quoteplus.data.remote

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("message")
    var message: String,

    @SerializedName("data")
    var data: String

)