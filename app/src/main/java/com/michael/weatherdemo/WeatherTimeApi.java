package com.michael.weatherdemo;

import com.michael.weatherdemo.Beans.WeatherTimeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Michael on 2017/4/5.
 */

public interface WeatherTimeApi {

    @GET(".")
    Call<WeatherTimeBean> weatherTimeGetCall(@Query("city") String city);
}
