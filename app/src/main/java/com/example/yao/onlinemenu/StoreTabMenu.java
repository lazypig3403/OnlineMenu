package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jane on 2017/4/26.
 */

public class StoreTabMenu extends  Fragment{

    // 加入物件listview
    private View rootView;
    private ListView listV;
    private ArrayAdapter<String> aAD;
    private List<String> menu_type;

    private String status = "selectmenu";
    private String storeID = "40";

    private ApiInterface_menu ApiI_menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup pager, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_store_tab_menu, pager, false);
        listV = (ListView) rootView.findViewById(R.id.foodtypelist);


        ApiI_menu = ApiClient.getClient().create(ApiInterface_menu.class);

        Call<ServerResponse_menu> call = ApiI_menu.selectMenu(status, storeID);
        call.enqueue(new Callback<ServerResponse_menu>()
        {
            @Override
            public void onResponse(Call<ServerResponse_menu> callSRm, Response<ServerResponse_menu> resSRm)
            {
                menu_type = new ArrayList<>();

                //取得菜單名稱
                final String[] arrMenuName = new String[resSRm.body().getResult().size()];
                //取得菜單ID
                final String[] arrMenuID = new String[resSRm.body().getResult().size()];

                for (int i = 0; i < resSRm.body().getResult().size(); i++) {
                    Result_menu RSm = resSRm.body().getResult().get(i);
                    arrMenuName[i] = RSm.getMName();
                    arrMenuID[i] = RSm.getMNO();
                }

                for(int j=0;j<arrMenuName.length;j++)
                {
                    menu_type.add(arrMenuName[j]);

                    listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Toast.makeText(getActivity(), "選擇:"+menu_type.get(position), Toast.LENGTH_LONG).show();

                            Intent intent = new Intent();
                            intent.setClass(getActivity(), MenuList.class);

                            Bundle to_Menulist = new Bundle();
                            to_Menulist.putString("Menu_Name",arrMenuName[position]);
                            to_Menulist.putString("Menu_No",arrMenuID[position]);
                            intent.putExtras(to_Menulist);

                            startActivity(intent);
                        }
                    });
                }

                aAD = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, menu_type);
                listV.setAdapter(aAD);

            }
            @Override
            public void onFailure(Call<ServerResponse_menu> callSRm, Throwable t)
            {
                callSRm.cancel();
            }
        });

        //宣告浮動按鈕，按向後導向"新增菜色"頁面
//        FloatingActionButton commBtn = (FloatingActionButton) rootView.findViewById(R.id.fab_add_menu);
//        commBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View vvv) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), DishAdd.class);
//                startActivity(intent);
//            }
//        });
        return rootView;
    }
}