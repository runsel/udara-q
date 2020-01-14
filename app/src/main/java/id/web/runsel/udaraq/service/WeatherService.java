package id.web.runsel.udaraq.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WeatherService {
    @Headers({
            "Content-Type: application/json",
            "X-Requested-With: XMLHttpRequest"
    })
    @POST("auth/login")
    Call<WeatherService> getCurrentLocation(@Body JsonObject jsonObject);
}
