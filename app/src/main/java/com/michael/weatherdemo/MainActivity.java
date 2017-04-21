package com.michael.weatherdemo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.michael.weatherdemo.Beans.dbInfo;
import com.michael.weatherdemo.Beans.weatherInfoBean;
import com.michael.weatherdemo.DB.DBManager;
import com.michael.weatherdemo.DB.WeatherSqliteOpenHelper;
import com.michael.weatherdemo.Utils.CityIdDao;
import com.michael.weatherdemo.Utils.WeatherDao;
import com.michael.weatherdemo.adapter.mainFragmentPagerAdapter;
import com.michael.weatherdemo.service.NotificationService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private DBManager dbManager;
    private SQLiteDatabase db;
    private CityIdDao cityDao;
    public static String[] chineseCity = {"北京", "上海", "天津", "重庆", "黑龙江", "辽宁", "吉林", "河北", "河南", "湖北", "湖南", "山东", "山西", "陕西", "安徽", "浙江", "江苏", "福建", "广东", "海南", "四川", "云南", "贵州", "青海", "甘肃", "江西", "台湾", "内蒙古", "宁夏", "新疆", "西藏", "广西", "香港", "澳门"};
    private ArrayList<String> l;
    private Toolbar mToolbar;

    private ViewPager mViewPager;
    private ArrayList<String> townIds;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> townName;
    private TextView tv_townName;
    private LinearLayout ll_point_container;
    private View pointView;
    private int previousSelectedPosition;
    private ImageButton menu;
    private mainFragmentPagerAdapter mPagerAdapter;
    private LinearLayout.LayoutParams layoutParams;
    private WeatherDao weatherDao;
    private int count;
    private dbInfo saveDatas;
    private ArrayList<weatherInfoBean> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent serviceIntent = new Intent(MainActivity.this,NotificationService.class);
        mContext = this;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        mViewPager = (ViewPager)findViewById(R.id.main_viewpager);
        tv_townName = (TextView)findViewById(R.id.town_name);
        menu =(ImageButton)findViewById(R.id.main_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext,v);
                popupMenu.getMenuInflater().inflate(R.menu.main,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_edit:
                                Intent intent = new Intent(mContext,EditActivity.class);
                                intent.putStringArrayListExtra("townIds",townIds);
                                intent.putStringArrayListExtra("townName",townName);
                                startActivity(intent);
                                finish();
                                break;
                            case R.id.action_about:
                                AlertDialog dialog = new AlertDialog.Builder(mContext)
                                        .setTitle("关于")
                                        .setMessage("感谢https://github.com/jokermonn的api（充当拿来主义了）\napi:https://github.com/jokermonn/-Api/blob/master/CenterWeather.md")
                                        .show();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        WeatherSqliteOpenHelper weatherSqliteOpenHelper = new WeatherSqliteOpenHelper(mContext);
        db = weatherSqliteOpenHelper.getWritableDatabase();
        infoList = new ArrayList<>();
        weatherDao = new WeatherDao(db);
        dbManager = new DBManager(mContext);
        dbManager.openDatabase();
        cityDao = new CityIdDao(dbManager.getDatabase());
        townIds = new ArrayList<>();
        townName = new ArrayList<>();
        Gson gson = new Gson();
        if(getIntent().getStringExtra("townId")==null) {
            if(getIntent().getStringArrayListExtra("townIds")!=null) {
                for(int i=0;i<getIntent().getStringArrayListExtra("townIds").size();i++){
                    townIds.add(getIntent().getStringArrayListExtra("townIds").get(i));
                    townName.add(getIntent().getStringArrayListExtra("townNames").get(i));
                }
                saveDatas = gson.fromJson(weatherDao.query(), dbInfo.class);
            }
            else{
                Log.d("11111",weatherDao.query());
                saveDatas = gson.fromJson(weatherDao.query(), dbInfo.class);
                Log.d("11111",saveDatas.getInfoBeanList().get(0).getId()+"lll");
                for (int i = 0; i < saveDatas.getInfoBeanList().size(); i++) {
                    townIds.add(new String(saveDatas.getInfoBeanList().get(i).getId()));
                    townName.add(cityDao.getTownName(saveDatas.getInfoBeanList().get(i).getId()) != null ? cityDao.getTownName(saveDatas.getInfoBeanList().get(i).getId()) : "");
                }
                for(int i=0;i<townIds.size();i++){
                    Log.d("11111",townIds.get(i));

                }
            }
        }
        else{
            saveDatas = new dbInfo();
            String id = getIntent().getStringExtra("townId");
            String name = getIntent().getStringExtra("townName");
            Log.d("11111",id);
            townIds.add (id);
            townName.add(name);
        }
        serviceIntent.putExtra("townId",townIds.get(0));
        startService(serviceIntent);
        initViewPager();
    }

    private void initViewPager() {
        fragmentList = new ArrayList<Fragment>();
        for(count=0;count<townIds.size();count++) {
            WeatherFragment fragment1 = WeatherFragment.newInstance(townIds.get(count),count);
            fragment1.setmFragmentListener(new WeatherFragment.FragmentListener() {

                @Override
                public void onSave(weatherInfoBean weatherInfoBean,int pos) {
                    saveDatas.setInfoBeanList(infoList);
                    if(saveDatas.getInfoBeanList()!=null){
                        if(saveDatas.getInfoBeanList().size()<count){
                            saveDatas.getInfoBeanList().add(weatherInfoBean);
                        }
                        else {
                            saveDatas.getInfoBeanList().remove(pos);
                            saveDatas.getInfoBeanList().add(pos,weatherInfoBean);
                        }
                    }
                    weatherDao.cleanTable();
                    Gson gson = new Gson();
                    weatherDao.add(gson.toJson(saveDatas,dbInfo.class));
                    Log.d("111111",gson.toJson(saveDatas,dbInfo.class));
                }
            });
            fragmentList.add(fragment1);
        }
        ll_point_container = (LinearLayout) findViewById(R.id.circle_layout);
        for (int i = 0; i < townIds.size(); i++) {
            // 加小白点, 指示器
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.circle_selector);
            layoutParams = new LinearLayout.LayoutParams(20, 20);
            if(i != 0)
                layoutParams.leftMargin = 10;

            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }

        mViewPager.setAdapter(mPagerAdapter = new mainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList){
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Fragment fragment = (Fragment)super.instantiateItem(container,position);
                getSupportFragmentManager().beginTransaction().show(fragment). commitAllowingStateLoss();
                return fragment;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                Fragment fragment = fragmentList.get(position);
                getSupportFragmentManager().beginTransaction().hide(fragment).commit();
            }
        });
        mViewPager.setCurrentItem(0);//设置当前显示标签页为第一页
        tv_townName.setText(townName.get(0));
        previousSelectedPosition = 0;
        ll_point_container.getChildAt(0).setEnabled(true);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void saveDatas() {

    }

    @Override
    protected void onDestroy() {
        dbManager.closeDatabase();
        db.close();
        super.onDestroy();
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tv_townName.setText(townName.get(position));
            ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
            ll_point_container.getChildAt(position).setEnabled(true);
            previousSelectedPosition  = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
