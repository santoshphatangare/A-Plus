package com.ssl.san.a_plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ssl.san.a_plus.utils.AppData;
import com.ssl.san.a_plus.utils.GetData;
import com.ssl.san.a_plus.utils.PostData;
import com.ssl.san.a_plus.utils.URL;

public class HomeActivity extends BaseActivity {

    AppData appData;
    public static boolean classChooseSkip = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new PostData("",this, URL.LOGIN, PostData.HIDE).execute();
        classChooseSkip = false;
        appData = new AppData(this);
        //setContentView(R.layout.activity_home);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //classChooseSkip = false;
        //appData = new AppData(this);
    }

    public void checkClass(){
        if(appData.getClassId() == 0){
            if(classChooseSkip){
                super.onBackPressed();
            } else {
                startActivity(new Intent(getApplicationContext(), ChooseClassActivity.class));
            }
        } else {
            new GetData(this,URL.SUBJECT_LIST,"?classId="+appData.getClassId());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_change_class){
            startActivity(new Intent(getApplicationContext(), ChooseClassActivity.class));
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
    public void onGetResponse(String response, String url) {
        if(url.equals(URL.LOGIN)){
            setContentView(R.layout.activity_home);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            checkClass();
        } else if(url.equals(URL.SUBJECT_LIST)){
            
        }
    }
}
