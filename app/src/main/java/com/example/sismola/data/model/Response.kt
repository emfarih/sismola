package com.example.sismola.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author by M on 10/11/19
 */
data class Response (
    @SerializedName("owner_name")
    @Expose
    var ownerName: String? = null,
    @SerializedName("owner_email")
    @Expose
    var ownerEmail: String? = null,
    @SerializedName("devices")
    @Expose
    var devices: List<Device>? = null
)
