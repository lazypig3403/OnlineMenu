package com.example.yao.onlinemenu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

//要記得在使用的class裡面宣告interface變數

public interface ApiInterface_menu {
    @FormUrlEncoded
    @POST("DB_Connection.php")
    Call<ServerResponse_menu> UserOperation(
            @Field("username") String username,
            @Field("password") String password,
            @Field("status") String status);

    //搭配@Field使用
    //一定要加，不然怎麼死的都不曉得
    @FormUrlEncoded
    @POST("menu.php")
    Call<ServerResponse_menu> selectMenu(
            @Field("status") String Status,
            @Field("storeID") String StoreID);

    //新增菜單
    @FormUrlEncoded
    @POST("menu.php")
    Call<ServerResponse_menu> insertMenu(
            @Field("status") String status,
            @Field("storeID") String StoreID,
            @Field("newMenuName") String nMenu);
}
