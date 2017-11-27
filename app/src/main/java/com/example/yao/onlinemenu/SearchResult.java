package com.example.yao.onlinemenu;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResult extends AppCompatActivity {

    private RecyclerView searchlist;
    private ApiInterface_search apiS;
    private String[] arrName, arrPrice, arrIngredient, arrType, arrRating, arrShopName, arrNO;
    private HashMap<String,Integer> hmapCTR = new HashMap();
    private int[] arrImg;
    private ArrayList<HashMap<String, String>> SearchData = new ArrayList<>();
    private ArrayList<String> arrNo = new ArrayList<>(), arrCtr = new ArrayList<>();
    private SharedPreferences ShaPre;
    //    private Boolean prevTimerNotRunning
    GlobalVariable gv ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiS = ApiClient.getClient().create(ApiInterface_search.class);
        ShaPre = getSharedPreferences("CTR", MODE_PRIVATE);
        gv = (GlobalVariable)getApplicationContext();
//      點擊計算之2分鐘倒數計時器
        countdownTimer();



        //接收資料
        Bundle bundle = getIntent().getExtras();
        String tag = bundle.getString("tag");
        String keyword = bundle.getString("keyword");
        String dollar1 = bundle.getString("dollar1");
        String dollar2 = bundle.getString("dollar2");

        Call<ServerResponse_search> call = apiS.search(tag,keyword,dollar1,dollar2);
        call.enqueue(new Callback<ServerResponse_search>(){
            @Override
            public void onResponse(Call<ServerResponse_search> call, Response<ServerResponse_search> response) {

                if(response.body().getSuccess().equals("1"))
                {
                    arrNO = new String[response.body().getResult().size()];
                    arrName = new String[response.body().getResult().size()];
                    arrPrice = new String[response.body().getResult().size()];
                    arrRating = new String[response.body().getResult().size()];
                    arrShopName = new String[response.body().getResult().size()];
                    arrType = new String[response.body().getResult().size()];
                    arrIngredient = new String[response.body().getResult().size()];


                    //取出資料
                    for (int i = 0; i < response.body().getResult().size(); i++) {
                        Result_search SingleRecord = response.body().getResult().get(i);
                        arrNO[i] = SingleRecord.getDNO();
                        arrName[i] = SingleRecord.getDish();
                        arrPrice[i] = SingleRecord.getPrice();
                        arrRating[i] = SingleRecord.getDStar();
                        arrShopName[i] = SingleRecord.getShopName();
                        arrType[i] = SingleRecord.getDType();
                        arrIngredient[i] = SingleRecord.getDIngredient();
                        hmapCTR.put(SingleRecord.getDNO(),SingleRecord.getCTR());
//                        ShaPre.edit().putInt(SingleRecord.getDNO(),SingleRecord.getCTR()).apply();
                    }



                    arrImg = new int[]{ R.mipmap.d001, R.mipmap.d002, R.mipmap.d003, R.mipmap.d003, R.mipmap.d003, R.mipmap.d003, R.mipmap.d003, R.mipmap.d003, R.mipmap.d003, R.mipmap.d003};
//                    arrType = new String[]{"飯食","飯食","飯食","飯食","飯食","飯食","飯食","飯食","飯食","飯食"};
//                    arrIngredient = new String[]{"雞肉 飯","雞肉 飯","雞肉 飯","雞肉 飯","雞肉 飯","雞肉 飯","雞肉 飯","雞肉 飯","雞肉 飯","雞肉 飯"};

                    for (int i = 0; i < arrName.length; i++) {
                        HashMap<String, String> hm = new HashMap<String, String>();
                        //依序將資料存入hm
//                        hm.put("Dish_NO", arrNO[i]);
                        hm.put("Name_Show", arrName[i]);
                        hm.put("Price_Show", arrPrice[i]);
                        hm.put("Rating_Show", arrRating[i]);
                        hm.put( "Img_show", String.valueOf(arrImg[i]) );
                        hm.put("Shopname_Show", arrShopName[i]);
                        //將hm存入data
                        SearchData.add(hm);
                    }

                    MyAdapter myAdapter = new MyAdapter(SearchData);
                    searchlist.setAdapter(myAdapter);
                }
                else if(response.body().getSuccess().equals("0"))
                {
                    Toast.makeText(SearchResult.this, "沒有取得資料", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ServerResponse_search> call, Throwable t) {

            }
        });


        searchlist = (RecyclerView) findViewById(R.id.search_listView);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchlist.setLayoutManager(layoutManager);

//        mlist.setOnItemClickListener(new mClick());
    }


    public void countdownTimer() {
        gv.setprevTimerNotRunning(false);

        CountDownTimer timer = new CountDownTimer(180000, 1000) {
            //每跳1000ms=1s↑進入一次onTick
            public void onTick(long millisUntilFinished) {
//                DoNothing
            }

            public void onFinish() {

//                    SharedPreferences ShaPre = getSharedPreferences("CTR", MODE_PRIVATE);
                Map<String,?> ctrdata = ShaPre.getAll();

                //MapToStringArray
//                String NOKey = "";
//                String CtrValue = "";
                for(Map.Entry entry:ctrdata.entrySet())
                {
//                  字串方式
//                  NOKey = NOKey +entry.getKey().toString() + ".";
//                  Toast.makeText(SearchResult.this, NOKey, Toast.LENGTH_SHORT).show();
//                  CtrValue = CtrValue + entry.getValue().toString() + "." ;
//                  array方式
                    arrNo.add(entry.getKey().toString());
                    arrCtr.add(entry.getValue().toString());
                }


                Call<ServerResponse> call = apiS.CTR(arrNo,arrCtr);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if(response.body().getSuccess() == 1)
                        {
//                                Toast.makeText(SearchResult.this, "!success", Toast.LENGTH_SHORT).show();
                            gv.setprevTimerNotRunning(true);
                            ShaPre.edit().clear().apply();
                            arrNo.clear();
                            arrCtr.clear();
                            Log.i("ctr","CTR update success");
                            if(gv.getprevTimerNotRunning()) {
                                countdownTimer();
                            }

                        }
                        else
                        {
//                                Toast.makeText(SearchResult.this, "Not 1", Toast.LENGTH_SHORT).show();
                            countdownTimer();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        countdownTimer();
                        call.cancel();
//                        Toast.makeText(SearchResult.this, "fail", Toast.LENGTH_SHORT).show();
                        Log.e("ctr","CTR update error");


                    }

                });

            }

        }.start();

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
            protected TextView mName,mPrice,mShopname;
            protected RatingBar mRating;
            protected ImageView mImg;

            public ViewHolder(View v) {
                super(v);
                mName = (TextView) v.findViewById(R.id.searchName);
                mPrice = (TextView) v.findViewById(R.id.searchPrice);
                mShopname = (TextView) v.findViewById(R.id.searchStoreName);
                mRating = (RatingBar) v.findViewById(R.id.searchRating);
                mImg = (ImageView) v.findViewById(R.id.searchImg);
            }
        }

        //設定項目顯示的資料, 註冊項目與元件的點擊事件
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mName.setText(arrName[position]);
            holder.mPrice.setText(arrPrice[position]);
            holder.mShopname.setText(arrShopName[position]);
            holder.mRating.setRating(Float.parseFloat(arrRating[position]));
            holder.mRating.setIsIndicator(false);
            holder.mImg.setImageResource(arrImg[position]);

            // *** 不會使用,要去查 ***
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //紀錄點擊

                    //設定目標為DishDetail
                    Intent intent = new Intent();
                    intent.setClass(holder.itemView.getContext(), SearchResult_Detail.class);

                    //傳送資料到DishDetail
                    Bundle bd_send = new Bundle();
                    bd_send.putString("dishNO", arrNO[position]);
                    bd_send.putString("searchName", arrName[position]);
                    bd_send.putString("searchShopame", arrShopName[position]);
                    bd_send.putString("searchPrice", arrPrice[position]);
                    bd_send.putString("searchType",arrType[position]);
                    bd_send.putString("searchIngredient", arrIngredient[position]);
                    bd_send.putInt("searchImg",arrImg[position]);
                    intent.putExtras(bd_send);

                    //SharedPreferences讀取變數 再寫入

                    int SavedVar = ShaPre.getInt(arrNO[position], -1);
                    if(SavedVar > -1)
                    {
                        ShaPre.edit().putInt(arrNO[position], ++SavedVar).apply();
                    }
                    else
                    {
                        ShaPre.edit().putInt(arrNO[position], 1).apply();
                    }
                    //啟動intent
                    holder.itemView.getContext().startActivity(intent);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(SearchResult.this, "Item  is long clicked.", Toast.LENGTH_SHORT).show();
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
                SearchResult.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}