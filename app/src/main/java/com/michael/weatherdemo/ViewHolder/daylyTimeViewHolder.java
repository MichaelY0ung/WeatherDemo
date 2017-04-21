package com.michael.weatherdemo.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michael.weatherdemo.R;

/**
 * Created by Michael on 2017/4/14.
 */

public class daylyTimeViewHolder extends RecyclerView.ViewHolder{
    public TextView date;
    public TextView weather;
    public TextView temp;
    public LinearLayout layout;
    public daylyTimeViewHolder(View itemView) {
        super(itemView);
        date = (TextView)itemView.findViewById(R.id.dayly_date);
        weather = (TextView)itemView.findViewById(R.id.dayly_weather);
        temp = (TextView)itemView.findViewById(R.id.dayly_temp);
        layout = (LinearLayout)itemView.findViewById(R.id.dayly_layout);
    }
    public void setVisiblity(int visiblity){
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
        layout.setVisibility(visiblity);
        switch (visiblity){
            case View.VISIBLE:
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                break;
            case View.GONE:
                param.height = 0;
                param.width = 0;
                break;
        }
        itemView.setLayoutParams(param);
    }
}
