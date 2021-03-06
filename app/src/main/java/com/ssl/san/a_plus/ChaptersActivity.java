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
import android.widget.RelativeLayout;
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
    RelativeLayout noChaptersLL, listLL;
    Intent data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        data = getIntent();
        String subId = data.getStringExtra("subId");
        String subName = data.getStringExtra("subName");
        getSupportActionBar().setTitle(subName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new ChapterListAdapter(this, R.layout.chapter_row, chapters);
        chapterList = (ListView) findViewById(R.id.chpterList);
        noChaptersLL = (RelativeLayout) findViewById(R.id.noChaptersLL);
        listLL = (RelativeLayout) findViewById(R.id.listLL);
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

    public void showQuestionPapers(View v){
        Intent intent = new Intent(getApplicationContext(), QuePaperListActivity.class);
        intent.putExtra("subId",data.getStringExtra("subId"));
        intent.putExtra("subName",data.getStringExtra("subName"));
        startActivity(intent);
    }

    @Override
    public void onGetResponse(String response, String url) {
        if(url.equals(URL.CHAPTERS)){
            ChapterResponse chapterResponse = new Gson().fromJson(response, ChapterResponse.class);
            if(chapterResponse.getCode().equals(Constants.SUCCESS)){
                if(chapterResponse.getChapters().size() == 0){
                    listLL.setVisibility(View.GONE);
                    noChaptersLL.setVisibility(View.VISIBLE);
                } else {
                    chapters.addAll(chapterResponse.getChapters());
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
