package com.example.yao.onlinemenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerCart extends AppCompatActivity {

    private RecyclerView cartlist;
    String[] arrName, arrPrice;
    int[] arrImg;
    private ArrayList<HashMap<String, String>> CartData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        cartlist = (RecyclerView) findViewById(R.id.cart_listView);

        arrName = new String[]{ "脆皮雞腿飯"};
        arrPrice = new String[]{ "85" };
        arrImg = new int[]{ R.mipmap.d001 };

        for (int i = 0; i < arrName.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            //依序將資料存入hm
            hm.put("Name_Show", arrName[i]);
            hm.put("Price_Show", arrPrice[i]);
            //將hm存入data
            CartData.add(hm);

            MyAdapter myAdapter = new MyAdapter(CartData);

            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            cartlist.setLayoutManager(layoutManager);
            cartlist.setAdapter(myAdapter);

            Button bt;
            bt = (Button)findViewById(R.id.bt_continue_buy);
            bt.setOnClickListener(new mListener());
            bt = (Button)findViewById(R.id.bt_account_order);
            bt.setOnClickListener(new mListener());
        }
    }

    private class mListener implements View.OnClickListener{
        @Override
        public void onClick(View v){

            switch (v.getId()) {
                case R.id.bt_continue_buy:
                    Toast.makeText(CustomerCart.this, "選購", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.bt_account_order:
                    Toast.makeText(CustomerCart.this, "結帳", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
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
                    (R.layout.cart_food_card, parent, false);
            //建立與回傳包裝好的畫面配置元件
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            protected TextView mName,mPrice;
            protected ImageView mImg;

            public ViewHolder(View v) {
                super(v);
                mName = (TextView) v.findViewById(R.id.cart_foodName);
                mPrice = (TextView) v.findViewById(R.id.cart_foodPrice);
                mImg = (ImageView) v.findViewById(R.id.cart_foodImg);
            }
        }

        //設定項目顯示的資料, 註冊項目與元件的點擊事件
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mName.setText(arrName[position]);
            holder.mPrice.setText(arrPrice[position]);
            holder.mImg.setImageResource(arrImg[position]);
        }

        //傳回項目資料的數量
        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
