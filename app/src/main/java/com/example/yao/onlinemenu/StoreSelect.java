package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreSelect extends AppCompatActivity {

    private Button add;
    private RecyclerView storeList;
    String[] arrName,arrStoreID;
    private ArrayList<HashMap<String, String>> storeData = new ArrayList<>();

    private ApiInterface_Store_Info ApiI_store_select;

    private String status = "selectstore";
    private String oID="";  //(PHP還沒改)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_select);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //取得userID
        GlobalVariable user = (GlobalVariable)getApplicationContext();
        oID = user.getOwnerID();

        add = (Button) findViewById(R.id.add_store);
        add.setOnClickListener(new mListener());

        storeList = (RecyclerView) findViewById(R.id.store_listView);

        ApiI_store_select = ApiClient.getClient().create(ApiInterface_Store_Info.class);
        Call<ServerResponse_storeinfo> call_ss = ApiI_store_select.store_select(status,oID);
        call_ss.enqueue(new Callback<ServerResponse_storeinfo>(){
            @Override
            public void onResponse(Call<ServerResponse_storeinfo> callSRs, Response<ServerResponse_storeinfo> resSRs)
            {
                //new的方式好過宣告final變數
                arrName = new String[resSRs.body().getResult().size()];
                arrStoreID = new String[resSRs.body().getResult().size()];

                //取出資料
                for (int i = 0; i < resSRs.body().getResult().size(); i++) {
                    Result_storeinfo Rd = resSRs.body().getResult().get(i);
                    arrName[i] = Rd.getShopName();
                    arrStoreID[i] = Rd.getSINO();
                }

                for (int i = 0; i < arrName.length; i++) {
                    HashMap<String, String> hm_ss = new HashMap<String, String>();
                    //依序將資料存入hm_dish
                    hm_ss.put("Name_Show", arrName[i]);
                    //將hm存入dishData
                    storeData.add(hm_ss);
                }

                MyAdapter myAdapter = new MyAdapter(storeData);
                storeList.setAdapter(myAdapter);

            }
            @Override
            public void onFailure (Call < ServerResponse_storeinfo > callSRs, Throwable t)
            {
                callSRs.cancel();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        storeList.setLayoutManager(layoutManager);
    }

    // 點擊按鈕, 管理店家新增.移除
    private class mListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setClass(StoreSelect.this,StoreAdd.class);
            startActivity(intent);
            StoreSelect.this.finish();
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
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //建立項目使用的畫面配置元件
            View v = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.store_card, parent, false);
            //建立與回傳包裝好的畫面配置元件
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            protected TextView mName;

            public ViewHolder(View v) {
                super(v);
                mName = (TextView) v.findViewById(R.id.storeName);
            }
        }

        //設定項目顯示的資料, 註冊項目與元件的點擊事件
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mName.setText(arrName[position]);
            // *** 不會使用,要去查 ***
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //儲存storeID
                    GlobalVariable user = (GlobalVariable) getApplicationContext();
                    user.setStoreID(arrStoreID[position]);

                    //前往Store
                    Intent intent = new Intent();
                    intent.setClass(StoreSelect.this, StoreTab.class);
                    holder.itemView.getContext().startActivity(intent);
//                    StoreSelect.this.finish();
                }
            } );

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(StoreSelect.this, "Item  is long clicked.", Toast.LENGTH_SHORT).show();
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

    //返回鍵操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
