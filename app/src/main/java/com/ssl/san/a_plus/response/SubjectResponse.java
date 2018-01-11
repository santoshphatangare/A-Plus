package com.ssl.san.a_plus.response;

import com.ssl.san.a_plus.beans.SubjectBean;

import java.util.ArrayList;

/**
 * Created by Santosh on 09-Jan-18.
 */

public class SubjectResponse extends Response{
    ArrayList<SubjectBean> subjects = new ArrayList<>();

    public ArrayList<SubjectBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<SubjectBean> subjects) {
        this.subjects = subjects;
    }
}
