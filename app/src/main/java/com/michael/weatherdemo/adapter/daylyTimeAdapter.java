package com.michael.weatherdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michael.weatherdemo.Beans.WeatherBasicBean;
import com.michael.weatherdemo.ViewHolder.daylyTimeViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Michael on 2017/4/14.
 */

public class daylyTimeAdapter extends RecyclerView.Adapter<daylyTimeViewHolder>{
    private LayoutInflater mInflater;
    private ArrayList<WeatherBasicBean.WeatherBean.FutureBean> datas;
    private Context mContext;
    private int layoutId;

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    private boolean showStatus = false;

    public daylyTimeAdapter(Context context, ArrayList<WeatherBasicBean.WeatherBean.FutureBean> datas, int layoutId){
        this.mContext = context;
        this.datas = datas;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public daylyTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new daylyTimeViewHolder(mInflater.inflate(layoutId,parent,false));
    }

    @Override
    public void onBindViewHolder(daylyTimeViewHolder holder, int position) {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("dd");
        String day =   sDateFormat.format(new Date());
        if(Integer.valueOf(datas.get(position).getDate().substring(8,10))==Integer.valueOf(day)+1){
            holder.setVisiblity(View.VISIBLE);
            holder.date.setText("明天|"+datas.get(position).getDay());
        }
        else if (Integer.valueOf(datas.get(position).getDate().substring(8,10))==Integer.valueOf(day)){
            holder.setVisiblity(View.VISIBLE);
            holder.date.setText("今天|"+datas.get(position).getDay());
        }
        else if(Integer.valueOf(datas.get(position).getDate().substring(8,10))==Integer.valueOf(day)+2){
            holder.setVisiblity(View.VISIBLE);
            holder.date.setText("后天|"+datas.get(position).getDay());
        }
        else if(showStatus){
            holder.setVisiblity(View.VISIBLE);
            holder.date.setText(datas.get(position).getDate().substring(5,10)+"|"+datas.get(position).getDay());
        }
        else{
            holder.setVisiblity(View.GONE);
            holder.date.setText(datas.get(position).getDate().substring(5,10)+"|"+datas.get(position).getDay());
        }

    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }
}
