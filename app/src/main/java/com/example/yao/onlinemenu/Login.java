package com.example.yao.onlinemenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private ToggleButton toggleButton1, toggleButton2;
    private Button button, button1, button2, button3, button4;
    private EditText editText1, editText2;
    private String severUrl="http://134.208.97.162/Login.php";
    private String un,pw,id;
 //   private String login = "0";
    private APIInterface apiinterface;
    private class toggleButton_OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            if (isChecked) //當按鈕狀態為選取時
            {

            } else //當按鈕狀態為未選取時
            {

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiinterface = ApiClient.getClient().create(APIInterface.class);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButton1.setChecked(false);
        toggleButton2.setChecked(false);
        toggleButton1.setOnCheckedChangeListener(new Login.toggleButton_OnCheckedChangeListener());
        toggleButton2.setOnCheckedChangeListener(new Login.toggleButton_OnCheckedChangeListener());
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);
        button2 =(Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        toggleButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toggleButton2.setChecked(false); //消費者身分登入
            }
        });
        toggleButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toggleButton1.setChecked(false); //店家身分登入
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText("");
                editText2.setText("");
                toggleButton1.setChecked(false);
                toggleButton2.setChecked(false);
                id="0";
            }
        }); //清除
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                un = editText1.getText().toString(); //帳號
                pw = editText2.getText().toString(); //密碼

                if(!toggleButton1.isChecked() && !toggleButton2.isChecked())
                {
                    Toast.makeText(Login.this, "請選擇身份", Toast.LENGTH_SHORT).show();
                }
                else if(un.equals(""))
                {
                    Toast.makeText(Login.this, "請輸入帳號", Toast.LENGTH_SHORT).show();
                }
                else if(pw.equals(""))
                {
                    Toast.makeText(Login.this, "請輸入密碼", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (toggleButton1.isChecked()) { id = "0";} //消費者
                    if (toggleButton2.isChecked()) { id = "1";} //店家

                    Call<ServerResponse> call = apiinterface.loginTest(un,pw,id);
                    call.enqueue(new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response)
                        {
                            if(response.body().getSuccess()==1)
                            {
                                Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_LONG).show();
                              //  login = "1";
                                IDLogin();
//                                InfoSelect();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "登入失敗", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t)
                        {
                            call.cancel();
                        }

                        public void IDLogin()
                        {
                            if(id.equals("0")) {
                                //儲存userID
                                GlobalVariable user = (GlobalVariable) getApplicationContext();
                                user.setCustomerID(un);

                                Intent customer = new Intent();
                                customer.setClass(Login.this, HomePageCustomer.class);
                                startActivity(customer);
                                Login.this.finish();
                            }
                            else
                            {
                                //儲存userID
                                GlobalVariable user = (GlobalVariable) getApplicationContext();
                                user.setOwnerID(un);

                                Intent store = new Intent();
                                store.setClass(Login.this, Store_Home.class);
                                startActivity(store);
                                Login.this.finish();
                            }
                        }

//                        public void InfoSelect()
//                        {
//                            Call<ServerResponse> call = apiinterface.infoselect(un);
//                            call.enqueue(new Callback<ServerResponse>() {
//                                @Override
//                                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response)
//                                {
//                                    if(response.body().getSuccess()==1)
//                                    {
//
//                                    }
//                                    else
//                                    {
//
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ServerResponse> call, Throwable t)
//                                {
//                                    call.cancel();
//                                }
//                            });
//                        }

                    });

                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this  , Feedback.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this  , Register.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this  , HomePageCustomer.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(Login.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束應用程式嗎?")
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    finish();
                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
        }
        return true;
    }

}
