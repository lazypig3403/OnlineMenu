package com.example.yao.onlinemenu;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreCommentUpdate extends AppCompatActivity {
    Button bt_send;
    TextView ad_send;
    TextView abc;
    EditText def;
    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    private ApiInterface_comment ApiI;
    private String UserName,SendTime,CommContent,Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_comment_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ad_send = (TextView)findViewById(R.id.textView_state);
        bt_send = (Button)findViewById(R.id.button_send_comment);
        abc = (TextView) findViewById(R.id.comment_update_id);
        def = (EditText) findViewById(R.id.comment_update_content);

        bt_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                UserName=abc.getText().toString();
                CommContent=def.getText().toString();
                SendTime=sDateFormat.format(new java.util.Date());
                Toast.makeText(StoreCommentUpdate.this,SendTime,Toast.LENGTH_LONG).show();

                Status="update";

                ApiI = ApiClient.getClient().create(ApiInterface_comment.class);

                Call<ServerResponse_comment> call = ApiI.commentUpdate(UserName,SendTime,CommContent,Status);

                call.enqueue(new Callback<ServerResponse_comment>() {
                    @Override
                    public void onResponse(Call<ServerResponse_comment> call, Response<ServerResponse_comment> SRc)
                    {
                        if (SRc.body().getSuccess().equals("1"))
                        {
                            showToast_posi();
                            ad_send.setText("評論已送出");
                        }
                        else {
                            showToast_fail();
                            ad_send.setText("傳送失敗");
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse_comment> call, Throwable t)
                    {call.cancel();}
                });



                //Send();
            }
        });
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
//                intent.setClass(StoreCommentUpdate.this, StoreTab.class);
//                startActivity(intent);
                StoreCommentUpdate.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Send()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("是否送出評論?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        showToast_nega();
                        ad_send.setText("取消送出");
                    }
                });
        AlertDialog about_dialog = builder.create();
        about_dialog.show();
    }

    public void showToast_posi(){
        Toast.makeText(this,"評論已送出",Toast.LENGTH_LONG).show();
    }
    public void showToast_fail(){
        Toast.makeText(this, "傳送失敗", Toast.LENGTH_LONG).show();
    }
    public void showToast_nega(){
        Toast.makeText(this,"取消送出",Toast.LENGTH_LONG).show();
    }
}