package com.wsg.demo;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by wushange on 2016/07/14.
 */
public class MyApplication extends Application {
    private static MyApplication INSTANCE;
    /**
     * 对外提供整个应用生命周期的Context
     **/
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);

    }

    public static Application getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new MyApplication();
        }
        return INSTANCE;
    }


    /**
     * 对外提供Application Context
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }
}
