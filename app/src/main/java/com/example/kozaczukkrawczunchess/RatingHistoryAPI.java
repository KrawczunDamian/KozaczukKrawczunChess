package com.example.kozaczukkrawczunchess;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RatingHistoryAPI {
    @GET("api/user/{username}/rating-history")
    Call<RatingHistory[]> getRatingHistory(@Path("username") String username);
}
