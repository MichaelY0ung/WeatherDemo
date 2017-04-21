package com.michael.weatherdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael on 2017/4/8.
 */

public class TestAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<String> mDatas;
    private int mLayoutId;
    private LayoutInflater mInflater;

    public TestAdapter(Context context, ArrayList<String> datas, int layoutId){
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public testViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new testViewHolder(mInflater.inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        testViewHolder mHolder = (testViewHolder)holder;
        mHolder.text.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas!=null?mDatas.size():0;
    }
    class testViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public testViewHolder(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.city_name_item);
        }
    }
}
