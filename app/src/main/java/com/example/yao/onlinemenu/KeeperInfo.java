package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KeeperInfo extends AppCompatActivity {

    private ApiInterface_keeper apiK;
    private TextView upPwd, upCell, upMail;

    private String uID = "demo123";
    private String status = "selectkeeper";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keeper_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ClickUpdate();  //變更資訊切換

        apiK = ApiClient.getClient().create(ApiInterface_keeper.class);

        Call<ServerResponse_keeperinfo> call = apiK.selectKeeper(uID,status);
        call.enqueue(new Callback<ServerResponse_keeperinfo>()
        {
            @Override
            public void onResponse(Call<ServerResponse_keeperinfo> callSRk, Response<ServerResponse_keeperinfo> resSRk)
            {
                Result_keeperinfo RSk = resSRk.body().getResult();

                String id_receive = RSk.getOID();
                String pw_receive = RSk.getOPwd();
                String name_receive = RSk.getOName();
                String sex_receive = RSk.getOSex();
                String cell_receive = RSk.getOCellPhone();
                String mail_receive = RSk.getOEmail();

                TextView kID = (TextView) findViewById(R.id.KeeperID);
                TextView kPW = (TextView) findViewById(R.id.KeeperPW);
                TextView kName = (TextView) findViewById(R.id.KeeperName);
                TextView kSex = (TextView) findViewById(R.id.KeeperSex);
                TextView kCell = (TextView) findViewById(R.id.KeeperCell);
                TextView kMail = (TextView) findViewById(R.id.KeeperMail);

                kID.setText(id_receive);
                kPW.setText(pw_receive);
                kName.setText(name_receive);
                kSex.setText(sex_receive);
                kCell.setText(cell_receive);
                kMail.setText(mail_receive);
            }

            @Override
            public void onFailure(Call<ServerResponse_keeperinfo> callSRki, Throwable t)
            {
                callSRki.cancel();
            }
        });
    }

    private void ClickUpdate() {
        upPwd = (TextView) findViewById(R.id.updatePW);
        upCell = (TextView) findViewById(R.id.updateCell);
        upMail = (TextView) findViewById(R.id.updateMail);

        View.OnClickListener UpdateListemer =
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();

                        if (id == R.id.updatePW) {
                            Intent intent = new Intent();
                            intent.setClass(KeeperInfo.this, UpdatePW.class);
                            startActivity(intent);
                            KeeperInfo.this.finish();
                        }
                        if (id == R.id.updateCell) {
                            Intent intent = new Intent();
                            intent.setClass(KeeperInfo.this, UpdateCell.class);
                            startActivity(intent);
                            KeeperInfo.this.finish();
                        }
                        if (id == R.id.updateMail) {
                            Intent intent = new Intent();
                            intent.setClass(KeeperInfo.this, UpdateMail.class);
                            startActivity(intent);
                            KeeperInfo.this.finish();
                        }
                    }
                };

        upPwd.setOnClickListener(UpdateListemer);
        upCell.setOnClickListener(UpdateListemer);
        upMail.setOnClickListener(UpdateListemer);
    }

    //返回鍵操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(KeeperInfo.this, Store_Home.class);
                startActivity(intent);
                KeeperInfo.this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
