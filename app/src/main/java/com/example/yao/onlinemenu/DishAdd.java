package com.example.yao.onlinemenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class DishAdd extends AppCompatActivity {
    private Button bt;
    private EditText et;
    private TextView tv;
    private String s1="", s2="" , s3="" , s4="" ;
    private String[] itemsDT,itemsDmeat,itemsDseafood,itemsDseasoning,itemsDelse;
    private int selectionDT;
    private boolean[] selectionDmeat,selectionDseafood,selectionDseasoning,selectionDelse;


    //連線資料
    String dName="",dPrice="",dType="",dIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        item();
        process();
    }

    void item(){
        itemsDT = new String[]{ "飯類", "麵類", "湯類", "小菜類", "其他" };
        selectionDT = -1;
        itemsDmeat = new String[]{ "牛肉" , "豬肉" , "羊肉" , "雞肉" , "鴨肉" } ;
        selectionDmeat = new boolean[itemsDmeat.length];  Arrays.fill(selectionDmeat,Boolean.FALSE);
        itemsDseafood = new String[]{ "蟹類" , "蝦類" , "魚肉" , "蚌類" } ;
        selectionDseafood = new boolean[itemsDseafood.length];  Arrays.fill(selectionDseafood,Boolean.FALSE);
        itemsDseasoning = new String[]{ "蒜頭" , "胡椒" , "九層塔" , "蔥" , "香菜" ,"洋蔥"} ;
        selectionDseasoning = new boolean[itemsDseasoning.length];  Arrays.fill(selectionDseasoning,Boolean.FALSE);
        itemsDelse = new String[]{ "飯","麵","水餃","蛋" , "牛奶" , "豆類"} ;
        selectionDelse = new boolean[itemsDelse.length];  Arrays.fill(selectionDelse,Boolean.FALSE);
    }

    void process(){
        bt = (Button) findViewById(R.id.dishAdd_confirm);
        bt.setOnClickListener(SaveListener);
        bt = (Button) findViewById(R.id.dishAdd_cancel);
        bt.setOnClickListener(SaveListener);
        bt = (Button) findViewById(R.id.bt_DishType);
        bt.setOnClickListener(new chooseListener());
        bt = (Button) findViewById(R.id.bt_meat);
        bt.setOnClickListener(new chooseListener());
        bt = (Button) findViewById(R.id.bt_seafood);
        bt.setOnClickListener(new chooseListener());
        bt = (Button) findViewById(R.id.bt_seasoning);
        bt.setOnClickListener(new chooseListener());
        bt = (Button) findViewById(R.id.bt_else);
        bt.setOnClickListener(new chooseListener());
    }

    private class chooseListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int id = v.getId();
            // 餐點類型
            if( id == R.id.bt_DishType ){
                AlertDialog.Builder choose = new AlertDialog.Builder(DishAdd.this);
                choose.setTitle("餐點類型");
                choose.setSingleChoiceItems(itemsDT, selectionDT,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 selectionDT = which;
                            }
                        });
                choose.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if( selectionDT != -1 ) {
                            tv = (TextView) findViewById(R.id.edit_dishType);
                            tv.setText(itemsDT[selectionDT]);
                        }
                    }
                });
                choose.show();
            }

            // 食材
            if( id == R.id.bt_meat) {
                AlertDialog.Builder chooseSmeat = new AlertDialog.Builder(DishAdd.this);
                chooseSmeat.setTitle("肉類");
                chooseSmeat.setMultiChoiceItems(itemsDmeat, selectionDmeat,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked)
                            { selectionDmeat[which] = isChecked; }
                        });

                chooseSmeat.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        // s1=" ";
                        for (int i = 0; i < selectionDmeat.length; i++) {
                            if (selectionDmeat[i]) { sb.append(itemsDmeat[i] + " "); }
                        }
                        s1 = sb.toString();
                        tv = (TextView) findViewById(R.id.edit_dishIngredients);
                        tv.setText(s1 + " " + s2 + " " + s3 + " " + s4);
                    }
                });
                chooseSmeat.show() ;
            }

            if( id == R.id.bt_seafood) {
                AlertDialog.Builder chooseSmeat = new AlertDialog.Builder(DishAdd.this);
                chooseSmeat.setTitle("海鮮");
                chooseSmeat.setMultiChoiceItems(itemsDseafood, selectionDseafood,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked)
                            { selectionDseafood[which] = isChecked; }
                        });

                chooseSmeat.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        // s1=" ";
                        for (int i = 0; i < selectionDseafood.length; i++) {
                            if (selectionDseafood[i]) { sb.append(itemsDseafood[i] + " "); }
                        }
                        s2 = sb.toString();
                        tv = (TextView) findViewById(R.id.edit_dishIngredients);
                        tv.setText(s1 + " " + s2 + " " + s3 + " " + s4);
                    }
                });
                chooseSmeat.show() ;
            }

            if( id == R.id.bt_seasoning) {
                AlertDialog.Builder chooseSseasoning = new AlertDialog.Builder(DishAdd.this);
                chooseSseasoning.setTitle("辛香料");
                chooseSseasoning.setMultiChoiceItems(itemsDseasoning, selectionDseasoning,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked)
                            { selectionDseasoning[which] = isChecked; }
                        });

                chooseSseasoning.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        //s3=" ";
                        for (int i = 0; i < selectionDseasoning.length; i++) {
                            if (selectionDseasoning[i]) { sb.append(itemsDseasoning[i] + " "); }
                        }
                        s3 = sb.toString();
                        tv = (TextView) findViewById(R.id.edit_dishIngredients);
                        tv.setText(s1 + " " + s2 + " " + s3 + " " + s4);
                    }
                });
                chooseSseasoning.show() ;
            }

            if( id == R.id.bt_else) {
                AlertDialog.Builder chooseSseasoning = new AlertDialog.Builder(DishAdd.this);
                chooseSseasoning.setTitle("其他");
                chooseSseasoning.setMultiChoiceItems(itemsDelse, selectionDelse,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked)
                            { selectionDelse[which] = isChecked; }
                        });

                chooseSseasoning.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        //s3=" ";
                        for (int i = 0; i < selectionDelse.length; i++) {
                            if (selectionDelse[i]) { sb.append(itemsDelse[i] + " "); }
                        }
                        s4 = sb.toString();
                        tv = (TextView) findViewById(R.id.edit_dishIngredients);
                        tv.setText(s1 + " " + s2 + " " + s3 + " " + s4);
                    }
                });
                chooseSseasoning.show() ;
            }
        }
    }

    // 判斷連線資料
    void InitData(){
        et = (EditText) findViewById(R.id.edit_dishName);
        dName = et.getText().toString();
        et = (EditText) findViewById(R.id.edit_dishPrice);
        dPrice = et.getText().toString();
        tv = (TextView) findViewById(R.id.edit_dishType);
        dType = tv.getText().toString();
        tv = (TextView) findViewById(R.id.edit_dishIngredients);
        dIngredients = tv.getText().toString();
    }

    //儲存變更並移動
    View.OnClickListener SaveListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 判斷資料
                    InitData();
                    Toast.makeText(DishAdd.this, dName+","+dPrice+","+dType+","+dIngredients, Toast.LENGTH_SHORT).show();

                    switch (v.getId()) {
                        case R.id.dishAdd_confirm:
                            new AlertDialog.Builder(DishAdd.this)
                                    .setTitle("確認視窗")
                                    .setMessage("確定儲存?")
                                    .setPositiveButton("確定",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setClass(DishAdd.this, StoreSelect.class);
                                                    startActivity(intent);
                                                    DishAdd.this.finish();
                                                }
                                            })
                                    .setNegativeButton("取消",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {  }
                                            }).show();
                            break;

                        case R.id.dishAdd_cancel:
                            new AlertDialog.Builder(DishAdd.this)
                                    .setTitle("確認視窗")
                                    .setMessage("確定取消?  離開後將不會儲存其變更")
                                    .setPositiveButton("確定",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setClass(DishAdd.this, StoreSelect.class);
                                                    startActivity(intent);
                                                    DishAdd.this.finish();
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

                //回到上一個fragment而非class//
                int count = getFragmentManager().getBackStackEntryCount();
                if (count == 0) {
                    super.onBackPressed();
                } else {
                    getFragmentManager().popBackStack();
                }

//                Intent intent = new Intent();
//                intent.setClass(DishAdd.this, StoreAdd.class);
//                startActivity(intent);
                DishAdd.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
//            Intent intent = new Intent();
//            intent.setClass(DishAdd.this, StoreSelect.class);
//            startActivity(intent);
//        }
//        return true;
//    }
}
