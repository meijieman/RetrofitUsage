package com.major.retrofit.api;

import com.major.retrofit.bean.Gank;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/12/17 0:02
 */
public interface ApiService{

    // http://gank.io/api/data/Android/10/{page}
    @GET("api/data/Android/2/{page}")
    Observable<Gank> getGank(@Path("page") int page);

}
