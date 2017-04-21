package com.michael.weatherdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.michael.weatherdemo.DB.DBManager;
import com.michael.weatherdemo.Utils.CityIdDao;

import java.util.ArrayList;

/**
 * Created by Michael on 2017/4/17.
 */

public class WelcomeActivity extends AppCompatActivity{
    private ProgressDialog progressDialog;
    private Context mContext;
    private String townId;
    private ArrayList<String> cityNames;
    private ArrayList<String> townNames;
    private String townName;
    private Handler mHandler = new Handler(){
        public AlertDialog dialog;
        @Override
        public void handleMessage(Message msg) {
            progressDialog.dismiss();
            switch (msg.what){
                case 0:
                    dialog = new AlertDialog.Builder(mContext)
                            .setItems(areaNames.toArray(new String[areaNames.size()]), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, final int which) {
                                    progressShow();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            cityNames=cityDao.getCitys(areaNames.get(which));
                                            Message msg = Message.obtain();
                                            msg.what=1;
                                            mHandler.sendMessage(msg);
                                        }
                                    }).start();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setTitle("请选择省、直辖市、地区")
                            .show();
                    break;
                case 1:
                    dialog = new AlertDialog.Builder(mContext)
                            .setItems(cityNames.toArray(new String[cityNames.size()]), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, final int which) {
                                    progressShow();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            townNames = cityDao.getTowns(cityNames.get(which));
                                            Message msg = Message.obtain();
                                            msg.what=2;
                                            mHandler.sendMessage(msg);
                                            dialog.dismiss();
                                        }
                                    }).start();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setTitle("请选择市")
                            .show();
                    break;
                case 2:
                    dialog = new AlertDialog.Builder(mContext)
                            .setItems(townNames.toArray(new String[townNames.size()]), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, final int which) {
                                    progressShow();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            townId = cityDao.getTownId(townNames.get(which));
                                            Log.d("11111",townId);
                                            townName = townNames.get(which);
                                            Message msg = Message.obtain();
                                            msg.what=3;
                                            mHandler.sendMessage(msg);
                                            dialog.dismiss();
                                        }
                                    }).start();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setTitle("请选择县")
                            .show();
                    break;
                case 3:
                    Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                    intent.putExtra("townId",townId);
                    Log.d("11111",townId);
                    intent.putExtra("townName",townName);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };
    private String cityCode;
    private DBManager dbManager;
    private CityIdDao cityDao;
    private ArrayList<String> areaNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        mContext = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        dbManager = new DBManager(mContext);
        dbManager.openDatabase();
        Log.d("11111",dbManager.getDatabase()!=null?"ok":"no");
        cityDao = new CityIdDao(dbManager.getDatabase());
        SharedPreferences setting = getSharedPreferences("status", 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        Log.d("111111",user_first+"");
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        if(user_first){//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            AlertDialog dialog = new AlertDialog.Builder(mContext)
                    .setItems(new String[]{"中国", "海外"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            progressShow();
                            cityCode = new String();
                            switch (which){
                                case 0:
                                    cityCode = "中国";
                                    break;
                                case 1:
                                    cityCode = "全球";
                                    break;
                            }
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    areaNames = cityDao.getAreaName(cityCode);
                                    Message msg = Message.obtain();
                                    msg.what = 0;
                                    mHandler.sendMessage(msg);
                                    dialog.dismiss();
                                }
                            }).start();
                        }
                    })
                    .setCancelable(false)
                    .setTitle("请选择地区")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

        }else{
            startActivity(intent);
            finish();
        }
    }
    private void progressShow() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("正在查找数据...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDatabase();
    }

}
