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

public class UpdatePW extends AppCompatActivity {

    private EditText oldP, newP, confirmP;
    private Button updatePW;
    private ApiInterface_keeper ApiI;
    private String op,np,cp;
    private String oID="";
    private String status = "updatepw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pw);
        setTitle("修改密碼");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //取得userID
        GlobalVariable user = (GlobalVariable)getApplicationContext();
        oID = user.getOwnerID();

        oldP = (EditText) findViewById(R.id.oldPwd);
        newP = (EditText) findViewById(R.id.newPwd);
        confirmP = (EditText) findViewById(R.id.confirmPwd);
        updatePW = (Button) findViewById(R.id.pw_update);

        updatePW.setOnClickListener(PWListemer);
    }

    View.OnClickListener PWListemer =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    op = oldP.getText().toString();
                    np = newP.getText().toString();
                    cp = confirmP.getText().toString();

                    if( cp.equals(np) )
                    {
//                        Toast.makeText(UpdatePW.this, "密碼正確", Toast.LENGTH_LONG).show();

                        ApiI = ApiClient.getClient().create(ApiInterface_keeper.class);

                        Call<ServerResponse_keeperinfo> call = ApiI.updatePW(oID,op,np,status);

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
                    else
                    {
                        Toast.makeText(UpdatePW.this, "請重新輸入密碼", Toast.LENGTH_LONG).show();
                    }

                }
            };
    public void showToast_posi(){Toast.makeText(this,"密碼已修改",Toast.LENGTH_LONG).show();}
    public void showToast_fail(){Toast.makeText(this,"修改失敗", Toast.LENGTH_LONG).show();}

    //返回鍵操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(UpdatePW.this, KeeperInfo.class);
                startActivity(intent);
                UpdatePW.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
