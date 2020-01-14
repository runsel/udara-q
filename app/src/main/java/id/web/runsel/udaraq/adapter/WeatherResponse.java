package id.web.runsel.udaraq.adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("temperature")
    @Expose
    private Integer temperature;
    @SerializedName("humadity")
    @Expose
    private Integer humadity;
    @SerializedName("air_quality")
    @Expose
    private Integer airQuality;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumadity() {
        return humadity;
    }

    public void setHumadity(Integer humadity) {
        this.humadity = humadity;
    }

    public Integer getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(Integer airQuality) {
        this.airQuality = airQuality;
    }
}
