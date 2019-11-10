
package com.example.sismola.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * @author by M on 10/11/19
 */
public class CurrentDataDevice {

    @SerializedName("last_update_unix")
    @Expose
    private Integer lastUpdateUnix;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;
    @SerializedName("light_intensity")
    @Expose
    private Integer lightIntensity;
    @SerializedName("soil_moisture")
    @Expose
    private Double soilMoisture;
    @SerializedName("ph")
    @Expose
    private Double ph;
    @SerializedName("soil_temp")
    @Expose
    private Double soilTemp;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("air_temp")
    @Expose
    private Double airTemp;
    @SerializedName("rainfall")
    @Expose
    private Integer rainfall;

    public Integer getLastUpdateUnix() {
        return lastUpdateUnix;
    }

    public void setLastUpdateUnix(Integer lastUpdateUnix) {
        this.lastUpdateUnix = lastUpdateUnix;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(Integer lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public Double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(Double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getSoilTemp() {
        return soilTemp;
    }

    public void setSoilTemp(Double soilTemp) {
        this.soilTemp = soilTemp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(Double airTemp) {
        this.airTemp = airTemp;
    }

    public Integer getRainfall() {
        return rainfall;
    }

    public void setRainfall(Integer rainfall) {
        this.rainfall = rainfall;
    }

}
