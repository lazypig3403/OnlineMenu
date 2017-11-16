package com.example.yao.onlinemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreTabInfo extends  Fragment
{

    private ApiInterface_Store_Info apiSI;
    private TextView storeName,openTime,address,phone,parking,booking,delivery,type;
    private ImageView img;

    private String storeID = "40";
    private String status = "storeinfoselect";
    int time=2 ;
    Button hide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup pager, Bundle savedInstanceState)
    {
       final View rootView = inflater.inflate(R.layout.activity_store_tab_info, pager, false);

        img = (ImageView) rootView.findViewById(R.id.imageView_photo) ;
        img.setImageResource(R.mipmap.gm);

        apiSI = ApiClient.getClient().create(ApiInterface_Store_Info.class);

        Call<ServerResponse_storeinfo> call = apiSI.store_info_select(storeID,status);
        call.enqueue(new Callback<ServerResponse_storeinfo>()
        {
            @Override
            public void onResponse(Call<ServerResponse_storeinfo> call, Response<ServerResponse_storeinfo> res)
            {
                Result_storeinfo RSsi = res.body().getResult().get(0);

                String storeName_receive = RSsi.getShopName();
                String address_receive = RSsi.getShopAddress();
                String phone_receive = RSsi.getShopPhone();
                String type_receive = RSsi.getShopType();
                String parking_receive = RSsi.getParkingLot();
                String booking_receive = RSsi.getReservation();
                String delivery_receive = RSsi.getDelivery();

                String mon_receive = RSsi.getMon();
                String tue_receive = RSsi.getTue();
                String wed_receive = RSsi.getWed();
                String thu_receive = RSsi.getThu();
                String fri_receive = RSsi.getFri();
                String sat_receive = RSsi.getSat();
                String sun_receive = RSsi.getSun();

                storeName = (TextView) rootView.findViewById(R.id.textView_storename);
                address = (TextView) rootView.findViewById(R.id.textView_address);
                phone = (TextView) rootView.findViewById(R.id.textView_phone);
                parking = (TextView) rootView.findViewById(R.id.textView_parking);
                booking = (TextView) rootView.findViewById(R.id.textView_booking);
                delivery = (TextView) rootView.findViewById(R.id.textView_delivery);

                storeName.setText(storeName_receive);
                address.setText(address_receive);
                phone.setText(phone_receive);

                openTime = (TextView) rootView.findViewById(R.id.select_mon);  openTime.setText(mon_receive);
                openTime = (TextView) rootView.findViewById(R.id.select_tue);  openTime.setText(tue_receive);
                openTime = (TextView) rootView.findViewById(R.id.select_wed);  openTime.setText(wed_receive);
                openTime = (TextView) rootView.findViewById(R.id.select_thu);  openTime.setText(thu_receive);
                openTime = (TextView) rootView.findViewById(R.id.select_fri);  openTime.setText(fri_receive);
                openTime = (TextView) rootView.findViewById(R.id.select_sat);  openTime.setText(sat_receive);
                openTime = (TextView) rootView.findViewById(R.id.select_sun);  openTime.setText(sun_receive);

                parking.setText(parking_receive);
                booking.setText(booking_receive);
                delivery.setText(delivery_receive);

                type = (TextView) rootView.findViewById(R.id.select_storetype);  type.setText(type_receive);

            }

            @Override
            public void onFailure(Call<ServerResponse_storeinfo> callSRki, Throwable t)
            {
                callSRki.cancel();
            }
        });


        FloatingActionButton infoBtn = (FloatingActionButton) rootView.findViewById(R.id.fab_info_update);
        infoBtn.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v){
                                           Intent newAct = new Intent();
                                           newAct.setClass(getActivity(), StoreInfoUpdate.class);
                                           startActivity(newAct);
//                                           getActivity().finish();
                                       }
                                   });

        //隱藏進階搜尋
        hide = (Button) rootView.findViewById(R.id.opentime_hide);
        hide.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String SU = "▲" , SD = "▼" ;
                if(time==1)
                {
                    openTime = (TextView) rootView.findViewById(R.id.select_mon);   openTime.setVisibility(View.VISIBLE);
                    openTime = (TextView) rootView.findViewById(R.id.select_tue);   openTime.setVisibility(View.VISIBLE);
                    openTime = (TextView) rootView.findViewById(R.id.select_wed);   openTime.setVisibility(View.VISIBLE);
                    openTime = (TextView) rootView.findViewById(R.id.select_thu);   openTime.setVisibility(View.VISIBLE);
                    openTime = (TextView) rootView.findViewById(R.id.select_fri);    openTime.setVisibility(View.VISIBLE);
                    openTime = (TextView) rootView.findViewById(R.id.select_sat);   openTime.setVisibility(View.VISIBLE);
                    openTime = (TextView) rootView.findViewById(R.id.select_sun);   openTime.setVisibility(View.VISIBLE);
                    hide.setText(SU);
                    time++ ;
                }
                else if (time==2)
                {
                    openTime = (TextView) rootView.findViewById(R.id.select_tue);   openTime.setVisibility(View.GONE);
                    openTime = (TextView) rootView.findViewById(R.id.select_wed);   openTime.setVisibility(View.GONE);
                    openTime = (TextView) rootView.findViewById(R.id.select_thu);   openTime.setVisibility(View.GONE);
                    openTime = (TextView) rootView.findViewById(R.id.select_fri);    openTime.setVisibility(View.GONE);
                    openTime = (TextView) rootView.findViewById(R.id.select_sat);   openTime.setVisibility(View.GONE);
                    openTime = (TextView) rootView.findViewById(R.id.select_sun);   openTime.setVisibility(View.GONE);
                    hide.setText(SD);
                    time-- ;
                }
            }
        });

        return rootView;
    }
}