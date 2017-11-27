package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuAdd extends AppCompatActivity {

    EditText et_newMenu;
    String nMenu;
    Button saveMenu;

    private String sID="40";
    private String status = "insertmenu";
    private ApiInterface_menu ApiI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_newMenu = (EditText) findViewById(R.id.edit_newMenu);

        saveMenu = (Button) findViewById(R.id.bt_newMenu_save);
        saveMenu.setOnClickListener(MenuListener);
    }

    View.OnClickListener MenuListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nMenu = et_newMenu.getText().toString();

                    ApiI = ApiClient.getClient().create(ApiInterface_menu.class);

                    Call<ServerResponse_menu> call = ApiI.insertMenu(status,sID,nMenu);

                    call.enqueue(new Callback<ServerResponse_menu>() {
                        @Override
                        public void onResponse(Call<ServerResponse_menu> call, Response<ServerResponse_menu> SRk)
                        {
                            if (SRk.body().getSuccess()==1) { showToast_posi(); }
                            else { showToast_fail(); }
                        }
                        @Override
                        public void onFailure(Call<ServerResponse_menu> call, Throwable t)
                        {call.cancel();}
                    });

                    Intent intent = new Intent();
                    intent.setClass(MenuAdd.this, StoreTab.class );
                    startActivity(intent);
                    MenuAdd.this.finish();
                }
            };
    public void showToast_posi() { Toast.makeText(this,"菜單新增成功",Toast.LENGTH_LONG).show(); }
    public void showToast_fail() { Toast.makeText(this,"新增失敗", Toast.LENGTH_LONG).show(); }


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
                MenuAdd.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
