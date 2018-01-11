package com.ssl.san.a_plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ssl.san.a_plus.adapters.ChapterListAdapter;
import com.ssl.san.a_plus.beans.ChapterBean;
import com.ssl.san.a_plus.response.ChapterResponse;
import com.ssl.san.a_plus.utils.Constants;
import com.ssl.san.a_plus.utils.GetData;
import com.ssl.san.a_plus.utils.URL;

import java.util.ArrayList;

public class ChaptersActivity extends BaseActivity {

    ArrayList<ChapterBean> chapters = new ArrayList<>();
    ChapterListAdapter adapter;
    ListView chapterList;
    TextView noChaptersTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent data = getIntent();
        String subId = data.getStringExtra("subId");
        String subName = data.getStringExtra("subName");
        getSupportActionBar().setTitle(subName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new ChapterListAdapter(this, R.layout.chapter_row, chapters);
        chapterList = (ListView) findViewById(R.id.chpterList);
        noChaptersTV = (TextView) findViewById(R.id.noChaptersTV);
        chapterList.setAdapter(adapter);
        new GetData(this, URL.CHAPTERS,"?subjectID="+subId).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onGetResponse(String response, String url) {
        if(url.equals(URL.CHAPTERS)){
            ChapterResponse chapterResponse = new Gson().fromJson(response, ChapterResponse.class);
            if(chapterResponse.getCode().equals(Constants.SUCCESS)){
                if(chapterResponse.getChapters().size() == 0){
                    chapterList.setVisibility(View.GONE);
                    noChaptersTV.setVisibility(View.VISIBLE);
                } else {
                    chapters.addAll(chapterResponse.getChapters());
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
