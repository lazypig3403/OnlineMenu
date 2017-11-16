package com.example.yao.onlinemenu;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreAdd extends AppCompatActivity {

    private EditText storeName,storeAddress,storePhone;
    private ToggleButton mon,tue,wed,thu,fri,sat,sun;
    private TextView t11,t13,t21,t23,t31,t33,tv;
    private TableRow tr1,tr2,tr3;
    private RadioButton parkY,parkN,bookY,bookN,deliveryY,deliveryN;
    private CheckBox c24h;
    private Button stype,stypeElse,add_t,goStoreSelect,goAddDish,cancel;
    int timeh=0;  //填了幾組時間

    private String[] itemsST;
    private boolean[] selectionST;
    //連線資料

    String sName="",sAddress="",sPhone="",parking="",booking="",delivering="",storeType="";
    String time_mon="",time_tue="",time_wed="",time_thu="",time_fri="",time_sat="",time_sun="";
    String openTime[] = new String[7];

    private ApiInterface_Store_Info ApiSI;
    private String oID;
    private String status = "storecreate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemsST = new String[]{"早餐", "午餐", "下午茶", "晚餐"};
        selectionST = new boolean[itemsST.length];;
        Arrays.fill(selectionST,Boolean.FALSE);

        processViews();
        processControllers();

    }

    private void processViews()
    {
        storeName = (EditText) findViewById(R.id.editText_storename);
        storeAddress = (EditText) findViewById(R.id.editText_address);
        storePhone = (EditText) findViewById(R.id.editText_phone);
        mon = (ToggleButton) findViewById(R.id.Mon);
        tue = (ToggleButton) findViewById(R.id.Tue);
        wed = (ToggleButton) findViewById(R.id.Wed);
        thu = (ToggleButton) findViewById(R.id.Thu);
        fri = (ToggleButton) findViewById(R.id.Fri);
        sat = (ToggleButton) findViewById(R.id.Sat);
        sun = (ToggleButton) findViewById(R.id.Sun);
//        add_t = (Button) findViewById(R.id.add_time);
        t11 = (TextView) findViewById(R.id.time11);
        t13 = (TextView) findViewById(R.id.time13);
        t21 = (TextView) findViewById(R.id.time21);
        t23 = (TextView) findViewById(R.id.time23);
        t31 = (TextView) findViewById(R.id.time31);
        t33 = (TextView) findViewById(R.id.time33);
        c24h = (CheckBox) findViewById(R.id.check24h);
        tr1 = (TableRow)findViewById(R.id.timeR1);
        tr2 = (TableRow)findViewById(R.id.timeR2);
        tr3 = (TableRow)findViewById(R.id.timeR3);
        parkY = (RadioButton) findViewById(R.id.radioButton_parking_y);
        parkN = (RadioButton) findViewById(R.id.radioButton_parking_n);
        bookY = (RadioButton) findViewById(R.id.radioButton_booking_y);
        bookN = (RadioButton) findViewById(R.id.radioButton_booking_n);
        deliveryY = (RadioButton) findViewById(R.id.radioButton_delivery_y);
        deliveryN = (RadioButton) findViewById(R.id.radioButton_delivery_n);
        stype = (Button) findViewById(R.id.bt_StroeType);
//        stypeElse = (Button) findViewById(R.id.bt_StroeTypeElse);
        goStoreSelect = (Button) findViewById(R.id.storeAdd_goStoreSelect);
        goAddDish = (Button) findViewById(R.id.storeAdd_goAddDish);
        cancel = (Button) findViewById(R.id.storeAdd_cancel);
    }

    private void processControllers()
    {
        goStoreSelect.setOnClickListener(SaveListener);
        goAddDish.setOnClickListener(SaveListener);
        cancel.setOnClickListener(CancelListener);
        mon.setOnClickListener(ChangeCr);
        tue.setOnClickListener(ChangeCr);
        wed.setOnClickListener(ChangeCr);
        thu.setOnClickListener(ChangeCr);
        fri.setOnClickListener(ChangeCr);
        sat.setOnClickListener(ChangeCr);
        sun.setOnClickListener(ChangeCr);
        t11.setOnClickListener(TimeListener);
        t13.setOnClickListener(TimeListener);
        t21.setOnClickListener(TimeListener);
        t23.setOnClickListener(TimeListener);
        t31.setOnClickListener(TimeListener);
        t33.setOnClickListener(TimeListener);

        c24h.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if( c24h.isChecked() ) {
                    tr1.setVisibility(View.GONE);
                    tr2.setVisibility(View.GONE);
                    tr3.setVisibility(View.GONE);
                }
                else {
                    tr1.setVisibility(View.VISIBLE);
                }
            }
        });

        parkY.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if( parkY.isChecked() ) { parkN.setChecked(false); }
            }
        });
        parkN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if( parkN.isChecked() ) { parkY.setChecked(false); }
            }
        });
        bookY.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if( bookY.isChecked() ) { bookN.setChecked(false); }
            }
        });
        bookN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if( bookN.isChecked() ) { bookY.setChecked(false); }
            }
        });
        deliveryY.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if( deliveryY.isChecked() ) { deliveryN.setChecked(false); }
            }
        });
        deliveryN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if( deliveryN.isChecked() ) { deliveryY.setChecked(false); }
            }
        });

        stype.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // choose 對話框參數
                AlertDialog.Builder choose = new AlertDialog.Builder(StoreAdd.this);
                choose.setTitle("餐廳類型");
                choose.setMultiChoiceItems(itemsST, selectionST,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked)
                            { selectionST[which] = isChecked; }
                        });
                choose.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < selectionST.length; i++) {
                            if (selectionST[i]) { sb.append(itemsST[i] + " "); }
                        }
                        storeType = sb.toString();
                        tv = (TextView)findViewById(R.id.edit_StoreType);
                        tv.setText(storeType);
                    }
                });
                choose.show();
            }
        });
    }

    //ToggleButton變色
    View.OnClickListener ChangeCr =
            new View.OnClickListener() {
                //不懂為何是int陣列???
                int week[]={ R.id.Mon, R.id.Tue, R.id.Wed, R.id.Thu, R.id.Fri, R.id.Sat, R.id.Sun };
                ToggleButton tb;
                @Override
                public void onClick(View view) {
                    for(int i=0; i<week.length; i++) {
                        tb = (ToggleButton) findViewById(week[i]);
                        if( tb.isChecked() ) { tb.setBackgroundColor(0xFFBDBDBD); }
                        if( !tb.isChecked() ) { tb.setBackgroundColor(0xFFEEEEEE); }
                    }
                }
            };

    //呼叫設定時間
    View.OnClickListener TimeListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Use the current time as the default values for the picker, 一定要在OnClick裡
                    final Calendar c = Calendar.getInstance();
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    switch ( (v.getId()) ){
                        case R.id.time11:
                            new TimePickerDialog(StoreAdd.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    t11.setText( (hourOfDay < 10 ? "0" : "") + hourOfDay
                                            + ":" + (minute < 10 ? "0" : "") + minute );
                                    timeh = 1;
                                }
                            }, hour, minute, true).show();
                            break;
                        case R.id.time13:
                            new TimePickerDialog(StoreAdd.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    t13.setText( (hourOfDay < 10 ? "0" : "") + hourOfDay
                                            + ":" + (minute < 10 ? "0" : "") + minute );
                                    timeh = 1;
                                }
                            }, hour, minute, true).show();
                            break;
