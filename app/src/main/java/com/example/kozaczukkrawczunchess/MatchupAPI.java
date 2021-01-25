package com.example.kozaczukkrawczunchess;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MatchupAPI {
    @GET("api/crosstable/{user1}/{user2}")
    Call<ProfilesList> getMatchup(@Path("user1") String user1,@Path("user2") String user2);
}
