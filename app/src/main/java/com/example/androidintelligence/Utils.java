package com.example.androidintelligence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.List;

public class Utils {
    public static HashMap<String, String> getAllInstalledApp(Context context) {
        HashMap<String, String> appNameToPackageName = new HashMap<>();
        // 获取PackageManager实例
        PackageManager packageManager = context.getPackageManager();

        // 获取已安装的应用程序信息
        @SuppressLint("QueryPermissionsNeeded") List<PackageInfo> packages = packageManager.getInstalledPackages(0);

        // 遍历并打印每个应用程序的包名
        for (PackageInfo packageInfo : packages) {
            String packageName = packageInfo.packageName;
            ApplicationInfo ai = null;
            try {
                ai = packageManager.getApplicationInfo(packageName, 0);
                String appName = (String) packageManager.getApplicationLabel(ai);//获取应用名称
                System.out.println("Installed package: " + packageName + ", app name: " + appName);
                appNameToPackageName.put(appName, packageName);
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return appNameToPackageName;
    }


    // 启动微信
    public static void launchWeChat(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.android.vending");
        if (intent != null) {
             context.startActivity(intent);
        }
    }
}
