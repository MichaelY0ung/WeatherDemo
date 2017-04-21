package com.michael.weatherdemo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michael on 2017/4/7.
 */

public class WeatherSqliteOpenHelper extends SQLiteOpenHelper{
    public WeatherSqliteOpenHelper(Context context) {
        super(context, "weather.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Weather(_id integer primary key autoincrement,weatherinfo varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
