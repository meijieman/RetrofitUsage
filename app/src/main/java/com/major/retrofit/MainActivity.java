package com.major.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.major.retrofit.api.ApiFactory;
import com.major.retrofit.bean.Gank;
import com.major.retrofit.util.LogUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                LogUtil.i("click");
                Observable<Gank> gank = ApiFactory.getInstance().getApiService().getGank(1);
                gank.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
//                    .onErrorResumeNext()
                    .subscribe(new Subscriber<Gank>(){
                        @Override
                        public void onCompleted(){

                        }

                        @Override
                        public void onError(Throwable e){
                            LogUtil.e("onError " + e);
                        }

                        @Override
                        public void onNext(Gank gank){
                            LogUtil.i("onNext " + gank);
                        }
                    });
            }
        });
    }
}
