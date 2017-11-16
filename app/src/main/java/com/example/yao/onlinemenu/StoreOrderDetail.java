package com.example.yao.onlinemenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StoreOrderDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("訂單詳細");
        setContentView(R.layout.activity_store_order_detail);

        Bundle bd_receive = getIntent().getExtras();

        //接收intent資料
        String id_show = bd_receive.getString("orderID");
        String time_show = bd_receive.getString("orderTime");
        String customer_show = bd_receive.getString("orderCustomerName");
        String info_show = bd_receive.getString("orderInfo");
        String total_show = bd_receive.getString("orderTotal");

        TextView tv;

    }
}
