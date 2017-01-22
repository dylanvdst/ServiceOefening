package com.example.dylan.serviceapplication.manager;

import android.content.Context;

import com.example.dylan.serviceapplication.R;
import com.example.dylan.serviceapplication.models.DaoMaster;
import com.example.dylan.serviceapplication.models.DaoSession;
import com.example.dylan.serviceapplication.models.Post;
import com.example.dylan.serviceapplication.models.PostDao;
import com.example.dylan.serviceapplication.models.Subreddit;
import com.example.dylan.serviceapplication.models.SubredditDao;

import java.util.ArrayList;
import java.util.Collections;

import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;

/**
 * Created by Dylan on 21/01/2017.
 */

public class PostRepository
{
    private ArrayList<Post> posts;
    private ArrayList<Subreddit> subreddits;
    private DaoMaster.DevOpenHelper helper;
    private DaoMaster master;
    private DaoSession session;
    private PostDao postDao;
    private SubredditDao subDao;
    private Context context;

    private ArrayList<Post> hulp;

    public PostRepository(Context context)
    {
        posts = new ArrayList<>();
        helper = new DaoMaster.DevOpenHelper(context, "RedditDB9", null);
        master = new DaoMaster(helper.getWritableDatabase());
        session = master.newSession();
        postDao = session.getPostDao();
        subDao = session.getSubredditDao();
        this.context = context;
        this.subreddits = getSubredditFromDB();
        if(subreddits.isEmpty())
        {
            addSubToDB();
        }
    }

    public ArrayList<Post> getPosts(String subreddit)
    {
        posts.clear();
        hulp = new ArrayList<>();
        Subreddit subreddit1 = null;
        for(Subreddit sub : getSubReddits())
        {
            if(sub.getName().equals(subreddit))
                subreddit1 = sub;
        }
        for(Post post : subreddit1.getPosts())
        {
            if(post.getSubreddit().getName().equals(subreddit))
                posts.add(post);
        }
        return posts;
    }

    public ArrayList<Subreddit> getSubredditFromDB()
    {
        AsyncSession session = postDao.getSession().startAsyncSession();
        session.setListener(new AsyncOperationListener()
        {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation)
            {
                Object object = operation.getResult();
                if(object != Collections.emptyList())
                {
                    subreddits = (ArrayList<Subreddit>)object;
                }
            }
        });
        session.loadAll(Subreddit.class);
        session.waitForCompletion();
        return subreddits;
    }

    public void addPost(Post post)
    {
        posts.add(post);
    }

    public void clear()
    {
        posts.clear();
    }

    public void addToDB()
    {
        AsyncSession asyncSession = postDao.getSession().startAsyncSession();
        for (Post post: posts)
        {
            asyncSession.insert(post);
        }
    }

    public void addSubToDB()
    {
        AsyncSession session = subDao.getSession().startAsyncSession();
        for(Subreddit s : setSubReddits())
        {
            session.insert(s);
        }

    }

    public ArrayList<Subreddit> setSubReddits()
    {
        Subreddit funny = new Subreddit();
        funny.setName(context.getString(R.string.s_funny));
        Subreddit picture = new Subreddit();
        picture.setName(context.getString(R.string.s_pictures));
        Subreddit got = new Subreddit();
        got.setName(context.getString(R.string.s_got));
        Subreddit donald = new Subreddit();
        donald.setName(context.getString(R.string.s_donald));
        Subreddit belgium = new Subreddit();
        belgium.setName(context.getString(R.string.s_belgium));
        Subreddit diy = new Subreddit();
        diy.setName(context.getString(R.string.s_diy));
        subreddits = new ArrayList<>();
        subreddits.add(funny);
        subreddits.add(picture);
        subreddits.add(got);
        subreddits.add(donald);
        subreddits.add(belgium);
        subreddits.add(diy);
        return subreddits;

    }

    public ArrayList<Subreddit> getSubReddits()
    {
        return subreddits;
    }
}
