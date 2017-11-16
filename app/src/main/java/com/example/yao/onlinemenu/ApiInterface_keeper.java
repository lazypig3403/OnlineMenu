package com.example.yao.onlinemenu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface_keeper
{
    @FormUrlEncoded
    @POST("DB_Connection.php")
    Call<ServerResponse_keeperinfo> UserOperation(
            @Field("username") String username,
            @Field("password") String password,
            @Field("status") String status);

    @FormUrlEncoded
    @POST("KeeperInfo.php")
    Call<ServerResponse_keeperinfo> selectKeeper(
            @Field("userID") String userID,
            @Field("status") String status);

    //0825新增 修改密碼
    @FormUrlEncoded
    @POST("KeeperInfo.php")
    Call<ServerResponse_keeperinfo> updatePW(
            @Field("userID") String ID,
            @Field("opassword") String oPW,
            @Field("npassword") String nPW,
            @Field("status") String status);
    //修改手機號碼
    @FormUrlEncoded
    @POST("KeeperInfo.php")
    Call<ServerResponse_keeperinfo> updateCell(
            @Field("userID") String ID,
            @Field("newcell") String nCell,
            @Field("status") String status);

    //修改電子信箱
    @FormUrlEncoded
    @POST("KeeperInfo.php")
    Call<ServerResponse_keeperinfo> updateMail(
            @Field("userID") String ID,
            @Field("newmail") String nMail,
            @Field("status") String status);
}
