package com.example.dylan.serviceapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dylan.serviceapplication.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PostFragment extends Fragment
{
    @BindView(R.id.txvTitleDet)
    TextView txvTitle;
    @BindView(R.id.txvDislike)
    TextView txvDislike;
    @BindView(R.id.txvLike)
    TextView txvLike;
    @BindView(R.id.txvAuthorDet)
    TextView txvAuthor;
    @BindView(R.id.ivImageDet)
    ImageView ivImage;

    public PostFragment()
    {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this, rootView);
        Bundle args = getArguments();
        txvTitle.setText(args.getString("TITLE"));
        txvAuthor.setText(args.getString("AUTHOR"));
        txvLike.setText(String.valueOf(args.getInt("UPVOTE")));
        txvDislike.setText(String.valueOf(args.getInt("DOWNVOTE")));
        Picasso.with(getContext()).load(args.getString("IMAGE")).into(ivImage);
        return rootView;
    }


}
