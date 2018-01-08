package com.ssl.san.a_plus;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Santosh on 06-Oct-15.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public abstract void onGetResponse(String response, String url);
    public void mt(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }
}
