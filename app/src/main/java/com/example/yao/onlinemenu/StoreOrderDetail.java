package com.example.yao.onlinemenu;

import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 String/Int : State_Order；需要的View
 訂單尚未處理:yet /0 ； View:[訂單狀態欄:(預設)尚未處理], Button:回覆.接受(State_Oder=1).拒絕(State_Oder=2)
 訂單接受:accept /1 ； View:[訂單狀態欄:已接受,處理中], 按鈕:回覆.出餐(出餐全數完成時,State_Oder=3)
 訂單拒絕:reject /2 ；View:[訂單狀態欄:已拒絕], 按鈕:回覆
 訂單結單:done /3 ； View:[訂單狀態欄:已結單/已出餐], 按鈕:評價(此筆訂單&顧客)
 */

public class StoreOrderDetail extends AppCompatActivity {

    //依訂單狀態做不同按鈕的顯示
    private Button btn, btReply, btAccept, btReject, btServe, btReview;
    private TextView tv;
    private AlertDialog.Builder confirm, exit;
    private String orderID, oStateText;
    private int orderState;
    private float orderRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("訂單詳細");
        setContentView(R.layout.activity_store_order_detail);
        processView();
        CreateDialog();

        //接收訂單編號&訂單狀態, 查詢顯示
        Bundle bd_receive = getIntent().getExtras();
        orderID = bd_receive.getString("orderID");
        orderState = bd_receive.getInt("state_order");
        SelectOrder(orderID);
        CheckState(orderState);
    }

    //宣告物件&事件
    private void processView() {
        //按鈕們
        btReply = (Button) findViewById(R.id.bt_orderReply);
        btReply.setOnClickListener(new OrderListener());
        btAccept = (Button) findViewById(R.id.bt_orderAccept);
        btAccept.setOnClickListener(new OrderListener());
        btReject = (Button) findViewById(R.id.bt_orderReject);
        btReject.setOnClickListener(new OrderListener());
        btServe = (Button) findViewById(R.id.bt_orderServingFinish);
        btServe.setOnClickListener(new OrderListener());
        btReview = (Button) findViewById(R.id.bt_orderReview);
        btReview.setOnClickListener(new OrderListener());
    }
    //建立一些重複使用的對話框
    private void CreateDialog(){

        //取消對話框時提醒
        exit = new AlertDialog.Builder(StoreOrderDetail.this);
        exit.setTitle("確定取消?");
        exit.setMessage("離開後不會儲存其變更");
        exit.setCancelable(false);
        exit.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {    }
        });
//        exit.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {    }
//        });

        //        //確認選項時(暫時不想改)
