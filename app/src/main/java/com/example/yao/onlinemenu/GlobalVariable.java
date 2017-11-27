package com.example.yao.onlinemenu;

import android.app.Application;

/**
 * Created by John on 2017/11/23.
 */

public class GlobalVariable extends Application {
    private Boolean prevTimerNotRunning;

    public void setprevTimerNotRunning(Boolean prevTimerNotRunning){
        this.prevTimerNotRunning = prevTimerNotRunning;
    }
    //顯示 變數字串
    public Boolean getprevTimerNotRunning() {
        return prevTimerNotRunning;
    }
}
