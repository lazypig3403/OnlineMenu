package com.example.yao.onlinemenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class Search extends AppCompatActivity {


    private String[] itemsSC = { "早餐" , "午餐" , "下午茶" , "晚餐" , "泰式" , "韓式" , "日式" , "法式" , "美式" , "港式" } ;
    private boolean[] selectionSC = { false , false ,false ,false , false ,false ,false , false ,false ,false } ;

    private String[] itemsSmeat = { "牛肉" , "豬肉" , "羊肉" , "雞肉"  } ;
    private boolean[] selectionSmeat = { false , false ,false ,false  } ;

    private String[] itemsSseafood = { "蟹類" , "蝦類" , "魚肉" , "蚌類" } ;
    private boolean[] selectionSseafood = { false , false ,false ,false } ;

    private String[] itemsSseasoning = { "蒜頭" , "胡椒" , "九層塔" , "蔥" , "香菜"  } ;
    private boolean[] selectionSseasoning = { false , false ,false ,false , false } ;

    private String[] itemsSelse = { "蛋" , "牛奶" , "豆類"   } ;
    private boolean[] selectionSelse = { false , false ,false  } ;

    Button mButton ,button ,buttonS ,Dbutton ;
    private TextView search類別 ,search食材  ;
    private String searchtag , searchkeyword , searchdollar_1 , searchdollar_2 ;
    String s0="" ,s1="", s2="" , s3="" , s4="" ;
    int time=1 ;

    EditText ETkeyword ,ETdollar_1 ,ETdollar_2 ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //關鍵字 金額輸入存取
        ETkeyword = (EditText)findViewById(R.id.editText_keyword)  ;


        ETdollar_1 = (EditText)findViewById(R.id.editText_dollar_1)  ;


        ETdollar_2 = (EditText)findViewById(R.id.editText_dollar_2)  ;



        search類別=(TextView)findViewById(R.id.search類別);
        search食材=(TextView)findViewById(R.id.search食材);

        mButton = (Button) findViewById(R.id.buttonSC);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // choose 對話框參數
                AlertDialog.Builder chooseSC = new AlertDialog.Builder(Search.this);
                chooseSC.setTitle("餐廳類型");
                chooseSC.setMultiChoiceItems(itemsSC, selectionSC,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                selectionSC[which] = isChecked;
                            }
                        });

                chooseSC.setPositiveButton("確定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();


                        for (int i = 0; i < selectionSC.length; i++) {
                            if (selectionSC[i]) {
                                sb.append(itemsSC[i] + " ");
                            }
                        }
                        s0 = sb.toString();
                        search類別.setText(sb.toString());
                    }
                });
                chooseSC.show() ; }
        });


        mButton = (Button) findViewById(R.id.buttomS肉類);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // choose 對話框參數
                AlertDialog.Builder chooseSmeat = new AlertDialog.Builder(Search.this);
                chooseSmeat.setTitle("肉類");
                chooseSmeat.setMultiChoiceItems(itemsSmeat, selectionSmeat,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                selectionSmeat[which] = isChecked;
                            }
                        });

                chooseSmeat.setPositiveButton("確定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                       // s1=" ";
                        for (int i = 0; i < selectionSmeat.length; i++) {
                            if (selectionSmeat[i]) {
                                sb.append(itemsSmeat[i] + " ");
                            }
                        }

                            s1 = sb.toString();
                            search食材.setText(s1 + " " + s2 + " " + s3 + " " + s4);

                    }
                });

                chooseSmeat.show() ; }
        });




        mButton = (Button) findViewById(R.id.buttomS海鮮);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // choose 對話框參數
                AlertDialog.Builder chooseSseafood = new AlertDialog.Builder(Search.this);
                chooseSseafood.setTitle("海鮮");
                chooseSseafood.setMultiChoiceItems(itemsSseafood, selectionSseafood,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                selectionSmeat[which] = isChecked;
                            }
                        });

                chooseSseafood.setPositiveButton("確定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                       // s2=" ";
                        for (int i = 0; i < selectionSseafood.length; i++) {
                            if (selectionSseafood[i]) {
                                sb.append(itemsSseafood[i] + " ");
                            }
                        }
                            s2 = sb.toString();
                            search食材.setText(s1 + " " + s2 + " " + s3 + " " + s4);

                    }
                });

                chooseSseafood.show() ; }
        });




        mButton = (Button) findViewById(R.id.buttomS辛香料);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // choose 對話框參數
                AlertDialog.Builder chooseSseasoning = new AlertDialog.Builder(Search.this);
                chooseSseasoning.setTitle("辛香料");
                chooseSseasoning.setMultiChoiceItems(itemsSseasoning, selectionSseasoning,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                selectionSseasoning[which] = isChecked;
                            }
                        });

                chooseSseasoning.setPositiveButton("確定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        //s3=" ";
                        for (int i = 0; i < selectionSseasoning.length; i++) {
                            if (selectionSseasoning[i]) {
                                sb.append(itemsSseasoning[i] + " ");
                            }
                        }
                        s3 = sb.toString();
                        search食材.setText(s1 + " " + s2 + " " + s3 + " " + s4);
                    }
                });

                chooseSseasoning.show() ; }
        });


        mButton = (Button) findViewById(R.id.buttomS其他);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // choose 對話框參數
                AlertDialog.Builder chooseSelse = new AlertDialog.Builder(Search.this);
                chooseSelse.setTitle("其他");
                chooseSelse.setMultiChoiceItems(itemsSelse, selectionSelse,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                selectionSelse[which] = isChecked;
                            }
                        });

                chooseSelse.setPositiveButton("確定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        //s4=" ";
                        for (int i = 0; i < selectionSelse.length; i++) {
                            if (selectionSelse[i]) {
                                sb.append(itemsSelse[i] + " ");
                            }
                        }

                        s4 = sb.toString();
                        search食材.setText(s1 + " " + s2 + " " + s3 + " " + s4);
                    }
                });

                chooseSelse.show() ; }
        });





        //隱藏進階搜尋
        buttonS = (Button)findViewById(R.id.buttonSG);
        buttonS.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String SU = "▲" , SD = "▼" ;
                if(time==1)
                {
                    ScrollView layout = (ScrollView) findViewById(R.id.S進階);
                    layout.setVisibility(View.VISIBLE);
                    buttonS.setText(SU);
                    time++ ;
                }
                else if (time==2)
                {
                    ScrollView layout = (ScrollView) findViewById(R.id.S進階);
                    layout.setVisibility(View.GONE);
                    buttonS.setText(SD);
                    time-- ;
                }
            }
        });






        button = (Button)findViewById(R.id.button搜尋);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this , SearchResult.class) ;

                searchkeyword = ETkeyword.getText().toString() ;
                searchdollar_1 = ETdollar_1.getText().toString() ;
                searchdollar_2 = ETdollar_2.getText().toString() ;
                searchtag = search類別.getText().toString() + search食材.getText().toString() ;

