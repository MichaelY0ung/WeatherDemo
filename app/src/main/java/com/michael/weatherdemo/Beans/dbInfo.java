package com.michael.weatherdemo.Beans;

import java.util.ArrayList;

/**
 * Created by Michael on 2017/4/17.
 */

public class dbInfo {
    public ArrayList<weatherInfoBean> getInfoBeanList() {
        return InfoBeanList;
    }

    public void setInfoBeanList(ArrayList<weatherInfoBean> infoBeanList) {
        InfoBeanList = infoBeanList;
    }

    private ArrayList<weatherInfoBean> InfoBeanList;
}
