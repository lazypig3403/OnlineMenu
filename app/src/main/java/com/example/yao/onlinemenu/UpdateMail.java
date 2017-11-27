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

import static com.example.yao.onlinemenu.R.id.newEmail;

public class UpdateMail extends AppCompatActivity {

    private EditText newMail;
    private Button updateMail;
    private ApiInterface_keeper ApiI;
    private String nMail;
    private String oID="";
    private String status = "updatemail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mail);
        setTitle("修改信箱");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //取得userID
        GlobalVariable user = (GlobalVariable)getApplicationContext();
        oID = user.getOwnerID();

        newMail = (EditText) findViewById(newEmail);
        updateMail = (Button) findViewById(R.id.mail_update);

        updateMail.setOnClickListener(MailListemer);
    }

    View.OnClickListener MailListemer =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nMail = newMail.getText().toString();

                    ApiI = ApiClient.getClient().create(ApiInterface_keeper.class);

                    Call<ServerResponse_keeperinfo> call = ApiI.updateMail(oID,nMail,status);

                    call.enqueue(new Callback<ServerResponse_keeperinfo>() {
                        @Override
                        public void onResponse(Call<ServerResponse_keeperinfo> call, Response<ServerResponse_keeperinfo> SRk)
                        {
                            if (SRk.body().getSuccess()==1)
                            {showToast_posi();}
                            else
                            {showToast_fail();}
                        }
                        @Override
                        public void onFailure(Call<ServerResponse_keeperinfo> call, Throwable t)
                        {call.cancel();}
                    });
                }
            };
    public void showToast_posi(){Toast.makeText(this,"信箱地址已修改",Toast.LENGTH_LONG).show();}
    public void showToast_fail(){Toast.makeText(this,"修改失敗", Toast.LENGTH_LONG).show();}

    //返回鍵操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(UpdateMail.this, KeeperInfo.class);
                startActivity(intent);
                UpdateMail.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
