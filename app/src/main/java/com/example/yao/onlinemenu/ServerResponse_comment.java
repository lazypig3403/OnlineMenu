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

public class ServerResponse_comment implements Serializable {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private List<Result_comment> result = null;

    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result_comment> getResult() {
        return result;
    }
    public void setResult(List<Result_comment> result) {
        this.result = result;
    }
}
