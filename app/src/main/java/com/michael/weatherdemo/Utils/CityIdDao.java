package com.michael.weatherdemo.Utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Michael on 2017/4/7.
 */

public class CityIdDao {
    private final SQLiteDatabase db;
    private String townId;

    public CityIdDao(SQLiteDatabase db){
        this.db = db;
    }
//    public ArrayList<String> getChineseCityInfo(){
//        ArrayList<String> list = new ArrayList<String>();
//        Cursor cursor = db.query("city_code",new String[]{"areaName"},"countryName=?",new String[]{"中国"},"areaName",null,null);
//        Cursor cursor1 = db.query("city_code",new String[]{"cityName"},"areaName=?",new String[]{"直辖市"},"cityName",null,null);
//        if(cursor1 != null && cursor1.getCount() >0){//判断cursor中是否存在数据
//            //循环遍历结果集，获取每一行的内容
//            while(cursor1.moveToNext()){//条件，游标能否定位到下一行
//                //获取数据
//                String cityname = cursor1.getString(0);
//                list.add(cityname);
//            }
//            cursor1.close();//关闭结果集
//        }
//        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据
//            //循环遍历结果集，获取每一行的内容
//            while(cursor.moveToNext()){//条件，游标能否定位到下一行
//                //获取数据
//                String cityname = cursor.getString(0);
//                if(cityname.equals("直辖市")){
//                    continue;
//                }
//                if(cityname.equals("特别行政区")){
//                    continue;
//                }
//                list.add(cityname);
//            }
//            cursor.close();//关闭结果集
//        }
//        Cursor cursor2 = db.query("city_code",new String[]{"cityName"},"areaName=?",new String[]{"特别行政区"},"cityName",null,null);
//        if(cursor2 != null && cursor2.getCount() >0){//判断cursor中是否存在数据
//            //循环遍历结果集，获取每一行的内容
//            while(cursor2.moveToNext()){//条件，游标能否定位到下一行
//                //获取数据
//                String cityname = cursor2.getString(0);
//                list.add(cityname);
//            }
//            cursor2.close();//关闭结果集
//        }
//        //关闭数据库对象
//        return list;
//    }
    public ArrayList<String> getTownInfo(String cityId){
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = db.query("city_code",new String[]{"townName",},"cityName = ?",new String[]{cityId},null,null,null);
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                String townName = cursor.getString(0);
                list.add(townName);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        return list;
    }
    public String getTownId(String townName){
        Cursor cursor = db.query("city_code",new String[]{"townId",},"townName = ?",new String[]{townName},null,null,null);
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                townId= cursor.getString(0);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        return townId;
    }
    public ArrayList<String> getAllId(){
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.query("city_code",new String[]{"townId",},null,null,null,null,null);
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                townId= cursor.getString(0);
                list.add(townId);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        return list;
    }
    public String getTownName(String townId){
        String townName = null;
        Cursor cursor = db.query("city_code",new String[]{"townName",},"townId=?",new String[]{townId},null,null,null);
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                 townName = cursor.getString(0);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        return townName;
    }
    public ArrayList<String> getAreaName(String countryName){
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.query("city_code",new String[]{"areaName",},"countryName=?",new String[]{countryName},"areaName",null,"ID");
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                String areaName = cursor.getString(0);
                list.add(areaName);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        return list;
    }
    public ArrayList<String> getTowns(String cityNames){
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.query("city_code",new String[]{"townName"},"cityName=?",new String[]{cityNames},"townName",null,"ID");
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                String cityName = cursor.getString(0);
                list.add(cityName);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        return list;
    }

    public ArrayList<String> getCitys(String areaName) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.query("city_code",new String[]{"cityName"},"areaName=?",new String[]{areaName},"cityName",null,"ID");
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据

            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                String cityName = cursor.getString(0);
                list.add(cityName);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        return list;
    }
}
