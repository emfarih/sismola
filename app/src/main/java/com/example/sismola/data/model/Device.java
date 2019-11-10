
package com.example.sismola.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * @author by M on 10/11/19
 */
public class Device {

    @SerializedName("info_device")
    @Expose
    private InfoDevice infoDevice;
    @SerializedName("current_data_device")
    @Expose
    private CurrentDataDevice currentDataDevice;

    public InfoDevice getInfoDevice() {
        return infoDevice;
    }

    public void setInfoDevice(InfoDevice infoDevice) {
        this.infoDevice = infoDevice;
    }

    public CurrentDataDevice getCurrentDataDevice() {
        return currentDataDevice;
    }

    public void setCurrentDataDevice(CurrentDataDevice currentDataDevice) {
        this.currentDataDevice = currentDataDevice;
    }

}
