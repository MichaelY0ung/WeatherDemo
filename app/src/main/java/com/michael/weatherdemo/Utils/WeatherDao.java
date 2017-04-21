package com.michael.weatherdemo.Utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Michael on 2017/4/7.
 */

public class WeatherDao {
    private SQLiteDatabase db;
    public WeatherDao(SQLiteDatabase db){
        this.db = db;
    }
    public boolean add(String data){
        ContentValues values = new ContentValues();
        values.put("weatherinfo",data);
        long result = db.insert("Weather",null,values);
        return result!= -1 ? true:false;
    }
    public String query(){
        String data = new String();
        Cursor cursor = db.query("Weather",null,null,null,null,null,null);
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                data = cursor.getString(1);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        return data;
    }
    public void cleanTable(){
        db.execSQL("DELETE FROM WEATHER");
    }
}
