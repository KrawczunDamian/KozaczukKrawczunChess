package com.example.kozaczukkrawczunchess;

import com.google.gson.annotations.SerializedName;

public class Classical {
    @SerializedName("rating")
    private Integer rating;
    @SerializedName("progress")
    private Integer progress;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
