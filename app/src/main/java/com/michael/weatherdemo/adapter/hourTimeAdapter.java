package com.michael.weatherdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.michael.weatherdemo.Beans.WeatherTimeBean;
import com.michael.weatherdemo.ViewHolder.hourTimeViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Michael on 2017/4/13.
 */

public class hourTimeAdapter extends RecyclerView.Adapter<hourTimeViewHolder>{
    private LayoutInflater mInflater;
    private ArrayList<WeatherTimeBean.HourlyBean> datas;
    private Context mContext;
    private int layoutId;

    public hourTimeAdapter(Context context, ArrayList<WeatherTimeBean.HourlyBean> datas, int layoutId){
        this.mContext = context;
        this.datas = datas;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public hourTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new hourTimeViewHolder(mInflater.inflate(layoutId,parent,false));
    }

    @Override
    public void onBindViewHolder(hourTimeViewHolder holder, int position) {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("HH");
        String hour =   sDateFormat.format(new Date());
        if(Integer.valueOf(datas.get(position).getTime().substring(11,13))>Integer.valueOf(hour)){
            holder.text1.setText(datas.get(position).getTime().substring(11,16));
        }
        else{
            holder.text1.setText("明日 "+datas.get(position).getTime().substring(11,16));
        }
        holder.text2.setText(datas.get(position).getTemperature()+"°");
        holder.text3.setText(datas.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }
}
