package com.michael.weatherdemo.Beans;

/**
 * Created by Michael on 2017/4/17.
 */

public class weatherInfoBean{
    private WeatherBasicBean basicBean;
    private WeatherTimeBean timeBean;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WeatherBasicBean getBasicBean() {
        return basicBean;
    }

    public void setBasicBean(WeatherBasicBean basicBean) {
        this.basicBean = basicBean;
    }

    public WeatherTimeBean getTimeBean() {
        return timeBean;
    }

    public void setTimeBean(WeatherTimeBean timeBean) {
        this.timeBean = timeBean;
    }
}
