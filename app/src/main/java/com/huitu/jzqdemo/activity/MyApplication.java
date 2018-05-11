package com.huitu.jzqdemo.activity;

import android.app.Application;

import com.huitu.jzqdemo.utils.Utils;
import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/2/26.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        MobSDK.init(this,"24e3d1415ce00","c7afbb44b034d45128cb1e6b320eb358");
    }
}
