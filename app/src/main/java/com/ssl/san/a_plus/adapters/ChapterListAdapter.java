package com.ssl.san.a_plus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ssl.san.a_plus.ChaptersActivity;
import com.ssl.san.a_plus.ChooseClassActivity;
import com.ssl.san.a_plus.R;
import com.ssl.san.a_plus.ReaderActivity;
import com.ssl.san.a_plus.beans.ChapterBean;
import com.ssl.san.a_plus.beans.ClassBean;
import com.ssl.san.a_plus.utils.AppData;

import java.util.ArrayList;

/**
 * Created by Santosh on 08-Jan-18.
 */

public class ChapterListAdapter extends ArrayAdapter {
    ChaptersActivity context;
    int resource;
    ArrayList<ChapterBean> chapters;
    LayoutInflater inflater;
    public ChapterListAdapter(ChaptersActivity context, int resource, ArrayList<ChapterBean> chapters) {
        super(context, resource, chapters);
        this.context = context;
        this.resource = resource;
        this.chapters = chapters;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout row = (RelativeLayout) inflater.inflate(resource, null);
        TextView name = (TextView) row.findViewById(R.id.chapterName);
        final TextView index = (TextView) row.findViewById(R.id.chapterIndex);
        TextView tags = (TextView) row.findViewById(R.id.chapterTags);
        name.setText(chapters.get(position).getChapterName());
        tags.setText(chapters.get(position).getChapterTags());
        index.setText(chapters.get(position).getChapterIndex()+"");
        row.setTag(chapters.get(position));
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterBean chapter = (ChapterBean) v.getTag();
                Intent intent = new Intent(context,ReaderActivity.class);
                intent.putExtra("data", new Gson().toJson(chapter));
                context.startActivity(intent);
            }
        });
        return row;
    }
}
