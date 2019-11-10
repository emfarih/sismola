
package com.example.sismola.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * @author by M on 10/11/19
 */
public class InfoDevice {

    @SerializedName("device_serial")
    @Expose
    private String deviceSerial;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("device_name")
    @Expose
    private String deviceName;
    @SerializedName("device_location")
    @Expose
    private String deviceLocation;
    @SerializedName("device_info")
    @Expose
    private Object deviceInfo;

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public Object getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(Object deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

}
