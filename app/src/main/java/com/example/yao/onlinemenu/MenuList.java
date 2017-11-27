package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuList extends AppCompatActivity
{

    private TextView tv_name;
    private RecyclerView mlist;
    String[] arrName, arrPrice, arrIngredient, arrType, arrStar;
    int[] arrImg;
    private ArrayList<HashMap<String, String>> dishData = new ArrayList<>();

    private ApiInterface_dish ApiI_dish;
    private String menuName,menuNo;
    private String status = "selectdish";
//    private String menuName_receive,menuNo_receive;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrImg = new int[]{ R.mipmap.d001, R.mipmap.d004 };

        tv_name = (TextView) findViewById(R.id.typeName);
        mlist = (RecyclerView) findViewById(R.id.listView);

        // 接收, (之後)並用此資料select資料庫
        Bundle extras = getIntent().getExtras();
        String menuName_receive = extras.getString("Menu_Name");
        String menuNo_receive = extras.getString("Menu_No");

        menuName=menuName_receive;
        menuNo = menuNo_receive;

        tv_name.setText(menuName);



        ApiI_dish = ApiClient.getClient().create(ApiInterface_dish.class);
        Call<ServerResponse_dish> call_dish = ApiI_dish.selectDish(status,menuNo);
        call_dish.enqueue(new Callback<ServerResponse_dish>()
        {
            @Override
            public void onResponse(Call<ServerResponse_dish> callSRd, Response<ServerResponse_dish> resSRd)
            {
                arrName = new String[resSRd.body().getResult().size()];
                arrPrice = new String[resSRd.body().getResult().size()];
                arrIngredient = new String[resSRd.body().getResult().size()];
                arrType = new String[resSRd.body().getResult().size()];
                arrStar = new String[resSRd.body().getResult().size()];

                //取出資料
                for (int i = 0; i < resSRd.body().getResult().size(); i++) {
                    Result_dish Rd = resSRd.body().getResult().get(i);
                    arrName[i] = Rd.getDName();
                    arrPrice[i] = Rd.getDPrice();
                    arrIngredient[i] = Rd.getDIngredient();
                    arrType[i] = Rd.getDType();
                    arrStar[i] = Rd.getDStar();
                }

                for (int i = 0; i < arrName.length; i++) {
                    HashMap<String, String> hm_dish = new HashMap<String, String>();
                    //依序將資料存入hm_dish
                    hm_dish.put("Name_Show", arrName[i]);
                    hm_dish.put("Price_Show", arrPrice[i]);
                    hm_dish.put( "Img_show", String.valueOf(arrImg[0]) );
                    //將hm存入dishData
                    dishData.add(hm_dish);
                }

                MyAdapter myAdapter = new MyAdapter(dishData);
                mlist.setAdapter(myAdapter);

            }
            @Override
            public void onFailure (Call <ServerResponse_dish> call_dish, Throwable t)
            {
                call_dish.cancel();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mlist.setLayoutManager(layoutManager);

        FloatingActionButton daBtn = (FloatingActionButton) findViewById(R.id.fab_add_menu_2);
        daBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vvv) {
                Intent intent = new Intent();
                intent.setClass(MenuList.this , DishAdd.class);

                Bundle to_DishAdd = new Bundle();
                to_DishAdd.putString("Menu_Name_da",menuName.toString());
                to_DishAdd.putString("Menu_No_da",menuNo);
                intent.putExtras(to_DishAdd);

                startActivity(intent);
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
    {


        //儲存項目資料的List物件
        private ArrayList<HashMap<String, String>> mData;

        public MyAdapter(ArrayList<HashMap<String, String>> data) {
            mData = data;
        }

        //建立與回傳包裝好的項目畫面配置元件
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            //建立項目使用的畫面配置元件
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, parent, false);
            //建立與回傳包裝好的畫面配置元件
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            protected TextView mName,mPrice;
            protected ImageView mImg;

            public ViewHolder(View v)
            {
                super(v);
                mName = (TextView) v.findViewById(R.id.foodName);
                mPrice = (TextView) v.findViewById(R.id.foodPrice);
                mImg = (ImageView) v.findViewById(R.id.foodImg);
            }
        }

        //設定項目顯示的資料, 註冊項目與元件的點擊事件
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mName.setText(arrName[position]);
            holder.mPrice.setText(arrPrice[position]);
            holder.mImg.setImageResource(arrImg[0]);

            // *** 不會使用,要去查 ***
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //設定目標為DishDetail
                    Intent intent = new Intent();
                    intent.setClass(holder.itemView.getContext(), DishDetail.class);

                    //傳送資料到DishDetail
                    Bundle bd_send = new Bundle();
                    bd_send.putString("Name", arrName[position]);
                    bd_send.putString("Price", arrPrice[position]);
                    bd_send.putString("Type",arrType[position]);
                    bd_send.putString("Ingredient", arrIngredient[position]);
                    bd_send.putInt("Img",arrImg[0]);
                    intent.putExtras(bd_send);

//                    int cnt=2;
//                    Bundle bd = new Bundle();
//                    bd.putInt("count", cnt);
//                    intent.putExtras(bd);

                    //啟動inten
                    holder.itemView.getContext().startActivity(intent);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(MenuList.this, "Item  is long clicked.", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
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

                //回到上一個fragment而非class//
                int count = getFragmentManager().getBackStackEntryCount();
                if (count == 0) {super.onBackPressed();}
                else {getFragmentManager().popBackStack();}
                MenuList.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}