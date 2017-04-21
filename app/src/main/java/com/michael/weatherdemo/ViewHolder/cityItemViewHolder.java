package com.michael.weatherdemo.ViewHolder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.michael.weatherdemo.R;
import com.michael.weatherdemo.Utils.CityItemTouchHelperCallback;

/**
 * Created by Michael on 2017/4/17.
 */

public class cityItemViewHolder extends RecyclerView.ViewHolder implements CityItemTouchHelperCallback.onStateChangedListener {
    public ImageButton touch_layout;
    public TextView town_name;
    public ImageButton delete;
    public cityItemViewHolder(View itemView) {
        super(itemView);
        touch_layout = (ImageButton)itemView.findViewById(R.id.bt_swipe);
        town_name = (TextView)itemView.findViewById(R.id.city_name_item);
    }

    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemCleared() {
        itemView.setBackgroundColor(Color.WHITE);
    }
}
