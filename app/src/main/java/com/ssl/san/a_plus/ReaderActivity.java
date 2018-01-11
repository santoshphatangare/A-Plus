package com.ssl.san.a_plus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ssl.san.a_plus.adapters.ContentListAdapter;
import com.ssl.san.a_plus.beans.ChapterBean;
import com.ssl.san.a_plus.beans.ContentBean;
import com.ssl.san.a_plus.response.ContentResponse;
import com.ssl.san.a_plus.utils.Constants;
import com.ssl.san.a_plus.utils.GetData;
import com.ssl.san.a_plus.utils.LoadImage;
import com.ssl.san.a_plus.utils.URL;

import java.util.ArrayList;

public class ReaderActivity extends BaseActivity {
    ChapterBean chapter;
    ListView contentList;
    ArrayList<ContentBean> content = new ArrayList<>();
    ContentListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chapter = new Gson().fromJson(getIntent().getStringExtra("data"),ChapterBean.class);
        getSupportActionBar().setTitle(chapter.getChapterName());
        contentList = (ListView) findViewById(R.id.contentList);
        adapter = new ContentListAdapter(this, R.layout.content_card, content);
        contentList.setAdapter(adapter);
        new GetData(this, URL.CONTENT, "?chapterId=" + chapter.getChapterId(),"Loading chapter content").execute();
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
        if(url.equals(URL.CONTENT)){
            ContentResponse contentResponse = new Gson().fromJson(response, ContentResponse.class);
            if(contentResponse.getCode().equals(Constants.SUCCESS)){
                content.addAll(contentResponse.getContent());
                adapter.notifyDataSetChanged();
            }
        }
    }
}
