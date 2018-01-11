package com.ssl.san.a_plus.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssl.san.a_plus.ChooseClassActivity;
import com.ssl.san.a_plus.R;
import com.ssl.san.a_plus.beans.ClassBean;
import com.ssl.san.a_plus.utils.AppData;

import java.util.ArrayList;

/**
 * Created by Santosh on 08-Jan-18.
 */

public class ClassListAdapter extends ArrayAdapter {
    ChooseClassActivity context;
    int resource;
    ArrayList<ClassBean> classes;
    LayoutInflater inflater;
    public ClassListAdapter(ChooseClassActivity context, int resource, ArrayList<ClassBean> classes) {
        super(context, resource, classes);
        this.context = context;
        this.resource = resource;
        this.classes = classes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout row = (LinearLayout) inflater.inflate(resource, null);
        TextView name = (TextView) row.findViewById(R.id.className);
        TextView board = (TextView) row.findViewById(R.id.classBoard);
        name.setText(classes.get(position).getClassName());
        board.setText(classes.get(position).getClassBoard());
        row.setTag(classes.get(position).getClassId());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int classId = (int) v.getTag();
                new AppData(context).setClassId(classId);
                context.onBackPressed();
            }
        });
        return row;
    }
}
