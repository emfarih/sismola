package com.example.sismola.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author by M on 10/11/19
 */
data class InfoDevice (

    @SerializedName("device_serial")
    @Expose
    var deviceSerial: String? = null,
    @SerializedName("device_id")
    @Expose
    var deviceId: String? = null,
    @SerializedName("device_name")
    @Expose
    var deviceName: String? = null,
    @SerializedName("device_location")
    @Expose
    var deviceLocation: String? = null,
    @SerializedName("device_info")
    @Expose
    var deviceInfo: Any? = null

)