//                //new一個Bundle物件，並將要傳遞的資料傳入
                Bundle bundle = new Bundle();
                bundle.putString("tag" , searchtag) ;//傳遞String,要傳其他型態的資料只需修改put後的型態即可 EX.bundle.putDouble("age",age );  傳遞Double
                bundle.putString("keyword" , searchkeyword) ;
                bundle.putString("dollar1" , searchdollar_1) ;
                bundle.putString("dollar2" , searchdollar_2) ;
                //將Bundle物件傳給intent
                intent.putExtras(bundle);


                //切換Activity
                startActivity(intent);
            }
        });


        //清除
        Dbutton = (Button)findViewById(R.id.buttom清除);
        Dbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ETkeyword.setText("");
                ETdollar_1.setText("");
                ETdollar_2.setText("");
                search食材.setText("");
                search類別.setText("");
                for(int i=0;i<selectionSC.length;i++){ selectionSC[i]=false; }
                for(int i=0;i<selectionSmeat.length;i++){ selectionSmeat[i]=false; }
                for(int i=0;i<selectionSseafood.length;i++){ selectionSseafood[i]=false; }
                for(int i=0;i<selectionSseasoning.length;i++){ selectionSseasoning[i]=false; }
                for(int i=0;i<selectionSelse.length;i++){ selectionSelse[i]=false; }
            }
        });


    }
}
