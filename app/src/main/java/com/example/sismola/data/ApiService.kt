package com.example.sismola.data

import com.example.sismola.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author by M on 10/11/19
 */
interface ApiService{
    @GET("datas.php")
    suspend fun getResponse(@Query("req") req: String = "aJ906CNS0k7RxaK",
                            @Query("type") type: String = "1",
                            @Query("owner") owner: String = "rayzerdodo@gmail.com"): ApiResponse
}