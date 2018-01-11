package com.ssl.san.a_plus.response;

import com.ssl.san.a_plus.beans.ContentBean;

import java.util.ArrayList;

/**
 * Created by Santosh on 10-Jan-18.
 */

public class ContentResponse extends Response{
    ArrayList<ContentBean> content = new ArrayList<>();

    public ArrayList<ContentBean> getContent() {
        return content;
    }

    public void setContent(ArrayList<ContentBean> content) {
        this.content = content;
    }
}
