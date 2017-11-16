package com.example.yao.onlinemenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageCustomer extends AppCompatActivity {


    private LinearLayout mGallery ;
    private int[] mImgIds ,mImgIds2 ,mImgIds3 ,mImgIds4 , mImgIds5;
    private String[]   mText, mText2, mText3, mText4 ,  mText5;
    private LayoutInflater mInflater ;
    private ApiInterface_menu apiinterface_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiinterface_menu = ApiClient.getClient().create(ApiInterface_menu.class);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_page_customer);


        mInflater = LayoutInflater.from(this);
        initData();
        initView();
        initView2();
        initView3();
        initView4();
        initView5();


        Button button;
        ImageButton imagebutton ;
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(HomePageCustomer.this, MorePopularity.class);
                startActivity(intent);

            }
        });


        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(HomePageCustomer.this, MoreNew.class);
                startActivity(intent);

            }
        });

        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(HomePageCustomer.this, MoreDiscount.class);
                startActivity(intent);

            }
        });

        button = (Button)findViewById(R.id.button4);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(HomePageCustomer.this, MoreNearby.class);
                startActivity(intent);

            }
        });





    }

        //Toolbar使用
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homepage_toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // About option clicked.
                Intent intent = new Intent();
                intent.setClass(HomePageCustomer.this,Search.class);
                startActivity(intent);
                break;
            case R.id.action_setting:
                // Settings option clicked.
                intent = new Intent();
                intent.setClass(HomePageCustomer.this, UserSetting.class);
                startActivity(intent);
                break;
            case R.id.action_shopping_cart:
                // Settings option clicked.
                intent = new Intent();
                intent.setClass(HomePageCustomer.this, CustomerCart.class);
                startActivity(intent);
                break;

            default: return false;
        }
        return true;
    }




//imagebutton按下去換頁寫法
  /*      imagebutton =(ImageButton)findViewById(R.id.imageButton首頁_搜尋);
        imagebutton.setOnClickListener(ibLis);

    }
    private View.OnClickListener ibLis=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(HomePageCustomer.this, Search.class);
            startActivity(intent);
            HomePageCustomer.this.finish();

        }

    };*/



    private void initData()
    {
        mImgIds = new int[] { R.mipmap.p001,R.mipmap.p002, R.mipmap.p003,
                R.mipmap.p004, R.mipmap.p005};
        mImgIds2 = new int[] { R.mipmap.p006,R.mipmap.p007, R.mipmap.p008,
                R.mipmap.p009, R.mipmap.p010};
        mImgIds3 = new int[] { R.mipmap.p011,R.mipmap.p012, R.mipmap.p013,
                R.mipmap.p014, R.mipmap.p015};
        mImgIds4 = new int[] { R.mipmap.p016,R.mipmap.p017, R.mipmap.p018,
                R.mipmap.p019, R.mipmap.p020};
        mImgIds5 = new int[] { R.mipmap.d001,R.mipmap.d002, R.mipmap.d003,
                R.mipmap.d004, R.mipmap.d005};

        mText = new String[] { "地獄拉麵" ,"豬排蓋飯","泰式酸辣蝦", "莓果乳酪蛋糕","握壽司"} ;
        mText2 = new String[] { "串燒" ,"水果冰","照燒豬排飯", "桶子雞","部隊鍋"} ;
        mText3 = new String[] { "雙倍起司部隊鍋" ,"部隊鍋","青醬義大利麵", "豚骨拉麵","牛肉飯"} ;
        mText4 = new String[] { "脆皮雞腿飯", "咔啦雞腿飯", "燒烤檸檬雞腿飯", "日式燒肉販","薄鹽鯖魚飯"} ;
        mText5 = new String[] { "鴨胸義大利麵" ,"紅醬義大利麵","泰式蝦排", "甜點","鬆餅"} ;

    }

    private void initView()
    {
        mGallery = (LinearLayout) findViewById(R.id.id_gallery人氣);

        for (int i = 0; i < mImgIds.length; i++)
        {

            View view = mInflater.inflate(R.layout.j_picture,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.imageButton);
            img.setImageResource(mImgIds[i]);
            TextView txt = (TextView) view
                    .findViewById(R.id.textView4);
            txt.setText(mText[i]);
            mGallery.addView(view);
        }

    }

    private void initView2()
    {
        mGallery= (LinearLayout) findViewById(R.id.id_gallery最新);


        for (int s = 0; s < mImgIds2.length; s++)
        {

            View view = mInflater.inflate(R.layout.j_picture,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.imageButton);
            img.setImageResource(mImgIds2[s]);
            TextView txt = (TextView) view
                    .findViewById(R.id.textView4);
            txt.setText(mText2[s]);
            mGallery.addView(view);
        }


    }

    private void initView3()
    {
        mGallery= (LinearLayout) findViewById(R.id.id_gallery折扣中);


        for (int s = 0; s < mImgIds3.length; s++)
        {

            View view = mInflater.inflate(R.layout.j_picture,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.imageButton);
            img.setImageResource(mImgIds3[s]);
            TextView txt = (TextView) view
                    .findViewById(R.id.textView4);
            txt.setText(mText3[s]);
            mGallery.addView(view);
        }


    }

    private void initView4()
    {
        mGallery= (LinearLayout) findViewById(R.id.id_gallery附近);


        for (int s = 0; s < mImgIds4.length; s++)
        {

            View view = mInflater.inflate(R.layout.j_picture,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.imageButton);
            img.setImageResource(mImgIds4[s]);
            TextView txt = (TextView) view
                    .findViewById(R.id.textView4);
            txt.setText(mText4[s]);
            mGallery.addView(view);
        }


    }

    private void initView5()
    {
        mGallery= (LinearLayout) findViewById(R.id.id_gallery評分);


        for (int s = 0; s < mImgIds5.length; s++)
        {

            View view = mInflater.inflate(R.layout.j_picture,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.imageButton);
            img.setImageResource(mImgIds5[s]);
            TextView txt = (TextView) view
                    .findViewById(R.id.textView4);
            txt.setText(mText4[s]);
            mGallery.addView(view);
        }


    }


}
