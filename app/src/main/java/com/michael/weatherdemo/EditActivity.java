package com.michael.weatherdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.michael.weatherdemo.Beans.townInfo;
import com.michael.weatherdemo.DB.DBManager;
import com.michael.weatherdemo.Utils.CityIdDao;
import com.michael.weatherdemo.Utils.CityItemTouchHelperCallback;
import com.michael.weatherdemo.ViewHolder.cityItemViewHolder;
import com.michael.weatherdemo.adapter.cityItemAdapter;

import java.util.ArrayList;

/**
 * Created by Michael on 2017/4/17.
 */

public class EditActivity extends AppCompatActivity{
    private Toolbar mToolbar;
    private RecyclerView rv_cityitem;
    private ArrayList<townInfo> datas;
    private Context mContext;
    private cityItemAdapter mAdapter;
    private CardView bottomLayout;
    private ItemTouchHelper mItemTouchHelper;
    private DBManager dbManager;
    private CityIdDao cityDao;
    private String cityCode;
    private ArrayList<String> areaNames;
    private ArrayList<String> cityNames;
    private ArrayList<String> townNames;
    private String townId;
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
                                public void onClick(DialogInterface dialog, final int which) {
                                    progressShow();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            townNames = cityDao.getTowns(cityNames.get(which));
                                            Message msg = Message.obtain();
                                            msg.what=2;
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
                            .setTitle("请选择市")
                            .show();
                    break;
                case 2:
                    dialog = new AlertDialog.Builder(mContext)
                            .setItems(townNames.toArray(new String[townNames.size()]), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, final int which) {
                                    progressShow();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            townId = cityDao.getTownId(townNames.get(which));
                                            townName = townNames.get(which);
                                            Message msg = Message.obtain();
                                            msg.what=3;
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
                            .setTitle("请选择县")
                            .show();
                    break;
                case 3:
                    townInfo townInfo = new townInfo();
                    townInfo.setTownName(townName);
                    townInfo.setTownId(townId);
                    datas.add(townInfo);
                    mAdapter.notifyItemInserted(datas.size());
                    Log.d("111111",townName+townId);
                    break;
            }
        }
    };
    private ProgressDialog progressDialog;
    private ArrayList<String> dataIds;
    private ArrayList<String> dataNames;
    private ImageButton help;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        mContext = this;
        Intent intent = getIntent();
        datas = new ArrayList<>();
        dataIds = intent.getStringArrayListExtra("townIds");
        dataNames = intent.getStringArrayListExtra("townName");
        for(int i=0;i<dataIds.size();i++){
            townInfo info = new townInfo();
            info.setTownId(dataIds.get(i));
            info.setTownName(dataNames.get(i));
            datas.add(info);
        }
        bottomLayout = (CardView)findViewById(R.id.add_town);
        rv_cityitem = (RecyclerView)findViewById(R.id.rv_cityitem);
        mToolbar = (Toolbar)findViewById(R.id.add_toolbar);
        help = (ImageButton)findViewById(R.id.edit_help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setTitle("帮助")
                        .setMessage("长摁或点击左侧摁钮移动城市清单的次序\n\n滑动删除清单")
                        .show();
            }
        });
        mToolbar.setTitle("");
        dbManager = new DBManager(mContext);
        dbManager.openDatabase();
        cityDao = new CityIdDao(dbManager.getDatabase());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
        rv_cityitem.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mAdapter = new cityItemAdapter(mContext,datas,R.layout.city_rv_item);
        mAdapter.setCityAdapterListener(new cityItemAdapter.CityAdapterListener() {
            @Override
            public void onDel(int pos) {
                Toast.makeText(mContext,"delete:"+pos,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipe(cityItemViewHolder holder) {
                mItemTouchHelper.startDrag(holder);
            }
        });
        rv_cityitem.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        rv_cityitem.setAdapter(mAdapter);
        ItemTouchHelper.Callback callback = new CityItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rv_cityitem);
        bottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setItems(new String[]{"中国", "海外"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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

            }
        });
    }

    @Override
    public void onBackPressed() {
        backActivity();
    }

    private void backActivity() {
        dataIds.clear();
        dataNames.clear();
        for(int i=0;i<datas.size();i++){
            dataIds.add(datas.get(i).getTownId());
            dataNames.add(datas.get(i).getTownName());
        }
        Intent intent1 = new Intent(EditActivity.this,MainActivity.class);
        intent1.putStringArrayListExtra("townIds",dataIds);
        intent1.putStringArrayListExtra("townNames",dataNames);
        startActivity(intent1);
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
