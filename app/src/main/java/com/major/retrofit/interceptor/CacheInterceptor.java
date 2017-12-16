package com.major.retrofit.interceptor;

import com.major.retrofit.BaseApp;
import com.major.retrofit.util.LogUtil;
import com.major.retrofit.util.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @desc: 可以参考 {@link okhttp3.internal.cache.CacheInterceptor}
 *
 * 需要调用 addInterceptor(new CacheInterceptor()) 以及 addNetworkInterceptor(new CacheInterceptor())
 * @author: Major
 * @since: 2017/12/17 0:02
 */
public class CacheInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException{

        Request request = chain.request();

        // 判断有无网络连接
        boolean connected = NetworkUtil.isConnected(BaseApp.getInstance());
        LogUtil.e("connected: " + connected);
        if(!connected){
            // 如果没有网络,从缓存获取数据。
            // 注意当没有网络，又设置为 FORCE_CACHE， 会返回 504 错误，此时需要判断为网络异常。
            LogUtil.i("没有网络，强制使用缓存");
            request = request.newBuilder()
                             .cacheControl(CacheControl.FORCE_CACHE)
                             .build();
        }

        Response response = chain.proceed(request);
        LogUtil.i("response");
        if(connected){
            String cacheControl = response.cacheControl().toString();
            LogUtil.i("有网络，缓存策略(设置前) " + cacheControl);
            int maxTime = 90;
            Response response1 = setCacheControl(response, 5);
            LogUtil.i("有网络，缓存策略(设置后) " + cacheControl);
            return response1;
        } else {
            String cacheControl = response.cacheControl().toString();
            LogUtil.e("没有网络，缓存策略(设置前) " + cacheControl);

            // 设置响应头在没有网络时的缓存时间
            int maxTime = 3600;
            Response response1 = setCacheControl(response, 10);
            LogUtil.e("没有网络，缓存策略(设置后) " + cacheControl);
            return response1;
        }
    }

    private Response setCacheControl(Response response, int maxTime){
        return response.newBuilder()
                       .header("Cache-Control", "public, max-age=" + maxTime)
                       .removeHeader("Pragma")
                       .build();
    }
}
