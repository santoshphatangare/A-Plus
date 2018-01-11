package com.ssl.san.a_plus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssl.san.a_plus.ChooseClassActivity;
import com.ssl.san.a_plus.ExtraActivity;
import com.ssl.san.a_plus.R;
import com.ssl.san.a_plus.ReaderActivity;
import com.ssl.san.a_plus.beans.ClassBean;
import com.ssl.san.a_plus.beans.ContentBean;
import com.ssl.san.a_plus.utils.Constants;
import com.ssl.san.a_plus.utils.LoadImage;

import java.util.ArrayList;

/**
 * Created by Santosh on 11-Jan-18.
 */

public class ContentListAdapter extends ArrayAdapter{

    ReaderActivity context;
    int resource;
    ArrayList<ContentBean> content;
    LayoutInflater inflater;
    public ContentListAdapter(ReaderActivity context, int resource, ArrayList<ContentBean> content) {
        super(context, resource, content);
        this.context = context;
        this.resource = resource;
        this.content = content;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout card = (LinearLayout) inflater.inflate(resource,null);
        TextView contentHeader = (TextView) card.findViewById(R.id.contentHeader);
        TextView contentDesc = (TextView) card.findViewById(R.id.contentDesc);
        ImageView contentImage = (ImageView) card.findViewById(R.id.contentImage);

        ImageView author = (ImageView) card.findViewById(R.id.author);
        ImageView questions = (ImageView) card.findViewById(R.id.questions);
        ImageView download = (ImageView) card.findViewById(R.id.download);
        ImageView video = (ImageView) card.findViewById(R.id.video);
        ImageView web = (ImageView) card.findViewById(R.id.web);
        if(content.get(position).getAuthorResult().trim().equals("")){
            author.setVisibility(View.GONE);
        }
        questions.setVisibility(View.GONE);
        download.setVisibility(View.GONE);

        if(content.get(position).getVideoResult().trim().equals("")){
            video.setVisibility(View.GONE);
        }
        if(content.get(position).getWebResult().trim().equals("")){
            web.setVisibility(View.GONE);
        }
        author.setTag(content.get(position));
        video.setTag(content.get(position));
        web.setTag(content.get(position));

        author.setOnClickListener(clickListener);
        video.setOnClickListener(clickListener);
        web.setOnClickListener(clickListener);

        if(content.get(position).getContentImage().trim().equals("")){
            contentImage.setVisibility(View.GONE);
        } else {
            if(!content.get(position).getContentImage().startsWith("http")){
                content.get(position).setContentImage(Constants.BASE_URL+content.get(position).getContentImage());
            }
            Log.e("IMAGE URL",content.get(position).getContentImage());
            new LoadImage().loadImage(context,R.drawable.app_icon,content.get(position).getContentImage(),contentImage,null);
        }
        contentHeader.setText(content.get(position).getContentTitle());
        contentDesc.setText(content.get(position).getContent());
        return card;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentBean content = (ContentBean) v.getTag();
            String title = "";
            String url = "";
            if(v.getId() == R.id.video){
                title = "Related Video";
                url = content.getVideoResult();
            } else if(v.getId() == R.id.author){
                title = "About Aunthor";
                url = content.getAuthorResult();
            } else if(v.getId() == R.id.web){
                title = "Web Information";
                url = content.getWebResult();
            }
            Intent intent = new Intent(context, ExtraActivity.class);
            intent.putExtra("title",title);
            intent.putExtra("url",url);
            context.startActivity(intent);
        }
    };
}
