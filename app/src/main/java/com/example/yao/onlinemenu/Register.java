package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private ToggleButton toggleButton1, toggleButton2;
    private Button button1, button2;
    private EditText editText, editText1, editText2, editText3, editText5, editText6;
    private String id="";
    private APIInterface apiinterface;

    private class toggleButton_OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            if (isChecked) //當按鈕狀態為未選取時
            {

            } else //當按鈕狀態為選取時
            {

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        apiinterface = ApiClient.getClient().create(APIInterface.class);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButton1.setChecked(false);
        toggleButton2.setChecked(false);
        toggleButton1.setOnCheckedChangeListener(new toggleButton_OnCheckedChangeListener());
        toggleButton2.setOnCheckedChangeListener(new toggleButton_OnCheckedChangeListener());
        editText = (EditText)findViewById(R.id.editText);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                editText.setText("");editText1.setText("");editText2.setText("");editText3.setText("");editText5.setText("");editText6.setText("");
                toggleButton1.setChecked(false);toggleButton2.setChecked(false);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String un = editText.getText().toString();
                String pw = editText1.getText().toString();
                String pwc = editText2.getText().toString();
                String rn = editText3.getText().toString();
                String cpn = editText5.getText().toString();
                String ema = editText6.getText().toString();
                if(toggleButton1.isChecked()==false && toggleButton2.isChecked()==false)
                {
                Toast.makeText(Register.this,"請選擇身份(可同時註冊)" , Toast.LENGTH_SHORT).show();
                }
                else if(!pw.equals(pwc))
                {
                    Toast.makeText(Register.this, "確認密碼不一致", Toast.LENGTH_SHORT).show();
                }
                else if (un.equals("") || pw.equals("") || rn.equals("") || cpn.equals("") || ema.equals(""))
                {
                    Toast.makeText(Register.this, "所有項目皆必填", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(toggleButton1.isChecked()==true && toggleButton2.isChecked()==true){id="0";} //同時註冊雙身分
                    else if(toggleButton1.isChecked()==true){id="1";} //消費者身分
                    else{id="2";} //店家身分

                    Call<ServerResponse> call = apiinterface.register(un,pw,rn, ema, cpn, id);
                    call.enqueue(new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response)
                        {
                            if(response.body().getSuccess().equals("1"))
                            {
                                Toast.makeText(getApplicationContext(), "註冊成功", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent();
                                intent.setClass(Register.this  , Login.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "失敗", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t)
                        {
                            call.cancel();
                        }
                    });
                }
            }
        });
    }
}
