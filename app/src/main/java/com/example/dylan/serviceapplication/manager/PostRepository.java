package com.example.dylan.serviceapplication.manager;

import android.content.Context;

import com.example.dylan.serviceapplication.models.Post;

import java.util.ArrayList;

/**
 * Created by Dylan on 21/01/2017.
 */

public class PostRepository
{
    private ArrayList<Post> gotPosts;
    public PostRepository(Context context)
    {
        gotPosts = new ArrayList<>();
    }

    public void addPost(Post post)
    {
        gotPosts.add(post);
    }

    public ArrayList<Post> getGoTPosts()
    {
        return gotPosts;
    }
}
