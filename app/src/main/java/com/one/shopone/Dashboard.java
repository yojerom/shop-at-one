package com.one.shopone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.one.shopone.adapter.Trendingadapter;
import com.one.shopone.model.Trending_details;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Custom_viewpager_adapter mcustom_viewpager_adapter;
    ViewPager mviewpager;
    NavigationView navigationView;
    private RecyclerView mrecyclerview;
    private Trendingadapter mAdapter;
    public ArrayList<Trending_details> mTrendingcollection;
    FloatingActionButton fabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        SharedPreferences sharedPreferences = Dashboard.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        String text1= sharedPreferences.getString(getString(R.string.userid),"hiiiii");

        //Toast.makeText(Dashboard.this,text1,Toast.LENGTH_LONG).show();
        mcustom_viewpager_adapter = new Custom_viewpager_adapter(this);
        mviewpager = (ViewPager)findViewById(R.id.viewimager);
        mviewpager.setAdapter(mcustom_viewpager_adapter);
        init();
        new FetchDataTask(mAdapter,mTrendingcollection).execute();
       // mviewpager.setVisibility(View.GONE);
        selectNavigation();
        search();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void selectNavigation(){
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home_id:

                        break;
                    case R.id.chat_id:

                        break;
                    case R.id.logout_id:
                        //FirebaseAuth.getInstance().signOut();
                        //startActivity(new Intent(Dashboard.this,MainActivity.class));
                        startActivity(new Intent(Dashboard.this,Profile.class));
                        break;
                }
                return  true;
            }
        });

    }
    public void init(){
        mrecyclerview = (RecyclerView)findViewById(R.id.trending_recycler);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerview.setHasFixedSize(true);
        mTrendingcollection = new ArrayList<>();
        mAdapter = new Trendingadapter(mTrendingcollection,this);
        mrecyclerview.setAdapter(mAdapter);
    }
    public void search(){
        fabtn = (FloatingActionButton)findViewById(R.id.searchingbtn);

        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Dashboard.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_search,null);
                final EditText mkeyword = (EditText)findViewById(R.id.keyword);
                final EditText mbrandname = (EditText)findViewById(R.id.brand);
                final EditText mlowprice = (EditText)findViewById(R.id.lowprice);
                final EditText mhighprice = (EditText)findViewById(R.id.highprice);
                Button msubmit = (Button)findViewById(R.id.submit);

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.show();
                dialog.show();




            }
        });
    }
}
