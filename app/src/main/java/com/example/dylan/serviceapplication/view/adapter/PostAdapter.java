package com.example.dylan.serviceapplication.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dylan.serviceapplication.R;
import com.example.dylan.serviceapplication.models.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dylan on 21/01/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MainViewHolder>
{

    private ArrayList<Post> postList;
    private Context context;

    public PostAdapter(ArrayList<Post> posts, Context context)
    {
        this.postList = posts;
        this.context = context;
    }

    @Override
    public PostAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostAdapter.MainViewHolder holder, int position)
    {
        ImageView image = holder.thumbnail;
        TextView title = holder.title;
        TextView author = holder.author;
        Context context = holder.thumbnail.getContext();
        Picasso.with(context).load(R.drawable.ic_menu_camera).into(image);
        image.setImageResource(R.drawable.ic_menu_camera);
        title.setText(postList.get(position).getTitle());
        author.setText(postList.get(position).getAuthor());
    }

    @Override
    public int getItemCount()
    {
        return postList.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.card_image)
        public ImageView thumbnail;
        @BindView(R.id.card_title)
        public TextView title;
        @BindView(R.id.card_author)
        public TextView author;

        public MainViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
