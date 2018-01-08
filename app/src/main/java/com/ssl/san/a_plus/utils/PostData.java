package com.ssl.san.a_plus.utils;

import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ssl.san.a_plus.BaseActivity;
import com.ssl.san.a_plus.R;


/**
 * Created by Santosh on 06-Oct-15.
 */
public class PostData extends AsyncTask {
    public static final int SHOW = 0;
    public static final int HIDE = 1;
    String url;
    String result = "";
    String callFor = "";
    String data = "";
    int showOrHide = SHOW;
    BaseActivity activity;
    Dialog dialog;
    String message = "Loading";

    public PostData(String data, BaseActivity activity, String callFor){
        this.activity = activity;
        this.callFor = callFor;
        this.data = data;
        url = createURL(callFor);
    }

    public PostData(String data, BaseActivity activity, String callFor, int showOrHide){
        this.activity = activity;
        this.callFor = callFor;
        this.data = data;
        this.showOrHide = showOrHide;
        url = createURL(callFor);
    }

    public PostData(String data, BaseActivity activity, String callFor, String message){
        this.activity = activity;
        this.callFor = callFor;
        this.data = data;
        this.message = message;
        url = createURL(callFor);
    }

    private String createURL(String callFor) {
        url = Constants.BASE_URL+callFor;
        return url;
    }

    @Override
    protected void onPreExecute() {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progess);
        if(message == null || message.equals("")){
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            dialog.findViewById(R.id.message).setVisibility(View.VISIBLE);
            ((TextView) dialog.findViewById(R.id.message)).setText(message);
        }
        if(showOrHide == SHOW) {
            dialog.show();
        }
        dialog.setCancelable(true);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Log.e("URL ===>",url);
        Log.e("Data ===>",data);
        try {
            result = ServerConnection.giveResponse(url, data);
        } catch (Exception e){
            Log.e("Error doInBackground",e.toString());
            e.printStackTrace();
        }
        Log.e("Response ==>", result);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(showOrHide == SHOW) {
            dialog.dismiss();
        }
        activity.onGetResponse(result,callFor);
    }
}
