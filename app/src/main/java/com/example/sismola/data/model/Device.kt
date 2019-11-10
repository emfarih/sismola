package com.example.sismola.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author by M on 10/11/19
 */
data class Device (

    @SerializedName("info_device")
    @Expose
    var infoDevice: InfoDevice? = null,
    @SerializedName("current_data_device")
    @Expose
    var currentDataDevice: CurrentDataDevice? = null

)
