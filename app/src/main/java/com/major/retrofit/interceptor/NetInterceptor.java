package com.major.retrofit.interceptor;

import android.util.Log;

import com.major.retrofit.BaseApp;
import com.major.retrofit.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @desc: 参考 http://www.jianshu.com/p/cf59500990c7
 * @author: Major
 * @since: 2017/12/17 3:12
 */
public class NetInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException{
        Request request = chain.request();
        boolean connected = NetworkUtil.isConnected(BaseApp.getInstance());
        if(connected){
            //如果有网络，缓存90s
            Log.e("zhanghe", "print");
            Response response = chain.proceed(request);
            int maxTime = 90;
            return response.newBuilder()
                           .removeHeader("Pragma")
                           .header("Cache-Control", "public, max-age=" + maxTime)
                           .build();
        }
        //如果没有网络，不做处理，直接返回
        return chain.proceed(request);
    }
}
