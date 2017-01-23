package com.example.dylan.serviceapplication.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.dylan.serviceapplication.R;
import com.example.dylan.serviceapplication.manager.PostRepository;
import com.example.dylan.serviceapplication.models.Post;
import com.example.dylan.serviceapplication.models.Subreddit;
import com.example.dylan.serviceapplication.view.adapter.PostAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dylan on 21/01/2017.
 */

public class RedditAPI
{
    public static final String BASE_URL = "https://www.reddit.com/r/";
    Retrofit retrofit;
    PostRepository repository;
    Context context;
    PostAdapter adapter;
    private Subreddit subreddit;

    public RedditAPI(Context context, PostRepository repository, PostAdapter adapter)
    {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        this.repository = repository;
        this.context = context;
        this.adapter = adapter;
    }

    public void searchPosts(Subreddit subreddit, String after, String where)
    {
        final RetrofitPosts service = retrofit.create(RetrofitPosts.class);
        final ProgressDialog ringProgressDialog = ProgressDialog.show(context, "Please wait ...", "Getting the information from Reddit", true);
        ringProgressDialog.setCancelable(true);
        Call<JsonObject> callGoTPosts = service.getPosts(subreddit.getName(), after);
        this.subreddit = subreddit;
        final String wheres = where;
        callGoTPosts.enqueue(new Callback<JsonObject>()
        {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
            {

                processCall(response, wheres);
                ringProgressDialog.dismiss();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t)
            {
                t.printStackTrace();
            }
        });

    }

    private void processCall(Response<JsonObject> response, String where)
    {
        if(where.equals(context.getString(R.string.MA)))
        {
            repository.deletePosts(subreddit);
        }
        JsonObject object = response.body();
        JsonObject object1 = object.getAsJsonObject("data");
        JsonArray object2 = object1.getAsJsonArray("children");
        //repository.clear();
        for(int i = 0; i < object2.size(); i++)
        {
            JsonObject object3 = object2.get(i).getAsJsonObject();
            JsonObject object4 = object3.getAsJsonObject("data");
            Post post = new Post();
            post.setTitle(object4.get("title").getAsString());
            post.setAuthor(object4.get("author").getAsString());
            post.setThumbnail(object4.get("thumbnail").getAsString());
            post.setUpvote(object4.get("ups").getAsInt());
            post.setDownvote(object4.get("downs").getAsInt());
            post.setAfter(object1.get("after").getAsString());
            post.setSubreddit(subreddit);
            repository.addPost(post);
        }

        repository.addToDB();
    }
}
