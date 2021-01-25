package com.example.kozaczukkrawczunchess;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfilesList {
    @SerializedName("users")
    private List<Profile> profiles;

    @SerializedName("nbGames")
    private Integer nbGames;

    public List<Profile> getProfiles() {
        return profiles;
    }
    public void setProfiles(List<Profile> profiles){
        this.profiles = profiles;
    }
    public Integer getNbGames() {
        return nbGames;
    }

    public void setNbGames(Integer nbGames) {
        this.nbGames = nbGames;
    }
}
