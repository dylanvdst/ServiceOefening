package com.example.dylan.serviceapplication.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dylan on 21/01/2017.
 */

public interface RetrofitPosts
{
    @GET("gameofthrones.json")
    Call<JsonObject> getGameOfThronesPosts();
}
