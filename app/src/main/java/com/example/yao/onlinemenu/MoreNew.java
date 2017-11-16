package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoreNew extends AppCompatActivity {

    private RecyclerView searchlist;
    String[] arrName, arrPrice, arrRating;
    private ArrayList<HashMap<String, String>> SearchData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_new);
        searchlist = (RecyclerView) findViewById(R.id.MoreNew_listView);

        arrName = new String[]{ "1牛肉飯", "2羊肉飯", "3鴨肉飯" };
        arrPrice = new String[]{ "80", "75", "70" };
        arrRating = new String[]{ "2", "4", "5" };

        for (int i = 0; i < arrName.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            //依序將資料存入hm
            hm.put("Name_Show", arrName[i]);
            hm.put("Price_Show", arrPrice[i]);
            hm.put("Rating_Show", arrRating[i]);
            //將hm存入data
            SearchData.add(hm);
        }

        MyAdapter myAdapter = new MyAdapter(SearchData);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchlist.setLayoutManager(layoutManager);
        searchlist.setAdapter(myAdapter);
//        mlist.setOnItemClickListener(new mClick());
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
                    (R.layout.show_menu, parent, false);
            //建立與回傳包裝好的畫面配置元件
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            protected TextView mName,mPrice;
            private RatingBar mRating;

            public ViewHolder(View v) {
                super(v);
                mName = (TextView) v.findViewById(R.id.searchName);
                mPrice = (TextView) v.findViewById(R.id.searchPrice);
                mRating = (RatingBar) v.findViewById(R.id.searchRating);
            }
        }

        //設定項目顯示的資料, 註冊項目與元件的點擊事件
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mName.setText(arrName[position]);
            holder.mPrice.setText(arrPrice[position]);
            holder.mRating.setIsIndicator(false);
            holder.mRating.setNumStars(5);
            holder.mRating.setRating(Float.parseFloat(arrRating[position]));
            // *** 不會使用,要去查 ***
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //前往Store
                    Intent intent = new Intent();
                    intent.setClass(MoreNew.this, DishDetail.class);
                    startActivity(intent);
                    //下面傳送要連線DB的資料
                }
            } );

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    Toast.makeText(SearchResult.this, "Item  is long clicked.", Toast.LENGTH_SHORT).show();
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
