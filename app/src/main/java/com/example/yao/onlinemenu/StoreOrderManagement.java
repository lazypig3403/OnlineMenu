package com.example.yao.onlinemenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StoreOrderManagement extends AppCompatActivity {

    private TabLayout mTitles;
    private RecyclerView mRecyclerView;
    //控制點選頁面的標籤, 初始為0="未處理"Tab
    private int mListStyles = 0;
    private String titles[] = new String[]{ "未處理", "已接受", "已拒絕", "已結單" };
    //訂單狀態, 初始為0="未處理訂單"
    private int State_Order = 0;
    private static final int ID_YET = 0;
    private static final int ID_ACCEPT = 1;
    private static final int ID_REJECT = 2;
    private static final int ID_FINISH = 3;
    private String[] arrID, arrTime, arrCustomer, arrInfo, arrTotal;
    public static final String TAG = "TabActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order_management);

        mTitles = (TabLayout) findViewById(R.id.om_tabs);
        mRecyclerView = (RecyclerView) findViewById(R.id.om_rv);

//        //原程式寫法
//        mTitles.addTab(mTitles.newTab().setText(titles[0]).setTag(Constans.TAG_ANDROID));
        //添加頁面Tab
        mTitles.addTab(mTitles.newTab().setText(titles[0]).setTag(ID_YET));
        mTitles.addTab(mTitles.newTab().setText(titles[1]).setTag(ID_ACCEPT));
        mTitles.addTab(mTitles.newTab().setText(titles[2]).setTag(ID_REJECT));
        mTitles.addTab(mTitles.newTab().setText(titles[3]).setTag(ID_FINISH));

        //[連線]初始狀態時, 要先查詢第一頁內容顯示
        InputData(mListStyles);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(StoreOrderManagement.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new mRecyclerViewAdapter(mListStyles, StoreOrderManagement.this));
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mTitles.addOnTabSelectedListener(tabSelectedListener);
    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mListStyles = (int) tab.getTag();
            //[連線]依點選的標籤號碼查詢相關訂單
            InputData(mListStyles);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(StoreOrderManagement.this, LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setAdapter(new mRecyclerViewAdapter(mListStyles,StoreOrderManagement.this));
            //刷新adapter內資料
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    // [連線]查詢訂單詳細資訊
    private void InputData(int mStyle){
        State_Order = mStyle;
        //使用[顧客ID]和點選的訂單狀態[Stage_Order]查詢訂單

        //假資料
        switch (mStyle) {
            case ID_YET:
                arrID = new String[]{ "0920001", "0920002", "0920003" };
                arrTime = new String[]{ "2017/09/20 11:00", "2017/09/20 11:30", "2017/09/20 12:00" };
                arrCustomer = new String[]{ "yoman", "池欣雅", "林德榮" };
                arrInfo = new String[]{ "脆皮雞腿飯*1", "日式燒肉飯*1", "薄鹽鯖魚飯*2" };
                arrTotal = new String[]{ "$85","$75","$75" };
                break;

            case ID_ACCEPT:
                arrID = new String[]{ "0920003" };
                arrTime = new String[]{"2017/09/20 12:00" };
                arrCustomer = new String[]{ "林德榮" };
                arrInfo = new String[]{ "薄鹽鯖魚飯*2" };
                arrTotal = new String[]{ "$75" };
                break;

            case ID_REJECT:
                arrID = new String[]{ "0920002" };
                arrTime = new String[]{ "2017/09/20 11:30" };
                arrCustomer = new String[]{ "池欣雅" };
                arrInfo = new String[]{ "日式燒肉飯*1" };
                arrTotal = new String[]{ "$75" };
                break;

            case ID_FINISH:
                arrID = new String[]{ "0920001" };
                arrTime = new String[]{ "2017/09/20 11:00" };
                arrCustomer = new String[]{ "yoman" };
                arrInfo = new String[]{ "脆皮雞腿飯*1" };
                arrTotal = new String[]{ "$85" };
                break;
        }
    }

    public class mRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private int mListStyle;
        private Context mContext;

        private static final int TYPE_YET = 0;
        private static final int TYPE_ACCEPT = 1;
        private static final int TYPE_REJECT = 2;
        private static final int TYPE_FINISH = 3;
        private static final int TYPE_NODATA = 4;


        public mRecyclerViewAdapter(int mListStyle, Context context) {
            this.mListStyle = mListStyle;
            this.mContext = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            return new ItemViewHolder(inflater.inflate(R.layout.order_card, parent, false));
        }

        @Override
        public int getItemViewType(int position) {
            if (arrID.length == 0 ) return TYPE_NODATA;
            if (mListStyle == ID_YET) return TYPE_YET;
            if (mListStyle == ID_ACCEPT) return TYPE_ACCEPT;
            if (mListStyle == ID_REJECT) return TYPE_REJECT;
            if (mListStyle == ID_FINISH) return TYPE_FINISH;
            return TYPE_NODATA;
        }

        @Override
        public int getItemCount() {
            return arrID.length;
        }

        private class ItemViewHolder extends RecyclerView.ViewHolder {

            //            private final TextView mContent;
            protected final TextView mID, mTime, mCustomer, mInfo, mTotal;

            public ItemViewHolder(View view) {
                super(view);
//                mContent = (TextView) view.findViewById(R.id.txvContent);
                mID = (TextView) view.findViewById(R.id.orderID);
                mTime = (TextView) view.findViewById(R.id.orderTime);
                mCustomer = (TextView) view.findViewById(R.id.orderCustomer);
                mInfo = (TextView) view.findViewById(R.id.orderInfo);
                mTotal = (TextView) view.findViewById(R.id.orderTotal);
            }

            public void showData(int pos) {
                mID.setText(arrID[pos]);
                mTime.setText(arrTime[pos]);
                mCustomer.setText(arrCustomer[pos]);
                mInfo.setText(arrInfo[pos]);
                mTotal.setText(arrTotal[pos]);
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof ItemViewHolder) { ((ItemViewHolder) holder).showData(position); }

            //to StoreOrderDetail
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //設定目標為DishDetail
                    Intent intent = new Intent();
                    intent.setClass(holder.itemView.getContext(), StoreOrderDetail.class);

                    //傳送資料到DishDetail (到時要檢查Bundle會不會使切換頁面crash)
                    //只傳送訂單編號,在詳細頁面再全部查詢, State_Order是為了顯示訂單的不同按鈕
                    Bundle bd_send = new Bundle();
                    bd_send.putInt("state_order", State_Order);
                    bd_send.putString("orderID", arrID[position]);
                    intent.putExtras(bd_send);

                    //啟動intent
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }

        //原程式碼(我想留著030)
        private class NoDataViewHolder extends RecyclerView.ViewHolder {
            public NoDataViewHolder(View view) {
                super(view);
            } }

        private class MoreOrderViewHolder extends RecyclerView.ViewHolder {
            public MoreOrderViewHolder(View view) {
                super(view);
            } }
    }
}
