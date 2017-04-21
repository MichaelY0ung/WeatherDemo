package com.michael.weatherdemo.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michael.weatherdemo.R;

/**
 * Created by Michael on 2017/4/13.
 */

public class hourTimeViewHolder extends RecyclerView.ViewHolder{
    public TextView text1;
    public TextView text2;
    public ImageView img1;
    public TextView text3;
    public hourTimeViewHolder(View itemView) {
        super(itemView);
        text1 = (TextView)itemView.findViewById(R.id.temp_hour_text1);
        text2 = (TextView)itemView.findViewById(R.id.temp_hour_text2);
        text3 = (TextView)itemView.findViewById(R.id.temp_hour_text3);
        img1 = (ImageView) itemView.findViewById(R.id.temp_hour_img);
    }
}
