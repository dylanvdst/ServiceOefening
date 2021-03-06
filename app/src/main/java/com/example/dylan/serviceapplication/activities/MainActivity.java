package com.example.dylan.serviceapplication.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dylan.serviceapplication.R;
import com.example.dylan.serviceapplication.fragments.MainActivityFragment;
import com.example.dylan.serviceapplication.fragments.MainActivityFragment_ViewBinding;
import com.example.dylan.serviceapplication.fragments.PostFragment;
import com.example.dylan.serviceapplication.manager.PostRepository;
import com.example.dylan.serviceapplication.models.Post;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityFragment.PostClicked
{
    private MainActivityFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(findViewById(R.id.fragment) != null)
        {
            MainActivityFragment fragment = new MainActivityFragment();
            this.mainFragment = fragment;
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, fragment).commit();
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_restart)
        {
            mainFragment.api.searchPosts(mainFragment.subreddit, "", getString(R.string.MA));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        MainActivityFragment fragment = new MainActivityFragment();
        Bundle bundle = new Bundle();


        if (id == R.id.nav_funny)
        {
            bundle.putString("nav", getString(R.string.s_funny));
        } else if (id == R.id.nav_got)
        {
            bundle.putString("nav", getString(R.string.s_got));
        } else if (id == R.id.nav_pictures)
        {
            bundle.putString("nav", getString(R.string.s_pictures));
        } else if (id == R.id.nav_belgium)
        {
            bundle.putString("nav", getString(R.string.s_belgium));
        } else if (id == R.id.nav_diy)
        {
            bundle.putString("nav", getString(R.string.s_diy));
        } else if (id == R.id.nav_donald)
        {
            bundle.putString("nav", getString(R.string.s_donald));
        }

        fragment.setArguments(bundle);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        this.mainFragment = fragment;
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void postClicked(Post post)
    {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString("TITLE", post.getTitle());
        args.putString("AUTHOR", post.getAuthor());
        args.putString("IMAGE", post.getThumbnail());
        args.putInt("UPVOTE", post.getUpvote());
        args.putInt("DOWNVOTE", post.getDownvote());
        fragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
