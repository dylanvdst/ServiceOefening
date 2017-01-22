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

    public Post()
    {

    }

    public Post(String title, String author, String thumbnail)
    {
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
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
}
