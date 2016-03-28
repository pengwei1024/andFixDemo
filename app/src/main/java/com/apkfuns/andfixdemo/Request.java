package com.apkfuns.andfixdemo;

import com.apkfuns.andfixdemo.models.FixConfigEntity;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by pengwei on 16/3/28.
 */
public interface Request {
    @GET("/getFixConfig1.json")
    Observable<FixConfigEntity> getFixConfig();

    @GET
    @Streaming
    Observable<ResponseBody> downloadPatch(@Url String url);
}
