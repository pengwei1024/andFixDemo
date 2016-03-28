package com.apkfuns.andfixdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.apkfuns.andfixdemo.models.FixConfigEntity;
import com.apkfuns.logutils.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("测试");
        App.getRequest()
                .getFixConfig()
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<FixConfigEntity>() {
                    @Override
                    public void call(FixConfigEntity fixConfigEntity) {
                        getPatch(fixConfigEntity.getPatchUrl());
                    }
                });
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<FixConfigEntity>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.e(e);
//                    }
//
//                    @Override
//                    public void onNext(FixConfigEntity fixConfigEntity) {
////                        getPatch(fixConfigEntity.getPatchUrl());
//                        LogUtils.d(fixConfigEntity);
//                    }
//                });

//        getPatch("http://qiniu.apkfuns.com/getFixConfig1.json");

    }


    private void getPatch(String patch) {
        App.getRequest()
                .downloadPatch(patch)
                .subscribeOn(Schedulers.io())
                .filter(new Func1<ResponseBody, Boolean>() {
                    @Override
                    public Boolean call(ResponseBody responseBody) {
                        return responseBody != null && responseBody.byteStream() != null;
                    }
                })
                .map(new Func1<ResponseBody, File>() {
                    @Override
                    public File call(ResponseBody responseBody) {
                        InputStream is = responseBody.byteStream();
                        return IOUtils.writeToFile(is, "/sdcard/newset.txt");
                    }
                })
                .filter(new Func1<File, Boolean>() {
                    @Override
                    public Boolean call(File file) {
                        LogUtils.d(file == null);
                        return file != null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        LogUtils.d(file.getAbsolutePath());
                    }
                })
        ;
    }


    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
