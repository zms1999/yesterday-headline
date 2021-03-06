package com.java.libingrui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {
    private NewsViewModel mViewModel;
    private News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        if (savedInstanceState != null) {
            return;
        }
        // init Recycler
//        mRecylerView = findViewById(R.id.news_detail_container);
//        mRecylerView.setLayoutManager(new LinearLayoutManager(this));

        // init content view
        TextView tv = findViewById(R.id.news_content);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        // init
        initToolbar();
        initViewModel();

        // send signal to ViewModel
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.ID);
        mViewModel.getNewsById(message);

        TextView textView = findViewById(R.id.news_id);
        textView.setText(message);
    }

    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    public void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        mViewModel.Var_getSelectedNews().observe(this, new Observer<News>() {
            @Override
            public void onChanged(@Nullable final News news) {
                if (news != null) {
                    mNews = news;
                    setText(mNews);
                }
            }
        });
    }

    public void setText(News news) {
        TextView titleView = findViewById(R.id.news_title);
        TextView srcView = findViewById(R.id.news_source);
        TextView dateView = findViewById(R.id.news_date);
        TextView contentView = findViewById(R.id.news_content);

        titleView.setText(news.title);
        contentView.setText(news.content);
        srcView.setText(news.source);
        dateView.setText(news.date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.news_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onShare(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_SEND);


        String toShare = mNews.content.substring(0, 30) + "...";
        intent.putExtra(Intent.EXTRA_TEXT, toShare);
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, null);
        startActivity(shareIntent);
    }
}
