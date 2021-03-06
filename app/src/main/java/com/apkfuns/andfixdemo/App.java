package com.apkfuns.andfixdemo;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;
import com.apkfuns.logutils.LogUtils;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pengwei on 16/3/28.
 */
public class App extends Application {

    private PatchManager manager;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initAndFix();
        logUtilsConfig();
    }


    public static App getInstance() {
        return instance;
    }

    private void logUtilsConfig() {
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configShowBorders(true)
                .configTagPrefix("andFixDemo");
    }


    public static Request getRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(Request.class);
    }

    public void addPatch(String patch) {
        try {
            manager.addPatch(patch);
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }

    private void initAndFix() {
        manager = new PatchManager(this);
        manager.init(VersionHelper.getVersionName(this));
//        try {
//            manager.addPatch("/sdcard/tencent/QQfile_recv/new.apatch");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        manager.loadPatch();
    }
}
