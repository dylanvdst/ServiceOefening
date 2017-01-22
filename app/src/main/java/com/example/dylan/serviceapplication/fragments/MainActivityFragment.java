package com.example.dylan.serviceapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dylan.serviceapplication.R;
import com.example.dylan.serviceapplication.manager.PostRepository;
import com.example.dylan.serviceapplication.models.Post;
import com.example.dylan.serviceapplication.network.RedditAPI;
import com.example.dylan.serviceapplication.view.adapter.PostAdapter;

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


    PostRepository repo;
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

        ButterKnife.bind(this, rootView);
        repo = new PostRepository(getContext());
        PostAdapter adapter = new PostAdapter(repo.getGoTPosts(), getContext());
        recyclerView.setAdapter(adapter);
        RedditAPI api = new RedditAPI(getContext(), repo, adapter);
        if(bundle != null)
        {
            switch (bundle.getString("nav"))
            {
                case "diy":api.searchDIYReddit();break;
                case "fun": api.searchFunnyReddit();break;
                case "got": api.searchGameOfThronesReddit();break;
                case "pic": api.searchPicturesReddit();break;
                case "bel": api.searchBelgiumReddit();break;
                case "don": api.searchDonaldReddit();break;
            }
        }
        else
        {
            api.searchBelgiumReddit();
        }
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try {
            postClickedInterface = (PostClicked) getActivity();
        }
        catch(ClassCastException e)
        {
            e.printStackTrace();
        }
    }


    private class PostOnclickListener implements View.OnClickListener{

        private final Context context;

        public PostOnclickListener(Context context)
        {
            this.context = context;
        }

        @Override
        public void onClick(View v)
        {
            currentIndex = recyclerView.getChildAdapterPosition(v);
            postClickedInterface.postClicked(repo.getGoTPosts().get(currentIndex));
        }
    }

    public interface PostClicked
    {
        void postClicked(Post post);
    }

}
