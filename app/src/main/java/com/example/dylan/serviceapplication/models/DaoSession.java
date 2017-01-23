package com.example.dylan.serviceapplication.models;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.dylan.serviceapplication.models.Post;
import com.example.dylan.serviceapplication.models.Subreddit;

import com.example.dylan.serviceapplication.models.PostDao;
import com.example.dylan.serviceapplication.models.SubredditDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig postDaoConfig;
    private final DaoConfig subredditDaoConfig;

    private final PostDao postDao;
    private final SubredditDao subredditDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        postDaoConfig = daoConfigMap.get(PostDao.class).clone();
        postDaoConfig.initIdentityScope(type);

        subredditDaoConfig = daoConfigMap.get(SubredditDao.class).clone();
        subredditDaoConfig.initIdentityScope(type);

        postDao = new PostDao(postDaoConfig, this);
        subredditDao = new SubredditDao(subredditDaoConfig, this);

        registerDao(Post.class, postDao);
        registerDao(Subreddit.class, subredditDao);
    }
    
    public void clear() {
        postDaoConfig.getIdentityScope().clear();
        subredditDaoConfig.getIdentityScope().clear();
    }

    public PostDao getPostDao() {
        return postDao;
    }

    public SubredditDao getSubredditDao() {
        return subredditDao;
    }

}
