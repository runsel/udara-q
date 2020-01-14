package id.web.runsel.udaraq.service;

import id.web.runsel.udaraq.model.User;
import id.web.runsel.udaraq.adapter.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/login")
    Call<UserResponse> loginUser(@Body User user);

    @POST("auth/register")
    Call<UserResponse> registerUser(@Body User user);
}