//                        case R.id.time21:
//                            new TimePickerDialog(StoreAdd.this, new TimePickerDialog.OnTimeSetListener() {
//                                @Override
//                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                    t21.setText( (hourOfDay < 10 ? "0" : "") + hourOfDay
//                                            + ":" + (minute < 10 ? "0" : "") + minute );
//                                    timeh = 2;
//                                }
//                            }, hour, minute, true).show();
//                            break;
//                        case R.id.time23:
//                            new TimePickerDialog(StoreAdd.this, new TimePickerDialog.OnTimeSetListener() {
//                                @Override
//                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                    t23.setText( (hourOfDay < 10 ? "0" : "") + hourOfDay
//                                            + ":" + (minute < 10 ? "0" : "") + minute );
//                                    timeh = 2;
//                                }
//                            }, hour, minute, true).show();
//                            break;
//                        case R.id.time31:
//                            new TimePickerDialog(StoreAdd.this, new TimePickerDialog.OnTimeSetListener() {
//                                @Override
//                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                    t31.setText( (hourOfDay < 10 ? "0" : "") + hourOfDay
//                                            + ":" + (minute < 10 ? "0" : "") + minute );
//                                    timeh = 3;
//                                }
//                            }, hour, minute, true).show();
//                            break;
//                        case R.id.time33:
//                            new TimePickerDialog(StoreAdd.this, new TimePickerDialog.OnTimeSetListener() {
//                                @Override
//                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                    t33.setText( (hourOfDay < 10 ? "0" : "") + hourOfDay
//                                            + ":" + (minute < 10 ? "0" : "") + minute );
//                                    timeh = 3;
//                                }
//                            }, hour, minute, true).show();
//                            break;
                    }
                }
            };

    // 判斷資料
    void InitData(){
        //EditView值
        sName = storeName.getText().toString();
        sAddress = storeAddress.getText().toString();
        sPhone = storePhone.getText().toString();

        //營業時間
        String time1 = t11.getText().toString() + "-" + t13.getText().toString();
        String time2 = t21.getText().toString() + "-" + t23.getText().toString();
        String time3 = t31.getText().toString() + "-" + t33.getText().toString();
        //不懂為何是int陣列???
        int week[]={ R.id.Mon, R.id.Tue, R.id.Wed, R.id.Thu, R.id.Fri, R.id.Sat, R.id.Sun };
        ToggleButton tb;

        for(int i=0; i<week.length; i++) {
            tb = (ToggleButton) findViewById(week[i]);
            if (tb.isChecked() && !c24h.isChecked() && timeh==1 ) {
                openTime[i] = tb.getText().toString() + time1;
            }
            if (tb.isChecked() && c24h.isChecked()) {
                openTime[i] = tb.getText().toString() + " 24小時營業";
            }
            if (tb.isChecked() == false) {
                openTime[i] = tb.getText().toString() + " 公休";
            }
            if ( tb.isChecked() && !c24h.isChecked() && timeh == 2) {}  //2組時間
            if ( tb.isChecked() && !c24h.isChecked() && timeh == 3) {}  //3組時間
        }

        //將opentime[]分別asign給星期一到星期日等7個變數
//        time_mon=openTime[0];
//        time_tue=openTime[1];
//        time_wed=openTime[2];
//        time_thu=openTime[3];
//        time_fri=openTime[4];
//        time_sat=openTime[5];
//        time_sun=openTime[6];

        //RadioButton
        if( parkY.isChecked() ) { parking=parkY.getText().toString(); }
        if( parkN.isChecked() ) { parking=parkN.getText().toString(); }
        if( bookY.isChecked() ) { booking=bookY.getText().toString(); }
        if( bookN.isChecked() ) { booking=bookN.getText().toString(); }
        if( deliveryY.isChecked() ) { delivering=deliveryY.getText().toString(); }
        if( deliveryN.isChecked() ) { delivering=deliveryN.getText().toString(); }

//        //CheckedButton
//        int cb_all[]={ R.id.checkBox1,R.id.checkBox2,R.id.checkBox3,R.id.checkBox4 };
//        CheckBox cb;
//        storeType = "";
//        for(int i=0; i<cb_all.length; i++){
//            cb = (CheckBox)findViewById(cb_all[i]);
//            if(cb.isChecked()) { storeType = storeType + " " + cb.getText().toString(); }
//        }



    }

    //儲存變更並移動
    View.OnClickListener SaveListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 判斷資料
                    InitData();

                    //檢查

