package com.example.yao.onlinemenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IM17 on 2017/10/3.
 */

public class ServerResponse_login {
    @SerializedName("CID")
    @Expose
    private String CID;
    @SerializedName("OID")
    @Expose
    private String OID;

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.CID = OID;
    }
}
