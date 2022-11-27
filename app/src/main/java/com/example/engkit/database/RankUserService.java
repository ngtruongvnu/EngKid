package com.example.engkit.database;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RankUserService {
    @GET("api/v1/users")
    Call<RankUser[]> getAllUsers();
}
