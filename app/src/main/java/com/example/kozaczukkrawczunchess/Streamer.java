package com.example.kozaczukkrawczunchess;

import com.google.gson.annotations.SerializedName;

public class Streamer {
    @SerializedName("id")
    private String UserID;
    @SerializedName("name")
    private String username;
    public String getUsername(){
        return username;
    }
    public String getUserID(){return UserID;}
    public void setUserID(String ID){this.UserID = ID;}
    public void setUsername(String username){this.username = username;}
}
