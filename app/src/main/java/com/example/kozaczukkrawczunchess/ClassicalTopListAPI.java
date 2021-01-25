package com.example.kozaczukkrawczunchess;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClassicalTopListAPI {
    @GET("player/top/10/classical")
    Call<ProfilesList> getTopClassical();

}
