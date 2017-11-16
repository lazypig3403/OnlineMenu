package com.example.yao.onlinemenu;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class UserSetting extends AppCompatActivity {
    private Switch switch1, switch2, switch3;
    private Button buttonse1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        buttonse1 = (Button)findViewById(R.id.buttonse1);
        buttonse1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword();
            }
        });
        switch1 = (Switch)findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    switch1.setText("開");

                } else {
                    switch1.setText("關");

                }
            }
        });
        switch2 = (Switch)findViewById(R.id.switch2);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    switch2.setText("開");

                } else {
                    switch2.setText("關");

                }
            }
        });
        switch3 = (Switch)findViewById(R.id.switch3);
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    switch3.setText("開");

                } else {
                    switch3.setText("關");

                }
            }
        });


    }

    private void ChangePassword() {
        final View item = LayoutInflater.from(UserSetting.this).inflate(R.layout.change_password, null);
        new AlertDialog.Builder(UserSetting.this)
                .setView(item)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText1 = (EditText) item.findViewById(R.id.edittext1);
                        EditText editText2 = (EditText) item.findViewById(R.id.edittext2);
                        EditText editText3 = (EditText) item.findViewById(R.id.edittext3);
                        TextView pw = (TextView)findViewById(R.id.textViewse5);
                        if(!(editText1.getText().toString()).equals(pw.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(), "舊密碼錯誤", Toast.LENGTH_SHORT).show();
                        }
                        else if((editText2.getText().toString()).equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "請輸入新密碼", Toast.LENGTH_SHORT).show();
                        }
                        else if(!(editText3.getText().toString()).equals(editText2.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(), "新密碼不一致", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            pw.setText(editText2.getText().toString());
                            Toast.makeText(getApplicationContext(), "更改成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    public void show(View v) {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);

        //依選取項目顯示不同訊息
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radioButtonse0:
                Toast.makeText(UserSetting.this, "關閉通知", Toast.LENGTH_SHORT).show(); //顯示結果
                break;
            case R.id.radioButtonse1:
                Toast.makeText(UserSetting.this, "震動", Toast.LENGTH_SHORT).show(); //顯示結果
                break;
            case R.id.radioButtonse2:
                Toast.makeText(UserSetting.this, "提示燈", Toast.LENGTH_SHORT).show(); //顯示結果
                break;
            case R.id.radioButtonse3:
                Toast.makeText(UserSetting.this, "震動+提示燈", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
