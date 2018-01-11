package com.ssl.san.a_plus.response;

import com.ssl.san.a_plus.beans.ChapterBean;

import java.util.ArrayList;

/**
 * Created by Santosh on 10-Jan-18.
 */

public class ChapterResponse extends Response{
    ArrayList<ChapterBean> chapters = new ArrayList<>();

    public ArrayList<ChapterBean> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<ChapterBean> chapters) {
        this.chapters = chapters;
    }
}
