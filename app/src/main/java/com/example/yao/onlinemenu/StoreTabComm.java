package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreTabComm extends Fragment {
    private View v;
    private ListView lv;
    private ListAdapter la;
    private ArrayList<HashMap<String, String>> data = new ArrayList<>();

    private static final String TAG = StoreTabComm.class.getSimpleName();

    private String status = "selectall";

    private ApiInterface_comment ApiI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup pager, Bundle savedInstanceState)
    {
        if (v == null) {
            v = inflater.inflate(R.layout.activity_store_tab_comm, null);

            lv = (ListView) v.findViewById(R.id.comment_list);

            ApiI = ApiClient.getClient().create(ApiInterface_comment.class);

            Call<ServerResponse_comment> call = ApiI.selectall(status);
            call.enqueue(new Callback<ServerResponse_comment>()
            {
                @Override
                public void onResponse(Call<ServerResponse_comment> callSRc, Response<ServerResponse_comment> resSRc)
                {
                    if (resSRc.body().getSuccess().equals("1")) {
                        Log.i(TAG, "Success");

                        final String[] arrName = new String[resSRc.body().getResult().size()];
                        final String[] arrTime = new String[resSRc.body().getResult().size()];
                        final String[] arrContent = new String[resSRc.body().getResult().size()];

                        //取出資料
                        for (int i = 0; i < resSRc.body().getResult().size(); i++) {
                            Result_comment RS = resSRc.body().getResult().get(i);
                            arrName[i] = RS.getCID();
                            arrTime[i] = RS.getCommtTime();
                            arrContent[i] = RS.getCommtText();
                        }

                        for (int i = 0; i < arrName.length; i++) {
                            HashMap<String, String> hm = new HashMap<String, String>();
                            //依序將資料存入hm
                            hm.put("Name_Show", arrName[i]);
                            hm.put("Time_Show", arrTime[i]);
                            hm.put("Content_Show", arrContent[i]);
                            //將hm存入data
                            data.add(hm);

                            lv.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //設定按下動作，產生toast訊息
                                    Toast.makeText(getActivity(), "你按下了第" + (position + 1) + "筆評論", Toast.LENGTH_LONG).show();
                                    //按下顯示單筆評論詳細
                                    Intent newAct = new Intent();
                                    newAct.setClass(getActivity(), StoreComment_detail.class);

                                    Bundle bd_send = new Bundle();
                                    bd_send.putString("NaMe", arrName[position]);
                                    bd_send.putString("TiMe", arrTime[position]);
                                    bd_send.putString("ConTent", arrContent[position]);
                                    newAct.putExtras(bd_send);

                                    startActivity(newAct);
//                                    getActivity().finish();
                                }
                            });
                        }

                        //宣告la為SimpleAdapter，將data顯示到comment_listi_tem中指定的textview上
                        la = new SimpleAdapter(getActivity(), data, R.layout.comment_list_item,
                                new String[]{"Name_Show", "Time_Show", "Content_Show"},
                                new int[]{R.id.textView_name, R.id.textView_time, R.id.textView_content});
                        //將adapter la設定給lv
                        lv.setAdapter(la);

                    } else {
                        Log.i(TAG, "Failed");
                        Toast.makeText(getActivity(), "失敗了", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse_comment> callSRc, Throwable t)
                {
                    Log.e(TAG, t.toString());
                    callSRc.cancel();
                }
            });

            //宣告浮動按鈕，按向後導向"評論編輯"頁面
//            FloatingActionButton commBtn = (FloatingActionButton) v.findViewById(R.id.fab_comm_update);
//            commBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View vvv) {
//                    Intent newAct = new Intent();
//                    newAct.setClass(getActivity(), StoreCommentUpdate.class);
//                    startActivity(newAct);
//                }
//            });
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }
}
