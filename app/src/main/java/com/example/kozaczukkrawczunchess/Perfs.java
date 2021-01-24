package com.example.kozaczukkrawczunchess;
import com.google.gson.annotations.SerializedName;

public class Perfs {

    @SerializedName("classical")
    private Classical classical;

    public Classical getClassical() {
        return classical;
    }

    public void setClassical(Classical classical) {
        this.classical = classical;
    }

}