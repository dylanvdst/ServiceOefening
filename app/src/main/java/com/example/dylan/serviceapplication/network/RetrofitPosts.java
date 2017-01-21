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
    @GET("belgium.json")
    Call<JsonObject> getBelgiumPosts();
    @GET("the_donald.json")
    Call<JsonObject> getDonaldPosts();
    @GET("funny.json")
    Call<JsonObject> getFunnyPosts();
    @GET("pictures.json")
    Call<JsonObject> getPicturesPosts();
    @GET("diy.json")
    Call<JsonObject> getDIYPosts();
}
