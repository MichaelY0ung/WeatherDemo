package com.michael.weatherdemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.michael.weatherdemo.Beans.WeatherBasicBean;
import com.michael.weatherdemo.Beans.WeatherTimeBean;
import com.michael.weatherdemo.Beans.dbInfo;
import com.michael.weatherdemo.Beans.weatherInfoBean;
import com.michael.weatherdemo.DB.WeatherSqliteOpenHelper;
import com.michael.weatherdemo.Utils.WeatherDao;
import com.michael.weatherdemo.Utils.utils;
import com.michael.weatherdemo.adapter.daylyTimeAdapter;
import com.michael.weatherdemo.adapter.hourTimeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michael on 2017/4/14.
 */

public class WeatherFragment extends android.support.v4.app.Fragment{
    private ArrayList<WeatherTimeBean.HourlyBean> hourlyList;
    private hourTimeAdapter HourlyAdapter;
    private WeatherBasicBean basicBean;
    private WeatherTimeBean timeBean;
    private String townId;
    private RecyclerView hourly_rv;
    private RecyclerView dayly_rv;
    private ArrayList<WeatherBasicBean.WeatherBean.FutureBean> daylyList;
    private daylyTimeAdapter DaylyAdapter;
    private TextView tv_show_day;
    private boolean show_day_status = false;
    private TextView tv_temp;
    private TextView tv_temp_feeling;
    private TextView tv_update_time;
    private TextView tv_air_quality;
    private TextView tv_aqi;
    private TextView tv_pm25;
    private TextView tv_sunrise;
    private TextView tv_sunset;
    private TextView tv_humidity;
    private TextView tv_visibility;
    private TextView tv_wind;
    private TextView tv_pressure;
    private TextView tv_dressing_brief;
    private TextView tv_dressing_detail;
    private TextView tv_uv_brief;
    private TextView tv_uv_detail;
    private TextView tv_washingcar_brief;
    private TextView tv_washingcar_detail;
    private TextView tv_travel_brief;
    private TextView tv_travel_detail;
    private TextView tv_sports_brief;
    private TextView tv_sports_detail;
    private TextView tv_flu_brief;
    private TextView tv_flu_detail;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean freshStatus = false;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if(swipeRefreshLayout.isRefreshing()){
                        swipeRefreshLayout.setRefreshing(false);
                        setRecentView();
                    }
                    break;
            }
        }
    };
    private WeatherSqliteOpenHelper weatherSqliteOpenHelper;
    private SQLiteDatabase db;
    private WeatherDao dao;
    private weatherInfoBean saveBean;
    private int count;

    public FragmentListener getmFragmentListener() {
        return mFragmentListener;
    }

    public void setmFragmentListener(FragmentListener mFragmentListener) {
        this.mFragmentListener = mFragmentListener;
    }

    private String basic;
    private Gson gson;
    private String time;
    private FragmentListener mFragmentListener;

    static WeatherFragment newInstance(String s,int count) {
        WeatherFragment newFragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("townId", s);
        bundle.putInt("count",count);
        newFragment.setArguments(bundle);
        //bundle还可以在每个标签里传送数据


        return newFragment;

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_viewpager, container, false);
        Bundle args = getArguments();
        townId = args.getString("townId");
        count = args.getInt("count");
        Log.d("11111",townId+"lalala"+count);
        weatherSqliteOpenHelper = new WeatherSqliteOpenHelper(getActivity());
        db = weatherSqliteOpenHelper.getWritableDatabase();
        dao = new WeatherDao(db);
        saveBean = new weatherInfoBean();
        saveBean.setId(townId);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        gson = new Gson();
        hourly_rv = (RecyclerView)view.findViewById(R.id.rv_hourly);
        dayly_rv = (RecyclerView)view.findViewById(R.id.rv_dayly);
        tv_show_day = (TextView)view.findViewById(R.id.show_more_day);
        tv_temp=(TextView)view.findViewById(R.id.temp);
        tv_temp_feeling=(TextView)view.findViewById(R.id.temp_feeling);
        tv_update_time = (TextView)view.findViewById(R.id.tv_updatetime);
        tv_air_quality = (TextView)view.findViewById(R.id.tv_air_quality);
        tv_aqi = (TextView)view.findViewById(R.id.tv_aqi);
        tv_pm25=(TextView)view.findViewById(R.id.tv_pm25);
        tv_sunrise = (TextView)view.findViewById(R.id.tv_sunrise);
        tv_sunset = (TextView)view.findViewById(R.id.tv_sunset);
        tv_wind = (TextView)view.findViewById(R.id.tv_wind);
        tv_humidity = (TextView)view.findViewById(R.id.tv_humidity);
        tv_visibility = (TextView)view.findViewById(R.id.tv_visibility);
        tv_pressure = (TextView)view.findViewById(R.id.tv_pressure);
        tv_dressing_brief = (TextView)view.findViewById(R.id.dressing_brief);
        tv_dressing_detail = (TextView)view.findViewById(R.id.dressing_detail);
        tv_uv_brief = (TextView)view.findViewById(R.id.uv_brief);
        tv_uv_detail = (TextView)view.findViewById(R.id.uv_detail);
        tv_washingcar_brief = (TextView)view.findViewById(R.id.washingcar_brief);
        tv_washingcar_detail = (TextView)view.findViewById(R.id.washingcar_detail);
        tv_flu_brief = (TextView)view.findViewById(R.id.flu_brief);
        tv_flu_detail = (TextView)view.findViewById(R.id.flu_detail);
        tv_travel_brief = (TextView)view.findViewById(R.id.travel_brief);
        tv_travel_detail = (TextView)view.findViewById(R.id.travel_detail);
        tv_sports_brief = (TextView)view.findViewById(R.id.sports_brief);
        tv_sports_detail = (TextView)view.findViewById(R.id.sports_detail);
        hourly_rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        dayly_rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        hourlyList = new ArrayList<>();
        daylyList = new ArrayList<>();
        HourlyAdapter = new hourTimeAdapter(getActivity(),hourlyList,R.layout.hour_weather_item);
        DaylyAdapter = new daylyTimeAdapter(getActivity(),daylyList,R.layout.day_weather_item);
        hourly_rv.setAdapter(HourlyAdapter);
        dayly_rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        dayly_rv.setAdapter(DaylyAdapter);
        tv_show_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newStatus = !show_day_status;
                DaylyAdapter.setShowStatus(newStatus);
                DaylyAdapter.notifyDataSetChanged();
               show_day_status = newStatus;
                if(newStatus){
                    tv_show_day.setText("隐藏");
                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeatherData();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message msg = Message.obtain();
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    }
                }).start();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = Message.obtain();
                msg.what = 0;
                mHandler.sendMessage(msg);
            }
        }).start();
        getWeatherData();
        return view;
    }

    private void getWeatherData() {
        if (utils.isNetWorkAvalible(getActivity())) {
            Retrofit retrofitBasic = new Retrofit.Builder()
                    .baseUrl("http://tj.nineton.cn/Heart/index/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Retrofit retrofitTime = new Retrofit.Builder()
                    .baseUrl("http://tj.nineton.cn/Heart/index/future24h/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            WeatherBasicApi weatherBasicApicApi = retrofitBasic.create(WeatherBasicApi.class);
            WeatherTimeApi weatherTimeApi = retrofitTime.create(WeatherTimeApi.class);
            Call<WeatherBasicBean> callBasic = weatherBasicApicApi.weatherBasicGetCall(townId);
            callBasic.enqueue(new Callback<WeatherBasicBean>() {
                @Override
                public void onResponse(Call<WeatherBasicBean> call, Response<WeatherBasicBean> response) {
                    basicBean = response.body();
                    //text.setText(basicBean.getWeather().get(0).getLast_update());
                    setBasicView(basicBean);
                    saveBean.setBasicBean(basicBean);
                    if(!freshStatus){
                        freshStatus = true;
                    }
                    else{
                        freshStatus = false;
                        swipeRefreshLayout.setRefreshing(false);
                        if(mFragmentListener!=null){
                            mFragmentListener.onSave(saveBean,count);
                        }
                    }
                }
                @Override
                public void onFailure(Call<WeatherBasicBean> call, Throwable t) {
                    Log.d("111111", t.getMessage()+"error"+townId);
                }
            });
            Call<WeatherTimeBean> call = weatherTimeApi.weatherTimeGetCall(townId);
            call.enqueue(new Callback<WeatherTimeBean>() {
                @Override
                public void onResponse(Call<WeatherTimeBean> call, Response<WeatherTimeBean> response) {
                    timeBean = response.body();
                    setTimeView(timeBean);
                    saveBean.setTimeBean(timeBean);
                    if(!freshStatus){
                        freshStatus = true;
                    }
                    else{
                        freshStatus = false;
                        swipeRefreshLayout.setRefreshing(false);
                        if(mFragmentListener!=null){
                            mFragmentListener.onSave(saveBean,count);
                        }
                    }

                }

                @Override
                public void onFailure(Call<WeatherTimeBean> call, Throwable t) {
                    Log.d("111111", t.getMessage()+"errortime");
                }
            });
        } else {
            Toast.makeText(getActivity(), "当前网络无连接！", Toast.LENGTH_SHORT).show();
            setRecentView();
        }
    }

    private void setRecentView() {
        String data = dao.query();
        if(data.equals("")){
            Toast.makeText(getActivity(),"数据更新失败",Toast.LENGTH_SHORT).show();
        }
        else {
            Gson gson = new Gson();
            dbInfo info = gson.fromJson(data, dbInfo.class);
            Log.d("11111",count+"");
            basicBean = info.getInfoBeanList().get(count).getBasicBean();
            timeBean = info.getInfoBeanList().get(count).getTimeBean();
            setBasicView(basicBean);
            setTimeView(timeBean);
        }
    }

    private void setTimeView(WeatherTimeBean timeBean) {
        hourlyList.clear();
        SimpleDateFormat sDateFormat=new SimpleDateFormat("HH");
        String hour =   sDateFormat.format(new Date());
        boolean hourStatus = false;
        for(WeatherTimeBean.HourlyBean bean:timeBean.getHourly()){
            if(hourStatus){
                hourlyList.add(bean);
            }
            else if (Integer.valueOf(bean.getTime().substring(11,13))==Integer.valueOf(hour)){
                hourStatus = true;
            }
        }
        Log.d("99999",hourlyList.size()+"");
        HourlyAdapter.notifyDataSetChanged();
    }

    private void setBasicView(WeatherBasicBean basicBean) {
        boolean dayStatus = false;
        SimpleDateFormat sDateFormat = new SimpleDateFormat("dd");
        String day = sDateFormat.format(new Date());
        daylyList.clear();
        for (WeatherBasicBean.WeatherBean.FutureBean bean : basicBean.getWeather().get(0).getFuture()) {
            if (dayStatus) {
                daylyList.add(bean);
            } else if (Integer.valueOf(bean.getDate().substring(8, 10)) == Integer.valueOf(day)) {
                daylyList.add(bean);
                dayStatus = true;
            }
            DaylyAdapter.notifyDataSetChanged();
            tv_temp.setText(basicBean.getWeather().get(0).getNow().getTemperature()+"℃");
            tv_temp_feeling.setText("体感温度："+(basicBean.getWeather().get(0).getNow().getFeels_like()!=null?basicBean.getWeather().get(0).getNow().getFeels_like()+"℃":"暂无数据"));
            tv_update_time.setText("更新时间:"+basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getLast_update().substring(0,19));
            tv_pm25.setText(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getPm25()!=null?basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getPm25():"无");
            tv_aqi.setText(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getAqi()!=null?basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getAqi():"无");
            tv_air_quality.setText(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getQuality()!=null?basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getQuality():"无");
            if(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getQuality()==null){
                tv_air_quality.setText("暂无");
            }
            else if(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getQuality().equals("优")){
                tv_air_quality.setTextColor(getResources().getColor(R.color.colorHighQuality));
            }
            else if(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getQuality().equals("良")){
                tv_air_quality.setTextColor(getResources().getColor(R.color.colorMediumQuality));
            }
            else{
                tv_air_quality.setTextColor(getResources().getColor(R.color.colorLowQuality));
            }
            if(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getQuality()!=null&&basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getQuality().length()>1){
                tv_air_quality.setTextSize(TypedValue.COMPLEX_UNIT_SP ,16);
            }
            else{
                tv_air_quality.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
            }
            if(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getAqi()!=null) {
                if (Integer.valueOf(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getAqi()) > 100) {
                    tv_aqi.setTextColor(getResources().getColor(R.color.colorLowQuality));
                } else if (Integer.valueOf(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getAqi()) > 50) {
                    tv_aqi.setTextColor(getResources().getColor(R.color.colorMediumQuality));
                } else {
                    tv_aqi.setTextColor(getResources().getColor(R.color.colorHighQuality));
                }
            }
            if(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getPm25()!=null) {
                if (Integer.valueOf(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getPm25()) > 75) {
                    tv_pm25.setTextColor(getResources().getColor(R.color.colorLowQuality));
                } else if (Integer.valueOf(basicBean.getWeather().get(0).getNow().getAir_quality().getCity().getPm25()) > 35) {
                    tv_pm25.setTextColor(getResources().getColor(R.color.colorMediumQuality));
                } else {
                    tv_pm25.setTextColor(getResources().getColor(R.color.colorHighQuality));
                }
            }
            tv_sunrise.setText(basicBean.getWeather().get(0).getToday().getSunrise());
            tv_sunset.setText(basicBean.getWeather().get(0).getToday().getSunset());
            tv_wind.setText(basicBean.getWeather().get(0).getNow().getWind_direction()+"风"+" "+basicBean.getWeather().get(0).getNow().getWind_scale()+"级"+"("+basicBean.getWeather().get(0).getNow().getWind_speed()+"km/h)");
            tv_visibility.setText(basicBean.getWeather().get(0).getNow().getVisibility()+"km");
            tv_pressure.setText(basicBean.getWeather().get(0).getNow().getPressure()+"hPa");
            tv_humidity.setText(basicBean.getWeather().get(0).getNow().getHumidity()+"%rh");
            tv_dressing_brief.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getDressing().getBrief());
            tv_dressing_detail.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getDressing().getDetails());
            tv_uv_brief.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getUv().getBrief());
            tv_uv_detail.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getUv().getDetails());
            tv_sports_brief.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getSport().getBrief());
            tv_sports_detail.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getSport().getDetails());
            tv_flu_brief.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getFlu().getBrief());
            tv_flu_detail.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getFlu().getDetails());
            tv_travel_brief.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getTravel().getBrief());
            tv_travel_detail.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getTravel().getDetails());
            tv_washingcar_brief.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getCar_washing().getBrief());
            tv_washingcar_detail.setText(basicBean.getWeather().get(0).getToday().getSuggestion().getCar_washing().getDetails());
        }
    }

    public interface FragmentListener {
        void onSave(weatherInfoBean weatherInfoBean,int pos);
    }
}
