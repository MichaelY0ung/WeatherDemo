package com.michael.weatherdemo.Utils;

import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Michael on 2017/4/17.
 */

public class CityItemTouchHelperCallback extends ItemTouchHelper.Callback{
    private onMoveAndSwipedListener mAdapter;

    public CityItemTouchHelperCallback(onMoveAndSwipedListener mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            //设置拖拽方向为上下
            final int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            //设置侧滑方向为从左到右和从右到左都可以
            final int swipeFlags = ItemTouchHelper.START|ItemTouchHelper.END;
            //将方向参数设置进去
            return makeMovementFlags(dragFlags,swipeFlags);
        }
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }
        //回调adapter中的onItemMove方法
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
    public interface onMoveAndSwipedListener {
        boolean onItemMove(int fromPosition , int toPosition);
        void onItemDismiss(int position);
    }
    public interface onStateChangedListener {
        void onItemSelected();
        void onItemCleared();
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            //根据侧滑的位移来修改item的透明度
            final float alpha = 1.0f - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        }
        else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof onStateChangedListener){
            onStateChangedListener listener = (onStateChangedListener)viewHolder;
            listener.onItemCleared();
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            if (viewHolder instanceof onStateChangedListener){
                onStateChangedListener listener = (onStateChangedListener)viewHolder;
                //回调ItemViewHolder中的onItemSelected方法来改变item的背景颜色
                listener.onItemSelected();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }
}
