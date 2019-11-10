
package com.example.sismola.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * @author by M on 10/11/19
 */
public class Response {

    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("owner_email")
    @Expose
    private String ownerEmail;
    @SerializedName("devices")
    @Expose
    private List<Device> devices = null;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

}
