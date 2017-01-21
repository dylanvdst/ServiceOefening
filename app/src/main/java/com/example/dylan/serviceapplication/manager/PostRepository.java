package com.example.dylan.serviceapplication.manager;

import android.content.Context;

import com.example.dylan.serviceapplication.models.Post;

import java.util.ArrayList;

/**
 * Created by Dylan on 21/01/2017.
 */

public class PostRepository
{
    private ArrayList<Post> posts;

    public PostRepository(Context context)
    {
        posts = new ArrayList<>();
    }

    public ArrayList<Post> getGoTPosts()
    {
        return posts;
    }

    public void addPost(Post post)
    {
        posts.add(post);
    }

    public void clear()
    {
        posts.clear();
    }
}
