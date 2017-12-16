package com.major.retrofit.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/12/17 1:43
 */
public class NetworkUtil{

    /**
     * 判断是否有网络
     */
    public static boolean isConnected(Context context){
        if(context != null){
            ConnectivityManager mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(mConnectivityManager != null){
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if(mNetworkInfo != null){
                    return mNetworkInfo.isAvailable();
                }
            }
        }
        return false;
    }
}
