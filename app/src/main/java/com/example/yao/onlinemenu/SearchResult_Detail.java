package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SearchResult_Detail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bd_receive = getIntent().getExtras();

        //接收intent資料
        String dish_no = bd_receive.getString("dishNO");
        String name_show = bd_receive.getString("searchName");
        String price_show = bd_receive.getString("searchPrice");
        String type_show = bd_receive.getString("searchType");
        String ingredient_show = bd_receive.getString("searchIngredient");
        int img_show = bd_receive.getInt("searchImg");

//        int c = bd_receive.getInt("count");
//        Toast.makeText(DishDetail.this, c, Toast.LENGTH_LONG).show();

        //宣告textview
        TextView tv_name = (TextView)findViewById(R.id.search_detail_dishName);
        TextView tv_price = (TextView)findViewById(R.id.search_detail_dishPrice);
        TextView tv_type = (TextView)findViewById(R.id.search_detail_dishType);
        TextView tv_ingredient = (TextView)findViewById(R.id.search_detail_dishIngredient);
        ImageView img = (ImageView)findViewById(R.id.search_detail_dishImg);

        tv_name.setText(name_show);
        tv_price.setText(price_show);
        tv_type.setText(type_show);
        tv_ingredient.setText(ingredient_show);
        img.setImageResource(img_show);
//        Toast.makeText(SearchResult_Detail.this, dish_no, Toast.LENGTH_SHORT).show();
        Button bt;
        bt = (Button)findViewById(R.id.bt_search_detail_goOrder);
        bt.setOnClickListener(new mListener());
        bt = (Button)findViewById(R.id.bt_search_detail_goMenu);
        bt.setOnClickListener(new mListener());
    }


    private class mListener implements View.OnClickListener{
        @Override
        public void onClick(View v){

            switch (v.getId()) {
                case R.id.bt_search_detail_goOrder:
                    Intent intent = new Intent();
                    intent.setClass(SearchResult_Detail.this, CustomerCart.class);
                    startActivity(intent);
                    SearchResult_Detail.this.finish();
                    break;

                case R.id.bt_search_detail_goMenu:
                    Toast.makeText(SearchResult_Detail.this, "show", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //返回鍵操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                SearchResult_Detail.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
