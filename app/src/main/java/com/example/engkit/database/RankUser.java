package com.example.engkit.database;

public class RankUser {
    public String fullname;
    public int score;
    public String imageUrl;
    public int rank;

    public RankUser(String fullname, int score, int rank, String imageUrl) {
        this.fullname = fullname;
        this.score = score;
        this.rank = rank;
        this.imageUrl = imageUrl;
    }
}
