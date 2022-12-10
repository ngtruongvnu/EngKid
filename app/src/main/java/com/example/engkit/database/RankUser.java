package com.example.engkit.database;


import com.google.gson.annotations.SerializedName;

public class RankUser {
    @SerializedName("fullname")
    public String fullname;
    @SerializedName("score")
    public int score;
    @SerializedName("imageUrl")
    public String imageUrl;
    @SerializedName("rank")
    public int rank;

    public RankUser(String fullname, int score, int rank, String imageUrl) {
        this.fullname = fullname;
        this.score = score;
        this.rank = rank;
        this.imageUrl = imageUrl;
    }
}
