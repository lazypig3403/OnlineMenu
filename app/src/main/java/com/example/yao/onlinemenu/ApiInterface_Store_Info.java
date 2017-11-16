package com.example.yao.onlinemenu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface_Store_Info
{
    @FormUrlEncoded
    @POST("DB_Connection.php")
    Call<ServerResponse_storeinfo> UserOperation(
            @Field("username") String username,
            @Field("password") String password,
            @Field("status") String status);

    //搭配@Field使用
    //一定要加，不然怎麼死的都不曉得
    @FormUrlEncoded
    @POST("store_info_select.php")
    Call<ServerResponse_storeinfo> store_info_select(
            @Field("storeID") String StoreID,
            @Field("status") String Status);

    @FormUrlEncoded
    @POST("store_info_select.php")
    Call<ServerResponse_storeinfo> store_select(
            @Field("status") String Status,
            @Field("ownerID") String OwnerID);

    @FormUrlEncoded
    @POST("store_info_select.php")
    Call<ServerResponse_storeinfo> store_create(
            @Field("status") String Status,
            @Field("storeName") String StoreName,
            @Field("storeAddress") String Address,
            @Field("storePhone") String Phone,
            @Field("storeType") String Type,
            @Field("parking") String Parking,
            @Field("booking") String Booking,
            @Field("delivering") String Delivery,
            @Field("monday") String OpenTime_0,
            @Field("tuesday") String OpenTime_1,
            @Field("wednesday") String OpenTime_2,
            @Field("thursday") String OpenTime_3,
            @Field("friday") String OpenTime_4,
            @Field("saturday") String OpenTime_5,
            @Field("sunday") String OpenTime_6);

    @FormUrlEncoded
    @POST("store_info_select.php")
    Call<ServerResponse_storeinfo> store_info_update(
            @Field("storeID") String StoreID,
            @Field("storeName") String StoreName,
            @Field("storeAddress") String Address,
            @Field("storePhone") String Phone,
            @Field("storeType") String Type,
            @Field("parking") String Parking,
            @Field("booking") String Booking,
            @Field("delivering") String Delivery,
            @Field("openTime") String[] OpenTime,
            @Field("status") String Status);
}


