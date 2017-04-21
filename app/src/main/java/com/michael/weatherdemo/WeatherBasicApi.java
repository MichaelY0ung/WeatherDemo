package com.michael.weatherdemo;

import com.michael.weatherdemo.Beans.WeatherBasicBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Michael on 2017/4/5.
 */

public interface WeatherBasicApi {
    @GET("all")
    Call<WeatherBasicBean> weatherBasicGetCall(@Query("city") String city);

}
