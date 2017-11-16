package com.example.yao.onlinemenu;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DishDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bd_receive = getIntent().getExtras();

        //接收intent資料
        String name_show = bd_receive.getString("Name");
        String price_show = bd_receive.getString("Price");
        String type_show = bd_receive.getString("Type");
        String ingredient_show = bd_receive.getString("Ingredient");
        int img_show = bd_receive.getInt("Img");

//        int c = bd_receive.getInt("count");
//        Toast.makeText(DishDetail.this, c, Toast.LENGTH_LONG).show();

        //宣告textview
        TextView tv_name = (TextView)findViewById(R.id.select_dishName);
        TextView tv_price = (TextView)findViewById(R.id.select_dishPrice);
        TextView tv_type = (TextView)findViewById(R.id.select_dishType);
        TextView tv_ingredient = (TextView)findViewById(R.id.select_dishIngredient);
        ImageView img = (ImageView)findViewById(R.id.select_dishImg);

        tv_name.setText(name_show);
        tv_price.setText(price_show);
        tv_type.setText(type_show);
        tv_ingredient.setText(ingredient_show);
        img.setImageResource(img_show);
    }


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
//                intent.setClass(DishDetail.this, MenuList.class);
//                startActivity(intent);
                DishDetail.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
