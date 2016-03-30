package com.apkfuns.andfixdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.apkfuns.andfixdemo.models.FixConfigEntity;
import com.apkfuns.logutils.LogUtils;


import java.io.File;
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
//        showToast("测试+ababab");
//        new AlertDialog.Builder(this).setMessage("12345").create().show();
//        getPatch("/sdcard/tencent/QQfile_recv/new.apatch");
//        showToast(Test.getMsg());
        int a = 10;
        showToast("修改xml");
        App.getRequest()
                .getFixConfig()
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<FixConfigEntity>() {
                    @Override
                    public void call(FixConfigEntity fixConfigEntity) {
                        LogUtils.d(fixConfigEntity);
                        if (fixConfigEntity.isNeedFix()) {
                            getPatch(fixConfigEntity.getPatchUrl());
                        }
                    }
                })
//                .subscribe(new Observer<FixConfigEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        LogUtils.d("onCompleted()");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.e(e);
//                    }
//
//                    @Override
//                    public void onNext(FixConfigEntity fixConfigEntity) {
//
//                    }
//                })
        ;
//                .subscribe(new Action1<FixConfigEntity>() {
//                    @Override
//                    public void call(FixConfigEntity fixConfigEntity) {
//                        LogUtils.d(fixConfigEntity);
//                        if (fixConfigEntity.isNeedFix()) {
//                            App.getInstance().addPatch("/sdcard/tencent/QQfile_recv/new.apatch");
//                        }
//                    }
//                });
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

        final int x = 0;


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(it);
            }
        });

    }


    private void getPatch(String patch) {
//        App.getInstance().addPatch(patch);
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
                        return IOUtils.writeToFile(is, "/sdcard/new.apatch");
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
                        LogUtils.d("下载成功:%s", file.getAbsolutePath());
                        App.getInstance().addPatch(file.getAbsolutePath());
                    }
                })
        ;
    }


    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
