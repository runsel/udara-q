package id.web.runsel.udaraq.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataForecast {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("predict")
    @Expose
    private String predict;
    @SerializedName("temp")
    @Expose
    private Integer temp;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

}