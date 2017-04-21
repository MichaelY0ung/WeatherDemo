package com.michael.weatherdemo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.michael.weatherdemo.Beans.WeatherBasicBean;
import com.michael.weatherdemo.Beans.WeatherTimeBean;
import com.michael.weatherdemo.MainActivity;
import com.michael.weatherdemo.R;
import com.michael.weatherdemo.Utils.utils;
import com.michael.weatherdemo.WeatherBasicApi;
import com.michael.weatherdemo.WeatherTimeApi;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michael on 2017/4/20.
 */

public class NotificationService extends Service{
    private boolean isRun;// 线程是否继续的标志
    private Handler handler1; // 显示当前时间线程消息处理器。
    private Handler handler2;// 推送通知栏消息的线程消息处理器。
    private int notificationCounter = 0;// 一个用于计算通知多少的计数器。
    private NotificationManager notificationManager;
    private Notification notification;
    private String townId;
    private WeatherBasicBean basicBean;
    private WeatherTimeBean timeBean;
    private Handler handler3;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        townId = intent.getStringExtra("townId");
        isRun = true;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder1 = new NotificationCompat.Builder(getApplicationContext());
        final NotificationCompat.Builder builder2 = new NotificationCompat.Builder(getApplicationContext());
        Retrofit retrofitBasic = new Retrofit.Builder()
                .baseUrl("http://tj.nineton.cn/Heart/index/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Retrofit retrofitTime = new Retrofit.Builder()
                .baseUrl("http://tj.nineton.cn/Heart/index/future24h/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final WeatherBasicApi weatherBasicApicApi = retrofitBasic.create(WeatherBasicApi.class);
        final WeatherTimeApi weatherTimeApi = retrofitTime.create(WeatherTimeApi.class);
        handler1 = new Handler(new Handler.Callback() {// 这样写，就不弹出什么泄漏的警告了
            @Override
            public boolean handleMessage(Message msg) {
                // 安卓显示当前时间的方法
                if (utils.isNetWorkAvalible(getApplicationContext())) {
                    Call<WeatherBasicBean> callBasic = weatherBasicApicApi.weatherBasicGetCall(townId);
                    callBasic.enqueue(new Callback<WeatherBasicBean>() {
                        @Override
                        public void onResponse(Call<WeatherBasicBean> call, Response<WeatherBasicBean> response) {
                            basicBean = response.body();
                            //text.setText(basicBean.getWeather().get(0).getLast_update());
                            builder1.setContentText(basicBean.getWeather().get(0).getNow().getTemperature()+"℃");
                            builder1.setContentTitle(basicBean.getWeather().get(0).getCity_name()+"|"+basicBean.getWeather().get(0).getNow().getText());
                            builder1.setWhen(System.currentTimeMillis());
                            builder1.setSmallIcon(R.mipmap.ic_launcher_round);
                            builder1.setAutoCancel(false);
                            builder1.setOngoing(true);
                            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                            builder1.setContentIntent(pendingIntent);
                            notificationManager.notify(0, builder1.build());
                        }
                        @Override
                        public void onFailure(Call<WeatherBasicBean> call, Throwable t) {
                            Log.d("111111", t.getMessage()+"error"+townId);
                        }
                    });
                }
                return false;
            }
        });
//                notification.icon = R.mipmap.ic_launcher_round;// 设置通知图标为app的图标
//                notification.flags = Notification.FLAG_AUTO_CANCEL;// 点击通知打开引用程序之后通知自动消失
//                notification.tickerText = "显示通知";// 在用户没有拉开标题栏之前，在标题栏中显示的文字
//                notification.when = System.currentTimeMillis();// 设置发送时间
//                notification.defaults = Notification.DEFAULT_ALL;// 设置使用默认声音、震动、闪光灯
        // 以下三行：在安卓设备任意环境中中，如果点击信息则打开MainActivity
        new Thread(new Runnable() {
            @Override
            public void run() {//每15分钟更新数据
                while (isRun) {
                    Message msg = new Message(); // 在安卓中，不要在线程中直接现实方法，这样app容易崩溃，有什么要搞，扔到消息处理器中实现。
                    handler1.sendMessage(msg);
                    try {
                        Thread.sleep(1000*60*15);// Java中线程的休眠，必须在try-catch结构中，每2s秒运行一次的意思
                        //15分钟更新一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!isRun) {
                        break;
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            // 在Runnable中，如果要让线程自己一直跑下去，必须自己定义while结构
            // 如果这个run()方法读完了，则整个线程自然死亡
            public void run() {
                // 定义一个线程中止标志
                while (isRun) {
                    try {
                        Thread.sleep(1000*60*15);// Java中线程的休眠，必须在try-catch结构中
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!isRun) {
                        break;
                    }
                    Message msg = new Message();
                    handler2.sendMessage(msg);
                }
            }
        }).start();// 默认线程不启动，必须自己start()
        // 计数器+1
        handler2 = new Handler(new Handler.Callback(){

            @Override
            public boolean handleMessage(Message msg) {
                Notification.Builder builder = new Notification.Builder(getApplicationContext());
//                notification.icon = R.mipmap.ic_launcher_round;// 设置通知图标为app的图标
//                notification.flags = Notification.FLAG_AUTO_CANCEL;// 点击通知打开引用程序之后通知自动消失
//                notification.tickerText = "显示通知";// 在用户没有拉开标题栏之前，在标题栏中显示的文字
//                notification.when = System.currentTimeMillis();// 设置发送时间
//                notification.defaults = Notification.DEFAULT_ALL;// 设置使用默认声音、震动、闪光灯
                // 以下三行：在安卓设备任意环境中中，如果点击信息则打开MainActivity
//                builder.setContentInfo("晴");
//                builder.setContentText("12/23");
//                builder.setContentTitle("北京");
//                builder.setSmallIcon(R.mipmap.ic_launcher_round);
//                builder.setTicker("新消息");
//                builder.setAutoCancel(true);
//                builder.setWhen(System.currentTimeMillis());
//                builder.setOngoing(true);
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                builder.setContentIntent(pendingIntent);
//                Notification notification = builder.build();
//                notificationManager.notify(notificationCounter, notification);
                if (utils.isNetWorkAvalible(getApplicationContext())) {
                    Call<WeatherTimeBean> call = weatherTimeApi.weatherTimeGetCall(townId);
                    call.enqueue(new Callback<WeatherTimeBean>() {
                        @Override
                        public void onResponse(Call<WeatherTimeBean> call, Response<WeatherTimeBean> response) {
                            timeBean = response.body();
                            SimpleDateFormat sDateFormat=new SimpleDateFormat("HH");
                            String hour =   sDateFormat.format(new Date());
                            int count = 0;
                            while(true) {
                                if (hour.equals(timeBean.getHourly().get(count).getTime().substring(11, 13))) {
                                    count++;
                                    break;
                                }
                                count++;
                            }
                            if( timeBean.getHourly().get(count).getText().indexOf("雨")!=-1){
                                builder2.setContentTitle("注意");
                                builder2.setContentText("一小时后可能会下雨，外出请准备好雨具");
                                builder2.setWhen(System.currentTimeMillis());
                                builder2.setSmallIcon(R.mipmap.ic_launcher_round);
                                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
                                builder1.setContentIntent(pendingIntent);
                                notificationManager.notify(1, builder2.build());
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherTimeBean> call, Throwable t) {
                            Log.d("111111", t.getMessage() + "errortime");
                        }
                    });
                }
                return false;
            }
        });
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isRun = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