//                    for(int j=0;j<openTime.length;j++){Toast.makeText(StoreAdd.this, openTime[j], Toast.LENGTH_SHORT).show();}

                    ApiSI = ApiClient.getClient().create(ApiInterface_Store_Info.class);

                    Call<ServerResponse_storeinfo> call = ApiSI.store_create(status,sName,sAddress,sPhone,storeType,parking,booking,delivering,
                            openTime[0],openTime[1],openTime[2],openTime[3],openTime[4],openTime[5],openTime[6]);

                    call.enqueue(new Callback<ServerResponse_storeinfo>()
                    {
                        @Override
                        public void onResponse(Call<ServerResponse_storeinfo> call, Response<ServerResponse_storeinfo> SRsi)
                        {
                            if (SRsi.body().getSuccess()==1)
                            {showToast_posi();}
                            else
                            {showToast_fail();}
                        }

                        @Override
                        public void onFailure(Call<ServerResponse_storeinfo> call, Throwable t)
                        {call.cancel();}
                    });

                    switch (v.getId()) {
                        case R.id.storeAdd_goStoreSelect:
                            new AlertDialog.Builder(StoreAdd.this)
                                    .setTitle("確認視窗")
                                    .setMessage("確定儲存?  回到店面列表")
                                    .setPositiveButton("確定",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setClass(StoreAdd.this, StoreSelect.class);
                                                    startActivity(intent);
                                                    StoreAdd.this.finish();
                                                }
                                            })
                                    .setNegativeButton("取消",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {  }
                                            }).show();
                            break;

                        case R.id.storeAdd_goAddDish:
                            new AlertDialog.Builder(StoreAdd.this)
                                    .setTitle("確認視窗")
                                    .setMessage("確定儲存?  開始新增餐點")
                                    .setPositiveButton("確定",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setClass(StoreAdd.this, DishAdd.class);
                                                    startActivity(intent);
                                                }
                                            })
                                    .setNegativeButton("取消",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {  }
                                            }).show();
                            break;
                    }
                }
            };

    View.OnClickListener CancelListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 判斷資料
                    InitData();
                    Toast.makeText(StoreAdd.this, parking+","+booking+","+delivering+","+storeType
                            , Toast.LENGTH_SHORT).show();
                    switch (v.getId()) {
                        case R.id.storeAdd_cancel:
                            new AlertDialog.Builder(StoreAdd.this)
                                    .setTitle("確認視窗")
                                    .setMessage("確定取消?  離開後將不會儲存其變更")
                                    .setPositiveButton("確定",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setClass(StoreAdd.this, StoreSelect.class);
                                                    startActivity(intent);
                                                    StoreAdd.this.finish();
                                                }
                                            })
                                    .setNegativeButton("取消",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {  }
                                            }).show();
                            break;
                    }
                }
            };

    //返回鍵操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(StoreAdd.this, StoreSelect.class);
                startActivity(intent);
                StoreAdd.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            Intent intent = new Intent();
            intent.setClass(StoreAdd.this, StoreSelect.class);
            startActivity(intent);
            StoreAdd.this.finish();
        }
        return true;
    }

    public void showToast_posi(){Toast.makeText(this,"資料已送出",Toast.LENGTH_LONG).show();}
    public void showToast_fail(){Toast.makeText(this, "傳送失敗", Toast.LENGTH_LONG).show();}
}
