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
public class GetData extends AsyncTask {
    String finalUrl;
    String result = "";
    String url = "";
    String extendedUrl = "";
    BaseActivity activity;
    Dialog dialog;
    String message = "Loading";

    public GetData(BaseActivity activity, String url, String extendedUrl){
        this.activity = activity;
        this.url = url;
        this.extendedUrl = extendedUrl;
        finalUrl = createURL(url);
    }

    public GetData(BaseActivity activity, String url, String extendedUrl, String message){
        this.activity = activity;
        this.url = url;
        this.extendedUrl = extendedUrl;
        this.message = message;
        finalUrl = createURL(url);
    }

    private String createURL(String callFor) {
        finalUrl = Constants.BASE_URL+callFor+extendedUrl;
        return finalUrl;
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
        dialog.show();
        dialog.setCancelable(false);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Log.e("URL ===>",finalUrl);
        try {
            result = ServerConnection.giveResponse(finalUrl,"");
        } catch (Exception e){
            Log.e("Error doInBackground",e.toString());
            e.printStackTrace();
        }
        Log.e("Response ===>",result);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        dialog.dismiss();
        activity.onGetResponse(result,url);
    }
}
