package com.example.yao.onlinemenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class StoreInfoUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_info_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
//                intent.setClass(StoreInfoUpdate.this, StoreTab.class);
//                startActivity(intent);
                StoreInfoUpdate.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
