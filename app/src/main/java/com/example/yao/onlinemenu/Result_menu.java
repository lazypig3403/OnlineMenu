package com.example.yao.onlinemenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_menu {

    @SerializedName("MNO")
    @Expose
    private String mNO;
    @SerializedName("MName")
    @Expose
    private String mName;
    @SerializedName("SINO")
    @Expose
    private String sINO;
    @SerializedName("TNO")
    @Expose
    private String tNO;

    public String getMNO() {
        return mNO;
    }

    public void setMNO(String mNO) {
        this.mNO = mNO;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getSINO() {
        return sINO;
    }

    public void setSINO(String sINO) {
        this.sINO = sINO;
    }

    public String getTNO() {
        return tNO;
    }

    public void setTNO(String tNO) {
        this.tNO = tNO;
    }

}