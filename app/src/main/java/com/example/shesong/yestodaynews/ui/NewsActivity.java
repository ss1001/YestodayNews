package com.example.shesong.yestodaynews.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.shesong.yestodaynews.R;

public class NewsActivity extends AppCompatActivity {
    private WebView wv_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        initData();
    }

    private void initData() {
        wv_news.loadUrl("http://news.sohu.com/20171114/n522403931.shtml");
    }

    private void initView() {
        wv_news=(WebView)findViewById(R.id.wv_news);
    }
}
