package com.example.yao.onlinemenu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by IM17 on 2017/7/15.
 */

public interface APIInterface {
    @FormUrlEncoded
    @POST("loginTest.php")
    Call<ServerResponse> loginTest(
            @Field("username") String username,
            @Field("password") String password,
            @Field("id") String id);

    //搭配@Field使用
    //一定要加，不然怎麼死的都不曉得
    @FormUrlEncoded
    @POST("register.php")
    Call<ServerResponse> register(
            @Field("un") String un,
            @Field("pw") String pw,
            @Field("rn") String rn,
            @Field("ema") String ema,
            @Field("cpn") String cpn,
            @Field("id") String id);

    @FormUrlEncoded
    @POST("vsupload.php")
    Call<ServerResponse> vsupload(
            @Field("un") String un,
            @Field("id") String id,
            @Field("vs") String vs,
            @Field("status") String status);

    @FormUrlEncoded
    @POST("infoselect.php")
    Call<ServerResponse> infoselect(
            @Field("un") String un);
}
