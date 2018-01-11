package com.ssl.san.a_plus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ssl.san.a_plus.beans.UserBean;
import com.ssl.san.a_plus.response.Response;
import com.ssl.san.a_plus.utils.GetData;
import com.ssl.san.a_plus.utils.URL;

public class ActivateActivity extends BaseActivity {
    TextView actName, actMobile, actId;
    UserBean user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = new Gson().fromJson(getIntent().getStringExtra("user"), UserBean.class);
        actName = (TextView) findViewById(R.id.actName);
        actMobile = (TextView) findViewById(R.id.actMobile);
        actId = (TextView) findViewById(R.id.actId);
        actName.setText(user.getUserName());
        actMobile.setText(user.getMobileNo());
        actId.setText("ID : "+user.getUserId());
    }

    public void activateAccount(View v){
        new GetData(this, URL.ACTIVATE_REQUEST,"?userId="+user.getUserId()).execute();
    }

    @Override
    public void onBackPressed() {
        setResult(HomeActivity.RESULT_ERROR);
        finish();
    }

    @Override
    public void onGetResponse(String response, String url) {
        if(url.equals(URL.ACTIVATE_REQUEST)){
            Toast.makeText(getApplicationContext(),"Request submitted",Toast.LENGTH_SHORT).show();
        }
    }
}
