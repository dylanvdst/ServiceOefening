package com.example.dylan.serviceapplication.models;

import android.net.Uri;

/**
 * Created by Dylan on 21/01/2017.
 */

public class Post
{
    private String title;
    private String author;
    private String thumbnail;
    private int upvote, downvote;

    public Post()
    {

    }

    public Post(String title, String author, String thumbnail, int upvote, int downvote)
    {
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public int getUpvote()
    {
        return upvote;
    }

    public void setUpvote(int upvote)
    {
        this.upvote = upvote;
    }

    public int getDownvote()
    {
        return downvote;
    }

    public void setDownvote(int downvote)
    {
        this.downvote = downvote;
    }
}
