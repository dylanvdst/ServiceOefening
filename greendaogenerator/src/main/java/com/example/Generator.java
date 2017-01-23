package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class Generator
{
    public static void main(String args[]) throws Exception
    {
        Schema schema = new Schema(1, "com.example.dylan.serviceapplication.models");

        Entity post = schema.addEntity("Post");
        post.addIdProperty();
        post.addStringProperty("title");
        post.addStringProperty("author");
        post.addStringProperty("thumbnail");
        post.addIntProperty("upvote");
        post.addIntProperty("downvote");

        Entity subreddit = schema.addEntity("Subreddit");
        subreddit.addIdProperty();
        subreddit.addStringProperty("name");

        Property subId = post.addLongProperty("subredditId").getProperty();
        post.addToOne(subreddit, subId);

        ToMany subToPost = subreddit.addToMany(post, subId);
        subToPost.setName("posts");

        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema, "./app/src/main/java");
    }
}
