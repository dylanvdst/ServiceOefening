package com.example.dylan.serviceapplication.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.R.attr.path;

/**
 * Created by Dylan on 21/01/2017.
 */

public interface RetrofitPosts
{
    @GET("{subreddit}.json")
    Call<JsonObject> getPosts(@Path("subreddit") String subreddit, @Query("after") String after);
}
