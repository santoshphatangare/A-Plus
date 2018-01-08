package com.ssl.san.a_plus.response;

import com.ssl.san.a_plus.beans.ClassBean;

import java.util.ArrayList;

/**
 * Created by Santosh on 08-Jan-18.
 */

public class ClassListResponse extends Response {
    ArrayList<ClassBean> classes = new ArrayList<>();

    public ArrayList<ClassBean> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassBean> classes) {
        this.classes = classes;
    }
}
