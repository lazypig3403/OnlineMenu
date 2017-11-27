package com.example.yao.onlinemenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by John on 2017/6/5.
 * 作為接收回傳"物件"的class
 * SerializedName要與Json的Key同名稱
 * SerializedName需要import"GSON"的Library
 */

public class ServerResponse implements Serializable {


    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    //
    @SerializedName("result")
    @Expose
    private List<Result_customerinfo> result = null;
    //

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
