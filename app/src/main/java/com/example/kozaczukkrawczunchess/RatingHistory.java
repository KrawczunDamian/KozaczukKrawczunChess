package com.example.kozaczukkrawczunchess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingHistory {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("points")
    @Expose
    private List<List<Integer>> points = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<Integer>> getPoints() {
        return points;
    }

    public void setPoints(List<List<Integer>> points) {
        this.points = points;
    }
}
