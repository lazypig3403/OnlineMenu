package com.example.yao.onlinemenu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by IM17 on 2017/10/2.
 */

public interface ApiInterface_search {
    @FormUrlEncoded
    @POST("searchTag.php")
    Call<ServerResponse_search> search(
            @Field("tags") String tag,
            @Field("keyword") String keyword,
            @Field("leftprice") String dollar1,
            @Field("rightprice") String dollar2);

    @FormUrlEncoded
    @POST("CTR.php")
    Call<ServerResponse> CTR(
            @Field("no") String NO,
            @Field("ctr") String CTR);
}
