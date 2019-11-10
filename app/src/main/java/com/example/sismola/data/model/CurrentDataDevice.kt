package com.example.sismola.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author by M on 10/11/19
 */
data class CurrentDataDevice (

    @SerializedName("last_update_unix")
    @Expose
    var lastUpdateUnix: Int? = null,
    @SerializedName("last_update")
    @Expose
    var lastUpdate: String? = null,
    @SerializedName("light_intensity")
    @Expose
    var lightIntensity: Int? = null,
    @SerializedName("soil_moisture")
    @Expose
    var soilMoisture: Double? = null,
    @SerializedName("ph")
    @Expose
    var ph: Double? = null,
    @SerializedName("soil_temp")
    @Expose
    var soilTemp: Double? = null,
    @SerializedName("humidity")
    @Expose
    var humidity: Double? = null,
    @SerializedName("air_temp")
    @Expose
    var airTemp: Double? = null,
    @SerializedName("rainfall")
    @Expose
    var rainfall: Int? = null

)
