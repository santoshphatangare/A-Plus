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

import com.google.gson.Gson;
import com.ssl.san.a_plus.adapters.ChapterListAdapter;
import com.ssl.san.a_plus.adapters.QuestionPaperListAdapter;
import com.ssl.san.a_plus.beans.ChapterBean;
import com.ssl.san.a_plus.beans.QuestionPaperBean;
import com.ssl.san.a_plus.response.ChapterResponse;
import com.ssl.san.a_plus.response.QuePaperResponse;
import com.ssl.san.a_plus.utils.Constants;
import com.ssl.san.a_plus.utils.GetData;
import com.ssl.san.a_plus.utils.URL;

import java.util.ArrayList;

public class QuePaperListActivity extends BaseActivity {
    ArrayList<QuestionPaperBean> questionPapers = new ArrayList<>();
    QuestionPaperListAdapter adapter;
    ListView questionPaperList;
    RelativeLayout noQuePaperLL, listLL;
    Intent data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_paper_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        data = getIntent();
        String subId = data.getStringExtra("subId");
        String subName = data.getStringExtra("subName");
        getSupportActionBar().setTitle(subName+" Question Papers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new QuestionPaperListAdapter(this, R.layout.que_paper_row, questionPapers);
        questionPaperList = (ListView) findViewById(R.id.questionPaperList);
        noQuePaperLL = (RelativeLayout) findViewById(R.id.noQuePaperLL);
        listLL = (RelativeLayout) findViewById(R.id.listLL);
        questionPaperList.setAdapter(adapter);
        new GetData(this, URL.QUE_PAPERS,"?subjectID="+subId).execute();
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
        if(url.equals(URL.QUE_PAPERS)){
            QuePaperResponse quesPaperResp = new Gson().fromJson(response, QuePaperResponse.class);
            if(quesPaperResp.getCode().equals(Constants.SUCCESS)){
                if(quesPaperResp.getQuestionPapers().size() == 0){
                    listLL.setVisibility(View.GONE);
                    noQuePaperLL.setVisibility(View.VISIBLE);
                } else {
                    questionPapers.addAll(quesPaperResp.getQuestionPapers());
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
