package com.example.yao.onlinemenu;

import android.app.Application;

/**
 * Created by Jane on 2017/10/3.
 */

public class GlobalVariable extends Application {
    //要傳送的字串
    private String OwnerID;
    //修改 變數字串
    public void setOwnerID(String ownerID) { this.OwnerID = ownerID; }
    //顯示.使用 變數字串
    public String getOwnerID() { return OwnerID; }

    private String StoreID;
    public void setStoreID(String storeID) { this.StoreID = storeID; }
    public String getStoreID() { return StoreID; }

    private String CustomerID;
    public void setCustomerID(String customerID) { this.CustomerID = customerID; }
    public String getCustomerID() { return CustomerID; }

    private Boolean prevTimerNotRunning;
    public void setprevTimerNotRunning(Boolean prevTimerNotRunning){ this.prevTimerNotRunning = prevTimerNotRunning; }
    public Boolean getprevTimerNotRunning() {
        return prevTimerNotRunning;
    }
}
