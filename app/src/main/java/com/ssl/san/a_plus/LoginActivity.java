package com.ssl.san.a_plus;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ssl.san.a_plus.beans.UserBean;
import com.ssl.san.a_plus.response.Response;
import com.ssl.san.a_plus.utils.AppData;
import com.ssl.san.a_plus.utils.Constants;
import com.ssl.san.a_plus.utils.PostData;
import com.ssl.san.a_plus.utils.URL;

public class LoginActivity extends BaseActivity {

    EditText mobileNo, userName, password;
    TextView otherMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobileNo = (EditText) findViewById(R.id.mobileNo);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        otherMessage = (TextView) findViewById(R.id.otherMessage);
        boolean isOnOther = getIntent().getBooleanExtra("isOnOther", false);
        if(isOnOther){
            otherMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        goBack(HomeActivity.RESULT_ERROR);
    }

    public void makeAccount(View v){
        if(mobileNo.getText().toString().trim().length()!=10){
            Toast.makeText(getApplicationContext(),"Enter Valid Mobile Number", Toast.LENGTH_LONG).show();
            mobileNo.requestFocus();
            return;
        }

        if(userName.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"Enter Your Name", Toast.LENGTH_LONG).show();
            userName.requestFocus();
            return;
        }

        if(password.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"Enter Your Password", Toast.LENGTH_LONG).show();
            password.requestFocus();
            return;
        }
        UserBean user = new UserBean();
        user.setMobileNo(mobileNo.getText().toString());
        user.setUserName(userName.getText().toString());
        user.setPassword(password.getText().toString());
        user.setDeviceId(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
        new PostData(new Gson().toJson(user), this, URL.NEW_USER,"Creating Account").execute();
    }

    @Override
    public void onGetResponse(String response, String url) {
        if(url.equals(URL.NEW_USER)){
            Response newAccResp = new Gson().fromJson(response, Response.class);
            Toast.makeText(getApplicationContext(),newAccResp.getMessage(),Toast.LENGTH_LONG).show();
            new AppData(this).setMobileNo(mobileNo.getText().toString());
            new AppData(this).setPassword(password.getText().toString());
            if(newAccResp.getCode().equals(Constants.SUCCESS)){
                goBack(HomeActivity.RESULT_SUCCESS);
            }
        }
    }

    public void goBack(int result){
        setResult(result);
        finish();
    }
}
