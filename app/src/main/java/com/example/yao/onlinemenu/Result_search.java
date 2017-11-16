package com.example.yao.onlinemenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IM17 on 2017/10/2.
 */

public class Result_search {


    @SerializedName("DNO")
    @Expose
    private String dno;
    @SerializedName("Dish")
    @Expose
    private String dish;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("DStar")
    @Expose
    private String dStar;
    @SerializedName("ShopName")
    @Expose
    private String shopName;
    @SerializedName("DType")
    @Expose
    private String dType;
    @SerializedName("DIngredient")
    @Expose
    private String dIngredient;
    @SerializedName("CTR")
    @Expose
    private int ctr;

    public String getDNO() {
        return dno;
    }

    public void setDNO(String dno) {
        this.dno = dno;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDStar() {
        return dStar;
    }

    public void setDStar(String dStar) {
        this.dStar = dStar;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDType() {
        return dType;
    }

    public void setDType(String dType) {
        this.dType = dType;
    }

    public String getDIngredient() {
        return dIngredient;
    }

    public void setDIngredient(String dIngredient) {
        this.dIngredient = dIngredient;
    }

    public int getCTR() {
        return ctr;
    }

    public void setCTR(int ctr) {
        this.ctr = ctr;
    }
}
