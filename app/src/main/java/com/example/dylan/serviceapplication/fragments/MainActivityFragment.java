package com.example.dylan.serviceapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.serviceapplication.R;
import com.example.dylan.serviceapplication.manager.PostRepository;
import com.example.dylan.serviceapplication.models.Post;
import com.example.dylan.serviceapplication.models.Subreddit;
import com.example.dylan.serviceapplication.network.RedditAPI;
import com.example.dylan.serviceapplication.view.adapter.PostAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityFragment extends Fragment
{

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerView;

    protected RecyclerView.LayoutManager layoutManager;

    private int currentIndex;

    public static PostOnclickListener postOnclickListener;
    public PostClicked postClickedInterface;
    public RedditAPI api;
    public Subreddit subreddit;
    private ArrayList<Subreddit> subreddits;


    public PostRepository repo;

    public MainActivityFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        postOnclickListener = new PostOnclickListener(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_activity, container, false);
        Bundle bundle = getArguments();
        repo = new PostRepository(getContext());
        subreddits = repo.getSubReddits();

        if (bundle != null)
        {

            for (Subreddit s : subreddits)
            {
                if (s.getName().equals(bundle.getString("nav")))
                {
                    subreddit = s;
                }
            }
        } else
        {
            subreddit = subreddits.get(0);
        }

        ButterKnife.bind(this, rootView);

        PostAdapter adapter = new PostAdapter(repo.getPosts(subreddit.getName()), getContext());
        recyclerView.setAdapter(adapter);
        api = new RedditAPI(getContext(), repo, adapter);
        if (repo.getPosts(subreddit.getName()).isEmpty())
        {
            api.searchPosts(subreddit.getName());
        }

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Log.i("MAINACTIVITYFRAGMENT", subreddit.getName());
        return rootView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            postClickedInterface = (PostClicked) getActivity();
        } catch (ClassCastException e)
        {
            e.printStackTrace();
        }
    }


    private class PostOnclickListener implements View.OnClickListener
    {

        private final Context context;

        public PostOnclickListener(Context context)
        {
            this.context = context;
        }

        @Override
        public void onClick(View v)
        {
            currentIndex = recyclerView.getChildAdapterPosition(v);
            postClickedInterface.postClicked(repo.getPosts(subreddit.getName()).get(currentIndex));
        }
    }

    public interface PostClicked
    {
        void postClicked(Post post);
    }

}
