package com.example.sismola.data

import com.example.sismola.data.model.ApiResponse
import javax.inject.Inject

/**
 * @author by M on 10/11/19
 */
class DataRepository @Inject constructor(private val apiService: ApiService){
    suspend fun getData(): ApiResponse {
        return apiService.getResponse()
    }
}