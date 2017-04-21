package com.michael.weatherdemo.Beans;

import java.io.Serializable;

/**
 * Created by Michael on 2017/4/17.
 */

public class townInfo implements Serializable {
    private String townId;
    private String townName;
    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

}
