package com.major.retrofit.util;

import android.util.Log;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/12/17 0:02
 */
public class LogUtil{

    private static final String TAG = "tag_ele";

    public static void i(String msg){
        Log.i(TAG, msg);
    }

    public static void w(String msg){
        Log.w(TAG, msg);
    }

    public static void e(String msg){
        Log.e(TAG, msg);
    }
}
