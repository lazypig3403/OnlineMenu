package com.example.yao.onlinemenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


public class StoreComment_detail extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_comment_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bd_receive = getIntent().getExtras();
        String name_show = bd_receive.getString("NaMe");
        String time_show = bd_receive.getString("TiMe");
        String content_show = bd_receive.getString("ConTent");

        TextView tv_name = (TextView)findViewById(R.id.name_show);
        TextView tv_time = (TextView)findViewById(R.id.time_show);
        TextView tv_content = (TextView)findViewById(R.id.content_show);

        tv_name.setText(name_show);
        tv_time.setText(time_show);
        tv_content.setText(content_show);
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
//                intent.setClass(StoreComment_detail.this, StoreTab.class);
//                startActivity(intent);
                StoreComment_detail.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
