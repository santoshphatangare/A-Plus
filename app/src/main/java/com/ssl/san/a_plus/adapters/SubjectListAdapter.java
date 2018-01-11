package com.ssl.san.a_plus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ssl.san.a_plus.ChaptersActivity;
import com.ssl.san.a_plus.ChooseClassActivity;
import com.ssl.san.a_plus.HomeActivity;
import com.ssl.san.a_plus.R;
import com.ssl.san.a_plus.beans.ClassBean;
import com.ssl.san.a_plus.beans.SubjectBean;
import com.ssl.san.a_plus.utils.AppData;
import com.ssl.san.a_plus.utils.Constants;
import com.ssl.san.a_plus.utils.LoadImage;

import java.util.ArrayList;

/**
 * Created by Santosh on 09-Jan-18.
 */

public class SubjectListAdapter extends ArrayAdapter {
    HomeActivity context;
    int resource;
    ArrayList<SubjectBean> subjects;
    LayoutInflater inflater;
    public SubjectListAdapter(HomeActivity context, int resource, ArrayList<SubjectBean> subjects) {
        super(context, resource, subjects);
        this.context = context;
        this.resource = resource;
        this.subjects = subjects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout row = (RelativeLayout) inflater.inflate(resource, null);
        TextView name = (TextView) row.findViewById(R.id.subjectName);
        ImageView subImg = (ImageView) row.findViewById(R.id.subjectImage);
        new LoadImage().loadImage(context, R.drawable.app_icon, Constants.BASE_URL+subjects.get(position).getSubjectImg(), subImg, null);
        name.setText(subjects.get(position).getSubjectName());
        row.setTag(subjects.get(position));
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectBean sub = (SubjectBean) v.getTag();
                Intent chapters = new Intent(context.getApplicationContext(), ChaptersActivity.class);
                chapters.putExtra("subId",sub.getSubjectId()+"");
                chapters.putExtra("subName",sub.getSubjectName()+"");
                context.startActivity(chapters);
            }
        });
        return row;
    }
}