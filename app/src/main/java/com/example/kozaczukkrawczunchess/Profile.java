package com.example.kozaczukkrawczunchess;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("id")
    private String UserID;
    @SerializedName("username")
    private String username;
    @SerializedName("online")
    private Boolean online;
    @SerializedName("title")
    private String title;
    @SerializedName("perfs")
    private Perfs perfs;
    public String getUsername(){
        return username;
    }
    public String getOnline(){
        String isOnline;
        if(online == true) isOnline = "Online";
        else isOnline = "Offline";
        return isOnline;
    }
    public String getUserID(){return UserID;}
    public void setUserID(String ID){this.UserID = ID;}
    public void setUsername(String username){this.username = username;}
    public void setOnline(Boolean online){this.online = online;}
    public Perfs getPerfs() {
        return perfs;
    }

    public void setPerfs(Perfs perfs) {
        this.perfs = perfs;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
