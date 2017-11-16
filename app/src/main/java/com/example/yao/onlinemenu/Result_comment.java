package com.example.yao.onlinemenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_comment {

    @SerializedName("CommtNO")
    @Expose
    private String commtNO;
    @SerializedName("CID")
    @Expose
    private String cID;
    @SerializedName("OID")
    @Expose
    private String oID;
    @SerializedName("CommtText")
    @Expose
    private String commtText;
    @SerializedName("CommtStar")
    @Expose
    private String commtStar;
    @SerializedName("CommtTime")
    @Expose
    private String commtTime;

    public String getCommtNO() {
        return commtNO;
    }

    public void setCommtNO(String commtNO) {
        this.commtNO = commtNO;
    }

    public String getCID() {
        return cID;
    }

    public void setCID(String cID) {
        this.cID = cID;
    }

    public String getOID() {
        return oID;
    }

    public void setOID(String oID) {
        this.oID = oID;
    }

    public String getCommtText() {
        return commtText;
    }

    public void setCommtText(String commtText) {
        this.commtText = commtText;
    }

    public String getCommtStar() {
        return commtStar;
    }

    public void setCommtStar(String commtStar) {
        this.commtStar = commtStar;
    }

    public String getCommtTime() {
        return commtTime;
    }

    public void setCommtTime(String commtTime) {
        this.commtTime = commtTime;
    }

}
