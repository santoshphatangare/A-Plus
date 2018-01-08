package com.ssl.san.a_plus;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ssl.san.a_plus.beans.ClassBean;
import com.ssl.san.a_plus.response.ClassListResponse;
import com.ssl.san.a_plus.utils.AppData;
import com.ssl.san.a_plus.utils.Constants;
import com.ssl.san.a_plus.utils.GetData;
import com.ssl.san.a_plus.utils.URL;

import java.util.ArrayList;

public class ChooseClassActivity extends BaseActivity {

    ClassListAdapter adapter;
    ArrayList<ClassBean> classes = new ArrayList<>();
    ListView classList;
    LinearLayout noClassLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        classList = (ListView) findViewById(R.id.classList);
        noClassLL = (LinearLayout) findViewById(R.id.noClassLL);
        adapter = new ClassListAdapter(this,R.layout.class_row,classes);
        classList.setAdapter(adapter);
        new GetData(this, URL.CLASS_LIST,"","Loading Class List").execute();
    }

    public void showNoClassesUi(){
        noClassLL.setVisibility(View.VISIBLE);
        classList.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        HomeActivity.classChooseSkip = true;
        super.onBackPressed();
    }

    @Override
    public void onGetResponse(String response, String url) {
        if(url.equals(URL.CLASS_LIST)){
            ClassListResponse classListResponse = new Gson().fromJson(response, ClassListResponse.class);
            if(classListResponse.getCode().equals(Constants.SUCCESS)){
                if(classListResponse.getClasses().size()!=0) {
                    classes.addAll(classListResponse.getClasses());
                    adapter.notifyDataSetChanged();
                } else {
                    showNoClassesUi();
                }
            } else if(classListResponse.getCode().equals(Constants.SERVER_ERROR)){
                Toast.makeText(getApplicationContext(),classListResponse.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
