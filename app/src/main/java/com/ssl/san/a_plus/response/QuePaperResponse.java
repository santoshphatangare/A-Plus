package com.ssl.san.a_plus.response;

import com.ssl.san.a_plus.beans.QuestionPaperBean;

import java.util.ArrayList;

/**
 * Created by Santosh on 16-Jan-18.
 */

public class QuePaperResponse extends Response {
    ArrayList<QuestionPaperBean> questionPapers = new ArrayList<>();

    public ArrayList<QuestionPaperBean> getQuestionPapers() {
        return questionPapers;
    }

    public void setQuestionPapers(ArrayList<QuestionPaperBean> questionPapers) {
        this.questionPapers = questionPapers;
    }
}
