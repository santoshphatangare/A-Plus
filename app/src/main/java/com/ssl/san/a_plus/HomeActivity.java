package com.ssl.san.a_plus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ssl.san.a_plus.adapters.SubjectListAdapter;
import com.ssl.san.a_plus.beans.SubjectBean;
import com.ssl.san.a_plus.beans.UserBean;
import com.ssl.san.a_plus.response.LoginResponse;
import com.ssl.san.a_plus.response.Response;
import com.ssl.san.a_plus.response.SubjectResponse;
import com.ssl.san.a_plus.utils.AppData;
import com.ssl.san.a_plus.utils.Constants;
import com.ssl.san.a_plus.utils.GetData;
import com.ssl.san.a_plus.utils.PostData;
import com.ssl.san.a_plus.utils.URL;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    public final int REQUEST_NEW_ACC = 0, REQUEST_CHOOSE_CLASS = 1, REQUEST_ACTIVATE = 2;
    public static final int RESULT_SUCCESS = 0, RESULT_ERROR = 1;
    AppData appData;
    ArrayList<SubjectBean> subjects = new ArrayList<>();
    ListView subjectList;
    SubjectListAdapter adapter;
    int classId = 0;
    RelativeLayout noSubjectsLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        appData = new AppData(this);
        login();
        classId = appData.getClassId();
    }

    public void login(){
        UserBean user = new UserBean();
        user.setMobileNo(appData.getMobileNo());
        user.setPassword(appData.getPassword());
        user.setDeviceId(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
        new PostData(new Gson().toJson(user),this, URL.LOGIN, PostData.HIDE).execute();
    }

    public void checkClass(){
        if(appData.getClassId() == 0){
            startActivityForResult(new Intent(getApplicationContext(), ChooseClassActivity.class),REQUEST_CHOOSE_CLASS);
        } else {
            loadSubjectList();
        }
    }

    public void loadSubjectList(){
        new GetData(this,URL.SUBJECT_LIST,"?classId="+appData.getClassId()).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_change_class){
            startActivityForResult(new Intent(getApplicationContext(), ChooseClassActivity.class), REQUEST_CHOOSE_CLASS);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Exit Application?");
        alert.setMessage("Are you sure to exit application?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                HomeActivity.super.onBackPressed();
            }
        });

        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public void onGetResponse(String response, String url) {
        if(url.equals(URL.LOGIN)){
            LoginResponse loginResponse = new Gson().fromJson(response, LoginResponse.class);
            if(loginResponse.getCode().equals(Constants.SUCCESS)) {
                setContentView(R.layout.activity_home);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                subjectList = (ListView) findViewById(R.id.subjectList);
                noSubjectsLL = (RelativeLayout) findViewById(R.id.noSubjectsLL);
                adapter = new SubjectListAdapter(this, R.layout.subject_row, subjects);
                subjectList.setAdapter(adapter);
                checkClass();
            } else if(loginResponse.getCode().equals(Constants.INACTIVE)) {
                Intent intent = new Intent(getApplicationContext(), ActivateActivity.class);
                intent.putExtra("user",new Gson().toJson(loginResponse.getUser()));
                startActivityForResult(intent, REQUEST_ACTIVATE);
            } else if(loginResponse.getCode().equals(Constants.OTHER)) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("isOnOther",true);
                startActivityForResult(intent, REQUEST_NEW_ACC);
            } else {
                startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class),REQUEST_NEW_ACC);
            }
        } else if(url.equals(URL.SUBJECT_LIST)){
            SubjectResponse subjectResponse = new Gson().fromJson(response, SubjectResponse.class);
            if(subjectResponse.getCode().equals(Constants.SUCCESS)){
                subjects.clear();
                if(subjectResponse.getSubjects().size() == 0){
                    noSubjectsLL.setVisibility(View.VISIBLE);
                    subjectList.setVisibility(View.GONE);
                } else {
                    subjects.addAll(subjectResponse.getSubjects());
                    noSubjectsLL.setVisibility(View.GONE);
                    subjectList.setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_NEW_ACC){
            if(resultCode == RESULT_SUCCESS){
                login();
            } else {
                finish();
            }
        }

        if(requestCode == REQUEST_CHOOSE_CLASS){
            if(resultCode == RESULT_SUCCESS){
                checkClass();
            } else {
                finish();
            }
        }

        if(requestCode == REQUEST_ACTIVATE){
            if(resultCode == RESULT_SUCCESS){
            } else {
                finish();
            }
        }
    }
}
