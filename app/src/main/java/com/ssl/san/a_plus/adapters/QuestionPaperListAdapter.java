package com.ssl.san.a_plus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ssl.san.a_plus.ChaptersActivity;
import com.ssl.san.a_plus.QuePaperListActivity;
import com.ssl.san.a_plus.R;
import com.ssl.san.a_plus.ReaderActivity;
import com.ssl.san.a_plus.beans.ChapterBean;
import com.ssl.san.a_plus.beans.QuestionPaperBean;

import java.util.ArrayList;

/**
 * Created by Santosh on 16-Jan-18.
 */

public class QuestionPaperListAdapter extends ArrayAdapter {
    QuePaperListActivity context;
    int resource;
    ArrayList<QuestionPaperBean> quePapers;
    LayoutInflater inflater;
    public QuestionPaperListAdapter(QuePaperListActivity context, int resource, ArrayList<QuestionPaperBean> quePapers) {
        super(context, resource, quePapers);
        this.context = context;
        this.resource = resource;
        this.quePapers = quePapers;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout row = (RelativeLayout) inflater.inflate(resource, null);
        TextView examYear = (TextView) row.findViewById(R.id.examYear);
        examYear.setText(quePapers.get(position).getQuePaperName());
        row.setTag(quePapers.get(position));
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionPaperBean quePaper  = (QuestionPaperBean) v.getTag();
//                Intent intent = new Intent(context,ReaderActivity.class);
//                intent.putExtra("quePaperId", new Gson().toJson(quePaper));
//                context.startActivity(intent);
            }
        });
        return row;
    }
}
