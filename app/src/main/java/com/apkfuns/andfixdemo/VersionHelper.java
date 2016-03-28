package com.apkfuns.andfixdemo;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by pengwei on 16/3/28.
 */
public class VersionHelper {

    /**
     * 获取版本名
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
