package com.major.retrofit.api;

import com.major.retrofit.AppConfig;
import com.major.retrofit.BaseApp;
import com.major.retrofit.interceptor.HttpLoggingInterceptor;
import com.major.retrofit.interceptor.NetInterceptor;
import com.major.retrofit.interceptor.NoNetInterceptor;
import com.major.retrofit.util.LogUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/12/17 0:04
 */
public class ApiFactory{

    private Retrofit mRetrofit;

    private volatile static ApiFactory sInstance;

    public static ApiFactory getInstance(){
        if(sInstance == null){
            synchronized(ApiFactory.class){
                sInstance = new ApiFactory();
            }
        }
        return sInstance;
    }


    private ApiFactory(){
//        okhttp3.logging.HttpLoggingInterceptor loggingInterceptor = new okhttp3.logging.HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS);

        int size = 1024 * 1024 * 10;
        File cacheFile = new File(BaseApp.getInstance().getCacheDir(), "okHttp");
        LogUtil.w("cacheFile " + cacheFile.getAbsolutePath());
        Cache cache = new Cache(cacheFile, size);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .readTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)
                .addInterceptor(new NoNetInterceptor())    // 将无网络拦截器当做应用拦截器添加
                .addNetworkInterceptor(new NetInterceptor()) // 将有网络拦截器当做网络拦截器添加
//                .addInterceptor(new CacheInterceptor())// 会完整的调用 CacheInterceptor， 但是读不到缓存策略
//                .addNetworkInterceptor(new CacheInterceptor()) // 没有走 request，只走 response。当读到缓存后都不走了，直接返回缓存
                .addInterceptor(interceptor)
                .cache(cache)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()) // 解析数据
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public ApiService getApiService(){
        return mRetrofit.create(ApiService.class);
    }
}
