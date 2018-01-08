package com.ssl.san.a_plus.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Santosh on 09-Nov-17.
 */

public class AppData {
    public static final String SP_NAME = "AP_SP";
    public static final String CLASS_ID = "CLASS_ID";
    Context context;
    SharedPreferences sp;
    public AppData(Context context){
        this.context = context;
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public void setClassId(int classID){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(CLASS_ID, classID);
        editor.commit();
    }

    public int getClassId(){
        return sp.getInt(CLASS_ID, 0);
    }
}
