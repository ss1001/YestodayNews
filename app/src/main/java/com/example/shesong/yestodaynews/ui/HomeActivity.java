package com.example.shesong.yestodaynews.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shesong.yestodaynews.R;
import com.example.shesong.yestodaynews.adapter.NewsAdapter;
import com.example.shesong.yestodaynews.entity.NewsEntity;
import com.example.shesong.yestodaynews.net.NetEnv;
import com.example.shesong.yestodaynews.net.NetRequest;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements INetCallback{

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private String url;
    private List<NewsEntity>newsLists;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter newsAdapter;
    private int lastPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_home);
        init();
        initData();
        handleScroll();
    }

    private void initData() {
        newsLists=new ArrayList<NewsEntity>();
        newsAdapter=new NewsAdapter(this,newsLists);
        initHeader();
        NetRequest request=new NetRequest(this,url,10,(INetCallback)this);
        request.doLoadData();
        recyclerView.setAdapter(newsAdapter);
    }

    private void init() {
   //     progressBar=(ProgressBar)findViewById(R.id.progressBar);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView=(RecyclerView)findViewById(R.id.sv_main);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetRequest request=new NetRequest(HomeActivity.this,url,10,(INetCallback) HomeActivity.this);
                request.doLoadData();
            }
        });
        url= NetEnv.NEWS_SERVER;

    }

    @Override
    public void netCall(JSONObject jsonObject) {

        try {
            String temp=jsonObject.getString("newslist");
            JSONArray jsonArray=new JSONArray(temp);
            for(int i=0;i<jsonArray.length();i++){
                Gson gson=new Gson();
                NewsEntity newsEntity=gson.fromJson(jsonArray.get(i).toString(), NewsEntity.class);
                newsLists.add(newsEntity);
            }
       //     progressBar.setVisibility(View.GONE);


            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(
                    this, DividerItemDecoration.HORIZONTAL));
            refreshLayout.setRefreshing(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initHeader() {
        View headerView=LayoutInflater.from(this).inflate(R.layout.news_item,null);
        ((SimpleDraweeView)headerView.findViewById(R.id.simpleDraweeView)).setImageURI("res://com.example.shesong.yestodaynews/"+R.drawable.activity_launching_drawable_logo);
        ((TextView)headerView.findViewById(R.id.tv_title)).setText("佘松");
        ((TextView)headerView.findViewById(R.id.tv_content)).setText("nothing nothing nothing nothing");
        headerView.findViewById(R.id.rl_good).setVisibility(View.GONE);
        newsAdapter.setHeaderView(headerView);
    }

    private void handleScroll(){
        recyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(lastPosition==newsLists.size()&&newState==RecyclerView.SCROLL_STATE_IDLE){
                    refreshLayout.setRefreshing(true);
                    NetRequest request=new NetRequest(HomeActivity.this,url,10,(INetCallback) HomeActivity.this);
                    request.doLoadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastPosition=mLayoutManager.findLastVisibleItemPosition();
            }
        });

        }
    @Override
    public void fail() {

    }
}
