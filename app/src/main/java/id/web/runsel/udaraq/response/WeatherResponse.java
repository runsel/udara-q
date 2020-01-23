package id.web.runsel.udaraq.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("air_quality")
    @Expose
    private Integer airQuality;
    @SerializedName("air_status")
    @Expose
    private String airStatus;
    @SerializedName("air_color")
    @Expose
    private String airColor;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("temperature")
    @Expose
    private Integer temperature;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("hourly")
    @Expose
    private Hourly hourly;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(Integer airQuality) {
        this.airQuality = airQuality;
    }

    public String getAirStatus() {
        return airStatus;
    }

    public void setAirStatus(String airStatus) {
        this.airStatus = airStatus;
    }

    public String getAirColor() {
        return airColor;
    }

    public void setAirColor(String airColor) {
        this.airColor = airColor;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

}