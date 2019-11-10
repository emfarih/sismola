package com.example.sismola.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author by M on 10/11/19
 */
data class Example (

    @SerializedName("success")
    @Expose
    var success: Boolean? = null,
    @SerializedName("response")
    @Expose
    var response: Response? = null

)