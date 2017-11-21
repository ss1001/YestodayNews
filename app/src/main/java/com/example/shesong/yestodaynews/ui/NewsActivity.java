package com.example.shesong.yestodaynews.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.shesong.yestodaynews.R;

public class NewsActivity extends AppCompatActivity {
    private WebView wv_news;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        initData();
    }

    private void initData() {
        url=getIntent().getStringExtra("url");
        wv_news.loadUrl(url);
    }

    private void initView() {
        wv_news=(WebView)findViewById(R.id.wv_news);
    }
}
