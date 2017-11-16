package com.example.yao.onlinemenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_keeperinfo
{
    @SerializedName("ONO")
    @Expose
    private String oNO;
    @SerializedName("OID")
    @Expose
    private String oID;
    @SerializedName("OPwd")
    @Expose
    private String oPwd;
    @SerializedName("OName")
    @Expose
    private String oName;
    @SerializedName("OSex")
    @Expose
    private String oSex;
    @SerializedName("OEmail")
    @Expose
    private String oEmail;
    @SerializedName("OCellPhone")
    @Expose
    private String oCellPhone;
    @SerializedName("OStatus")
    @Expose
    private String oStatus;

    public String getONO() {
        return oNO;
    }

    public void setONO(String oNO) {
        this.oNO = oNO;
    }

    public String getOID() {
        return oID;
    }

    public void setOID(String oID) {
        this.oID = oID;
    }

    public String getOPwd() {
        return oPwd;
    }

    public void setOPwd(String oPwd) {
        this.oPwd = oPwd;
    }

    public String getOName() {
        return oName;
    }

    public void setOName(String oName) {
        this.oName = oName;
    }

    public String getOSex() {
        return oSex;
    }

    public void setOSex(String oSex) {
        this.oSex = oSex;
    }

    public String getOEmail() {
        return oEmail;
    }

    public void setOEmail(String oEmail) {
        this.oEmail = oEmail;
    }

    public String getOCellPhone() {
        return oCellPhone;
    }

    public void setOCellPhone(String oCellPhone) {
        this.oCellPhone = oCellPhone;
    }

    public String getOStatus() {
        return oStatus;
    }

    public void setOStatus(String oStatus) {
        this.oStatus = oStatus;
    }
}
