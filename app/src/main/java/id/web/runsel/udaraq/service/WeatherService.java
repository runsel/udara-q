package id.web.runsel.udaraq.service;

import id.web.runsel.udaraq.response.ForecastResponse;
import id.web.runsel.udaraq.response.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface WeatherService {
    @GET
    Call<WeatherResponse> getCurrentLocation(@Header("Authorization") String token, @Url String url);

    @GET
    Call<ForecastResponse> getForecast(@Header("Authorization") String token, @Url String url);
}
