package com.example.yao.onlinemenu;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Feedback extends AppCompatActivity {
    private EditText editText, editText1, editText2;
    private Button button, button1, button2;
    private ImageButton imageButton;
    private ImageView imv0, imv1, imv2, imv3, imv4, imv5;
    private List<String> fbt, choose;
    private int no = 0, num = 0;
    private DisplayMetrics mPhone;
    private final static int CAMERA = 66 ;
    private final static int PHOTO = 99 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        List();
        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);
        imv0 = (ImageView)findViewById(R.id.imageView);
        imv1 = (ImageView)findViewById(R.id.imageView1);
        imv2 = (ImageView)findViewById(R.id.imageView2);
        imv3 = (ImageView)findViewById(R.id.imageView3);
        imv4 = (ImageView)findViewById(R.id.imageView4);
        imv5 = (ImageView)findViewById(R.id.imageView5);
        editText = (EditText)findViewById(R.id.editText);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listDialog();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText.setText("");
                editText1.setText("");
                editText2.setText("");
                button.setText("請選擇類型");
                no = 0;
                if(num>0)
                {
                    imv0.setImageBitmap(null);
                    imv1.setImageBitmap(null);
                    imv2.setImageBitmap(null);
                    imv3.setImageBitmap(null);
                    imv4.setImageBitmap(null);
                    imv5.setImageBitmap(null);
                }
                num = 0;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String e = editText.getText().toString();
                String t = editText1.getText().toString();
                String c = editText2.getText().toString();
                if (e.equals("")) {
                    Toast.makeText(Feedback.this, "請填寫信箱", Toast.LENGTH_SHORT).show();
                } else if (t.equals("")) {
                    Toast.makeText(Feedback.this, "請填寫主旨", Toast.LENGTH_SHORT).show();
                } else if (no == 0) {
                    Toast.makeText(Feedback.this, "請選擇類型", Toast.LENGTH_SHORT).show();
                } else if (c.equals("")) {
                    Toast.makeText(Feedback.this, "請填寫詳述", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SendMail();
                }

            }
        });
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                listDialog1();
            }
        });

        }

    private void List() {
        fbt = new ArrayList<>();
        fbt.add(getString(R.string.fbt1));
        fbt.add(getString(R.string.fbt2));
        fbt.add(getString(R.string.fbt3));
        fbt.add(getString(R.string.fbt4));
        choose = new ArrayList<>();
        choose.add(getString(R.string.choose1));
        choose.add(getString(R.string.choose2));
    }

    private void listDialog() {
        new AlertDialog.Builder(Feedback.this)
                .setItems(fbt.toArray(new String[fbt.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = fbt.get(which);
                        button.setText(name);
                        no = Integer.parseInt(name.substring(0, 1));
                    }
                })
                .show();
    }

    private void listDialog1()
    {
        new AlertDialog.Builder(Feedback.this)
                .setItems(choose.toArray(new String[choose.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = choose.get(which);
                        if(name.equals("相機"))
                        {
                            ContentValues value = new ContentValues();
                            value.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                            Uri uri= getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    value);
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri.getPath());
                            startActivityForResult(intent, CAMERA);
                        }
                        else if(name.equals("相簿"))
                        {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, PHOTO);
                        }
                    }
                })
                .show();
    }

    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
        if ((requestCode == CAMERA || requestCode == PHOTO ) && data != null)
        {
            //取得照片路徑uri
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();

            try
            {
                //讀取照片，型態為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                //判斷照片為橫向或者為直向，並進入ScalePic判斷圖片是否要進行縮放
                if(bitmap.getWidth()>bitmap.getHeight())ScalePic(bitmap,
                        mPhone.heightPixels);
                else ScalePic(bitmap,mPhone.widthPixels);
            }
            catch (FileNotFoundException e)
            {
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ScalePic(Bitmap bitmap,int phone)
    {
        //縮放比例預設為1
        float mScale = 1 ;

        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if(bitmap.getWidth() > phone )
        {
            //判斷縮放比例
            mScale = (float)phone/(float)bitmap.getWidth();

            Matrix mMat = new Matrix() ;
            mMat.setScale(mScale, mScale);

            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMat, false);
        //    imv0.setImageBitmap(mScaleBitmap);
            if (num == 0) {
                imv0.setImageBitmap(mScaleBitmap);
                num++;
            } else if (num == 1) {
                imv1.setImageBitmap(mScaleBitmap);
                num++;
            } else if (num == 2) {
                imv2.setImageBitmap(mScaleBitmap);
                num++;
            } else if (num == 3) {
                imv3.setImageBitmap(mScaleBitmap);
                num++;
            } else if (num == 4) {
                imv4.setImageBitmap(mScaleBitmap);
                num++;
            } else {
                imv5.setImageBitmap(mScaleBitmap);
            }

        }
        else
        {
           // imv0.setImageBitmap(bitmap);
            if (num == 0) {
                imv0.setImageBitmap(bitmap);
                num++;
            } else if (num == 1) {
                imv1.setImageBitmap(bitmap);
                num++;
            } else if (num == 2) {
                imv2.setImageBitmap(bitmap);
                num++;
            } else if (num == 3) {
                imv3.setImageBitmap(bitmap);
                num++;
            } else if (num == 4) {
                imv4.setImageBitmap(bitmap);
                num++;
            } else {
                imv5.setImageBitmap(bitmap);
            }
        }
    }

    private void SendMail() {
        new AlertDialog.Builder(Feedback.this)
                .setMessage("確定送出?")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "已送出", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "未送出", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}


