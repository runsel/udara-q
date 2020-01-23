package id.web.runsel.udaraq.service;

import id.web.runsel.udaraq.response.ProfileResponse;
import id.web.runsel.udaraq.response.UserResponse;
import id.web.runsel.udaraq.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ProfileService {
    @Headers({
            "Content-Type: application/json",
            "X-Requested-With: XMLHttpRequest"
    })
    @GET("profile")
    Call<ProfileResponse> getProfile(@Header("Authorization") String token);

    @POST("profile/update")
    Call<UserResponse> updateProfile(@Header("Authorization") String token, @Body User user);
}
