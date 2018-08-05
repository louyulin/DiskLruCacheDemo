package com.example.louyulin.diskdemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by louyulin on 2018/7/27.
 */

public class AppContext extends Application {
    public static AppContext appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        getAppVersion();
    }

    public static int getAppVersion() {
        Context context = getContext();
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static Context getContext() {
        return appContext.getApplicationContext();
    }
}
