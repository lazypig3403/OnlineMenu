package com.example.yao.onlinemenu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

//要記得在使用的class裡面宣告interface變數

public interface ApiInterface_comment {
    @FormUrlEncoded
    @POST("DB_Connection.php")
    Call<ServerResponse_comment> UserOperation(
            @Field("username") String username,
            @Field("password") String password,
            @Field("status") String status);

    //搭配@Field使用
    //一定要加，不然怎麼死的都不曉得
    @FormUrlEncoded
    @POST("comment_select.php")
    Call<ServerResponse_comment> commentUpdate(
            @Field("userName") String AAA,
            @Field("sendTime") String BBB,
            @Field("commContent") String CCC,
            @Field("status") String DDD);

    @FormUrlEncoded
    @POST("comment_select.php")
    Call<ServerResponse_comment> selectall(
            @Field("status") String status);
}
