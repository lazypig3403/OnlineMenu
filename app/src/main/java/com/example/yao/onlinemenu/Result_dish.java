package com.example.yao.onlinemenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2017/9/6.
 */

public class Result_dish {

    @SerializedName("DNO")
    @Expose
    private String dNO;
    @SerializedName("DIngredient")
    @Expose
    private String dIngredient;
    @SerializedName("MNO")
    @Expose
    private String mNO;
    @SerializedName("DName")
    @Expose
    private String dName;
    @SerializedName("DPrice")
    @Expose
    private String dPrice;
    @SerializedName("DType")
    @Expose
    private String dType;
    @SerializedName("DStar")
    @Expose
    private String dStar;

    public String getDNO() {
        return dNO;
    }

    public void setDNO(String dNO) {
        this.dNO = dNO;
    }

    public String getDIngredient() {
        return dIngredient;
    }

    public void setDIngredient(String dIngredient) {
        this.dIngredient = dIngredient;
    }

    public String getMNO() {
        return mNO;
    }

    public void setMNO(String mNO) {
        this.mNO = mNO;
    }

    public String getDName() {
        return dName;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getDPrice() {
        return dPrice;
    }

    public void setDPrice(String dPrice) {
        this.dPrice = dPrice;
    }

    public String getDType() {
        return dType;
    }

    public void setDType(String dType) {
        this.dType = dType;
    }

    public String getDStar() {
        return dStar;
    }

    public void setDStar(String dStar) {
        this.dStar = dStar;
    }

}