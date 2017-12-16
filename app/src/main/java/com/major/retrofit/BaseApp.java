package com.major.retrofit;

import android.app.Application;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/12/17 1:44
 */
public class BaseApp extends Application{

    private static BaseApp sInstance;

    public static BaseApp getInstance(){
        return sInstance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
    }
}
