package com.apkfuns.andfixdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.apkfuns.logutils.LogUtils;

/**
 * Created by pengwei on 16/3/30.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("这是第二个界面");
//        try {
//            throw new NullPointerException("aaaaa");
//
//        } catch (Exception e) {
//            LogUtils.d(e.toString());
//        }
    }
}
