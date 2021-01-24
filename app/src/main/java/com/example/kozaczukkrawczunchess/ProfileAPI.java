package com.example.kozaczukkrawczunchess;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProfileAPI {
    @GET("api/user/{username}")
    Call<Profile> findProfile(@Path("username") String username);

}
