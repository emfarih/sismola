package com.example.sismola.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
        @SerializedName("response")
        val response: Response
)