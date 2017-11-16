package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreOrderManagement extends AppCompatActivity {

    private RecyclerView orderlist;
    String[] arrID, arrTime, arrCustomer, arrInfo, arrTotal;
    private ArrayList<HashMap<String, String>> orderData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("訂單管理");
        setContentView(R.layout.activity_store_order_management);

        orderlist = (RecyclerView) findViewById(R.id.order_listView);


        arrID = new String[]{ "0920001", "0920002", "0920003" };
        arrTime = new String[]{ "2017/09/20 11:00", "2017/09/20 11:30", "2017/09/20 12:00" };
        arrCustomer = new String[]{ "yoman", "池欣雅", "林德榮" };
        arrInfo = new String[]{ "脆皮雞腿飯*1", "日式燒肉飯*1", "薄鹽鯖魚飯*2" };
        arrTotal = new String[]{"$85","$75","$75"};

        for (int i = 0; i < arrID.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            //依序將資料存入hm
            hm.put("Id_Show", arrID[i]);
            hm.put("Time_Show", arrTime[i]);
            hm.put("Customer_Show", arrCustomer[i]);
            hm.put( "Info_show", arrInfo[i]);
            hm.put( "Total_show", arrTotal[i]);
            //將hm存入data
            orderData.add(hm);
        }

        MyAdapter myAdapter = new MyAdapter(orderData);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderlist.setLayoutManager(layoutManager);
        orderlist.setAdapter(myAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        //儲存項目資料的List物件
        private ArrayList<HashMap<String, String>> mData;

        public MyAdapter(ArrayList<HashMap<String, String>> data) {
            mData = data;
        }

        //建立與回傳包裝好的項目畫面配置元件
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //建立項目使用的畫面配置元件
            View v = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.order_card, parent, false);
            //建立與回傳包裝好的畫面配置元件
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            protected TextView mID, mTime, mCustomer, mInfo, mTotal;

            public ViewHolder(View v) {
                super(v);
                mID = (TextView) v.findViewById(R.id.orderID);
                mTime = (TextView) v.findViewById(R.id.orderTime);
                mCustomer = (TextView) v.findViewById(R.id.orderCustomer);
                mInfo = (TextView) v.findViewById(R.id.orderInfo);
                mTotal = (TextView) v.findViewById(R.id.orderTotal);
            }
        }

        //設定項目顯示的資料, 註冊項目與元件的點擊事件
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mID.setText(arrID[position]);
            holder.mTime.setText(arrTime[position]);
            holder.mCustomer.setText(arrCustomer[position]);
            holder.mInfo.setText(arrInfo[position]);
            holder.mTotal.setText(arrTotal[position]);

            // *** 不會使用,要去查 ***
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //設定目標為DishDetail
                    Intent intent = new Intent();
                    intent.setClass(holder.itemView.getContext(), StoreOrderDetail.class);

                    //傳送資料到DishDetail
                    Bundle bd_send = new Bundle();
                    bd_send.putString("orderID", arrID[position]);
                    bd_send.putString("orderTime", arrTime[position]);
                    bd_send.putString("orderCustomerName", arrCustomer[position]);
                    bd_send.putString("orderInfo", arrInfo[position]);
                    bd_send.putString("orderTotal", arrTotal[position]);
                    intent.putExtras(bd_send);

                    //啟動inten
                    holder.itemView.getContext().startActivity(intent);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(StoreOrderManagement.this, "Item  is long clicked.", Toast.LENGTH_SHORT).show();
                    return true;
                }
            } );
        }

        //傳回項目資料的數量
        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
