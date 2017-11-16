package com.example.yao.onlinemenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class Store_Home extends AppCompatActivity{

    private TextView KeeperM, StoreM, OrderM, Notice, Statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store__home);

        processViews();
        processControllers();

//        Bundle un_receive = getIntent().getExtras();
//        String un_show = un_receive.getString("un_login2storehome");
//
//        Toast.makeText(getApplicationContext(), un_show, Toast.LENGTH_LONG).show();

    }
    private void processViews()
    {
        KeeperM = (TextView) findViewById(R.id.KeeperM);
        StoreM = (TextView) findViewById(R.id.StoreM);
        OrderM = (TextView) findViewById(R.id.OrderM);
        Notice = (TextView) findViewById(R.id.Notice);
        Statistics = (TextView) findViewById(R.id.Statistics);
    }
    private void processControllers() {
        View.OnClickListener HomeListemer =
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();

                        if (id == R.id.KeeperM) {
                            Intent intent = new Intent();
                            intent.setClass(Store_Home.this, KeeperInfo.class);
                            startActivity(intent);
//                            Store_Home.this.finish();
                        }
                        if (id == R.id.StoreM) {
                            Intent intent = new Intent();
                            intent.setClass(Store_Home.this, StoreSelect.class);
                            startActivity(intent);
//                            Store_Home.this.finish();
                        }
                        if (id == R.id.OrderM) {
                            Intent intent = new Intent();
                            intent.setClass(Store_Home.this, StoreOrderManagement.class);
                            startActivity(intent);
//                            HomePageStore.this.finish();
                        }
                        if (id == R.id.Notice) {
//                            Intent intent = new Intent();
//                            intent.setClass(HomePageStore.this, StoreKeeperInfo.class);
//                            startActivity(intent);
//                            HomePageStore.this.finish();
                        }
                        if (id == R.id.Statistics) {
//                            Intent intent = new Intent();
//                            intent.setClass(HomePageStore.this, StoreKeeperInfo.class);
//                            startActivity(intent);
//                            HomePageStore.this.finish();
                        }
                    }
                };
        KeeperM.setOnClickListener(HomeListemer);
        StoreM.setOnClickListener(HomeListemer);
        OrderM.setOnClickListener(HomeListemer);
        Notice.setOnClickListener(HomeListemer);
        Statistics.setOnClickListener(HomeListemer);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(Store_Home.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束應用程式嗎?")
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    finish();
                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
        }
        return true;
    }
}