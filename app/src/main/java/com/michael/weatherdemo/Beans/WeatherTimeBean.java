package com.michael.weatherdemo.Beans;

import java.util.ArrayList;

/**
 * Created by Michael on 2017/4/5.
 */

public class WeatherTimeBean {
    /**
     * status : OK
     * hourly : [{"text":"阴","code":"9","temperature":"16","time":"2017-04-05T05:00:00+08:00"},{"text":"阴","code":"9","temperature":"15","time":"2017-04-05T06:00:00+08:00"},{"text":"阴","code":"9","temperature":"15","time":"2017-04-05T07:00:00+08:00"},{"text":"小雨","code":"13","temperature":"16","time":"2017-04-05T08:00:00+08:00"},{"text":"小雨","code":"13","temperature":"17","time":"2017-04-05T09:00:00+08:00"},{"text":"小雨","code":"13","temperature":"18","time":"2017-04-05T10:00:00+08:00"},{"text":"小雨","code":"13","temperature":"19","time":"2017-04-05T11:00:00+08:00"},{"text":"小雨","code":"13","temperature":"20","time":"2017-04-05T12:00:00+08:00"},{"text":"小雨","code":"13","temperature":"20","time":"2017-04-05T13:00:00+08:00"},{"text":"小雨","code":"13","temperature":"21","time":"2017-04-05T14:00:00+08:00"},{"text":"小雨","code":"13","temperature":"21","time":"2017-04-05T15:00:00+08:00"},{"text":"小雨","code":"13","temperature":"21","time":"2017-04-05T16:00:00+08:00"},{"text":"小雨","code":"13","temperature":"21","time":"2017-04-05T17:00:00+08:00"},{"text":"小雨","code":"13","temperature":"20","time":"2017-04-05T18:00:00+08:00"},{"text":"小雨","code":"13","temperature":"19","time":"2017-04-05T19:00:00+08:00"},{"text":"小雨","code":"13","temperature":"18","time":"2017-04-05T20:00:00+08:00"},{"text":"小雨","code":"13","temperature":"17","time":"2017-04-05T21:00:00+08:00"},{"text":"小雨","code":"13","temperature":"17","time":"2017-04-05T22:00:00+08:00"},{"text":"阴","code":"9","temperature":"17","time":"2017-04-05T23:00:00+08:00"},{"text":"阴","code":"9","temperature":"17","time":"2017-04-06T00:00:00+08:00"},{"text":"阴","code":"9","temperature":"17","time":"2017-04-06T01:00:00+08:00"},{"text":"阴","code":"9","temperature":"16","time":"2017-04-06T02:00:00+08:00"},{"text":"阴","code":"9","temperature":"16","time":"2017-04-06T03:00:00+08:00"},{"text":"阴","code":"9","temperature":"16","time":"2017-04-06T04:00:00+08:00"}]
     */

    private String status;
    private ArrayList<HourlyBean> hourly;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<HourlyBean> getHourly() {
        return hourly;
    }

    public void setHourly(ArrayList<HourlyBean> hourly) {
        this.hourly = hourly;
    }

    public class HourlyBean {
        /**
         * text : 阴
         * code : 9
         * temperature : 16
         * time : 2017-04-05T05:00:00+08:00
         */

        private String text;
        private String code;
        private String temperature;
        private String time;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
