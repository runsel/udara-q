package id.web.runsel.udaraq.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastResponse {

    @SerializedName("daily")
    @Expose
    private Daily daily;
    @SerializedName("data")
    @Expose
    private List<DataForecast> data = null;

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public List<DataForecast> getData() {
        return data;
    }

    public void setData(List<DataForecast> data) {
        this.data = data;
    }

}