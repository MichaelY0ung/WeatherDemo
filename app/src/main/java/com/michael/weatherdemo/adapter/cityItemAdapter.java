package com.michael.weatherdemo.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.michael.weatherdemo.Beans.townInfo;
import com.michael.weatherdemo.Utils.CityItemTouchHelperCallback;
import com.michael.weatherdemo.ViewHolder.cityItemViewHolder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michael on 2017/4/17.
 */

public class cityItemAdapter extends RecyclerView.Adapter<cityItemViewHolder> implements CityItemTouchHelperCallback.onMoveAndSwipedListener {
    private LayoutInflater mInflater;
    private ArrayList<townInfo> datas;
    private Context mContext;
    private int layoutId;

    public CityAdapterListener getCityAdapterListener() {
        return cityAdapterListener;
    }

    public void setCityAdapterListener(CityAdapterListener cityAdapterListener) {
        this.cityAdapterListener = cityAdapterListener;
    }
    private CityAdapterListener cityAdapterListener;
    public cityItemAdapter(Context context, ArrayList<townInfo> datas,int layoutId) {
        this.mContext = context;
        this.datas = datas;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public cityItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new cityItemViewHolder(mInflater.inflate(layoutId,parent,false));
    }

    @Override
    public void onBindViewHolder(final cityItemViewHolder holder, int position) {
            holder.touch_layout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                        //回调RecyclerListFragment中的startDrag方法
                        //让mItemTouchHelper执行拖拽操作
                        cityAdapterListener.onSwipe(holder);
                    }
                    return false;
                }
            });
        holder.town_name.setText(datas.get(position).getTownName());
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(datas,fromPosition,toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        //删除mItems数据
        datas.remove(position);
        //删除RecyclerView列表对应item
        notifyItemRemoved(position);
    }

    public interface CityAdapterListener {
        void onDel(int pos);
        void onSwipe(cityItemViewHolder holder);
    }
}
