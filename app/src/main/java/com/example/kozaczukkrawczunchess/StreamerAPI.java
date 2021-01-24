package com.example.kozaczukkrawczunchess;


import retrofit2.Call;
import retrofit2.http.GET;


public interface StreamerAPI {
    @GET("streamer/live")
    Call<Streamer[]> getStreamers();

}