//        confirm = new AlertDialog.Builder(StoreOrderDetail.this);
//        confirm.setCancelable(false);
//        confirm.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {    }
//        });

    }

    //[連線-查詢]利用顧客編號&訂單編號[orderID], 查詢出訂單所有資訊
    private void SelectOrder(String oID){
        //測試用
        Toast.makeText(StoreOrderDetail.this, "訂單編號:"+ orderID , Toast.LENGTH_SHORT).show();

        String oTime, oTotal, oCName, oCPhone, oCAddress, oForHereToGo, oArrivalTime, oCMessage, oSMessage;
        String[] oItemName, oItemNumber, oItemPrice;



//        //填裝資料
        tv = (TextView) findViewById(R.id.detail_orderID);  tv.setText(oID);
//        tv = (TextView) findViewById(R.id.detail_orderTime);    tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderTotal);   tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderCustomerName);    tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderCustomerPhone);   tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderCustomerAddress);   tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderForHereToGo);   tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderArrivalTime);   tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderCustomerMessage);   tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderStoreMessage);   tv.setText();

//        //特別處理, 多筆資料顯示問題=_=
//        tv = (TextView) findViewById(R.id.detail_orderItemName);    tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderItemNumber);  tv.setText();
//        tv = (TextView) findViewById(R.id.detail_orderItemPrice);   tv.setText();
    }

    //依訂單狀態做相對應動作
    private void CheckState(int oState) {
        switch (oState){
            case 0:
                oStateText = "尚未處理";
                btReply.setVisibility(View.VISIBLE);
                btAccept.setVisibility(View.VISIBLE);
                btReject.setVisibility(View.VISIBLE);
                btServe.setVisibility(View.GONE);
                btReview.setVisibility(View.GONE);
                tv = (TextView) findViewById(R.id.detail_orderState);
                tv.setText(oStateText);
                break;
            case 1:
                oStateText = "已接受,處理中";
                btReply.setVisibility(View.VISIBLE);
                btAccept.setVisibility(View.GONE);
                btReject.setVisibility(View.GONE);
                btServe.setVisibility(View.VISIBLE);
                btReview.setVisibility(View.GONE);
                tv = (TextView) findViewById(R.id.detail_orderState);
                tv.setText(oStateText);
                break;
            case 2:
                oStateText = "已拒絕";
                btReply.setVisibility(View.GONE);
                btAccept.setVisibility(View.GONE);
                btReject.setVisibility(View.GONE);
                btServe.setVisibility(View.GONE);
                btReview.setVisibility(View.GONE);
                tv = (TextView) findViewById(R.id.detail_orderState);
                tv.setText(oStateText);
                break;
            case 3:
                oStateText = "已結單/已出餐";
                btReply.setVisibility(View.VISIBLE);
                btAccept.setVisibility(View.GONE);
                btReject.setVisibility(View.GONE);
                btServe.setVisibility(View.GONE);
                btReview.setVisibility(View.VISIBLE);
                tv = (TextView) findViewById(R.id.detail_orderState);
                tv.setText(oStateText);
                break;
        }
    }

    private class OrderListener implements View.OnClickListener{
        //一次要使用多個對話框,以後可以用DialogFragment取代
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_orderReply:
                    final Dialog replyDialog = new Dialog(StoreOrderDetail.this);
                    replyDialog.setContentView(R.layout.activity_store_order_reply);
                    replyDialog.setCancelable(true);
                    replyDialog.setOnCancelListener(OnDialogCancel);
                    final EditText edtReply = (EditText) replyDialog.findViewById(R.id.edit_orderReply);
                    final Spinner spnReply = (Spinner) replyDialog.findViewById(R.id.spn_orderReply);
                    spnReply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String[] reasons = getResources().getStringArray(R.array.reasons_orderReply);
                            if(position > 0) { edtReply.setText(reasons[position]); }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {  }
                    });
                    btn = (Button) replyDialog.findViewById(R.id.bt_orderReplySend);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //[連線-上傳]用訂單編號, 上傳店家留言[edtReply]


                            //刷新頁面狀態(之後有緣寫)
                            //Dialog需手動關閉, AlertDialog不用
                            replyDialog.dismiss();
                        }
                    });
                    btn = (Button) replyDialog.findViewById(R.id.bt_orderReplyCancel);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { replyDialog.cancel(); }
                    });
                    //now that the dialog is set up, it's time to show it
                    replyDialog.show();
                    break;

                case R.id.bt_orderAccept:
                    AlertDialog.Builder accept = new AlertDialog.Builder(StoreOrderDetail.this);
                    accept.setTitle("接受訂單");
                    accept.setMessage("確定接受訂單?");
                    accept.setCancelable(false);
                    accept.setPositiveButton("接受", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //[連線-上傳]用訂單編號,上傳更改後的訂餐狀態
                            orderState = 1;
                            CheckState(orderState);


                            //刷新頁面狀態(之後有緣寫)
                        }
                    });
                    accept.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {    }
                    });
                    accept.show();
                    break;

                case R.id.bt_orderReject:
                    AlertDialog.Builder reject = new AlertDialog.Builder(StoreOrderDetail.this);
                    reject.setTitle("拒絕訂單");
                    reject.setMessage("確定拒絕訂單?");
                    reject.setCancelable(false);
                    reject.setPositiveButton("拒絕", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //[連線-上傳]用訂單編號,上傳更改後的訂餐狀態
                            orderState = 2;
                            CheckState(orderState);


                            //刷新頁面狀態(之後有緣寫)
                        }
                    });
                    reject.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {    }
                    });
                    reject.show();
                    break;

                case R.id.bt_orderServingFinish:
                    AlertDialog.Builder serve = new AlertDialog.Builder(StoreOrderDetail.this);
                    serve.setTitle("出餐通知");
                    serve.setMessage("餐點已全數出餐完畢,送出通知?");
                    serve.setCancelable(false);
                    serve.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //[連線]用訂單編號,上傳更改後的訂餐狀態
                            orderState = 3;
                            CheckState(orderState);


                            //刷新頁面狀態(之後有緣寫)
                        }
                    });
                    serve.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {    }
                    });
                    serve.show();
                    break;

                case R.id.bt_orderReview:
                    final Dialog rankDialog = new Dialog(StoreOrderDetail.this);
                    rankDialog.setContentView(R.layout.activity_store_order_review);
                    rankDialog.setCancelable(true);
                    rankDialog.setOnCancelListener(OnDialogCancel);
                    final RatingBar ratingBar = (RatingBar)rankDialog.findViewById(R.id.rb_orderReview);
                    ratingBar.setMax(5); //設定最大值
                    ratingBar.setNumStars(5); //設定最大星型數量
                    ratingBar.setStepSize(0.5f); //設定步進值
                    ratingBar.setRating((float) 3.5); //設定目前顯示的星型數量
                    ratingBar.setIsIndicator(false); //設定是否不被使用者修改評分
                    ratingBar.setOnRatingBarChangeListener(OnRatingBarChange);//設定監聽器
                    btn = (Button) rankDialog.findViewById(R.id.bt_orderRatingSend);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //[連線]用訂單編號, 上傳評分星數[orderRating], (評分完隱藏評價按鈕?)


                            //刷新頁面狀態(之後有緣寫)
                            btReview.setVisibility(View.GONE);
                            //Dialog需手動關閉, AlertDialog不用
                            rankDialog.dismiss();
                        }
                    });
                    btn = (Button) rankDialog.findViewById(R.id.bt_orderRatingCancel);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { rankDialog.cancel(); }
                    });
                    rankDialog.show();
                    break;
            }
        }
    }

    private Dialog.OnCancelListener OnDialogCancel = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            AlertDialog cancel = exit.create();
            cancel.show();
        }
    };

    private RatingBar.OnRatingBarChangeListener OnRatingBarChange = new RatingBar.OnRatingBarChangeListener()
    {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating,boolean fromUser)
        {
            orderRating = rating;
            Toast.makeText(StoreOrderDetail.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();
        }
    };
}

