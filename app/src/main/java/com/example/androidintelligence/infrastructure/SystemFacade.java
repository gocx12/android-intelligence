package com.example.androidintelligence.infrastructure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SystemFacade {
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
    public static void launchApp(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        if (intent != null) {
            context.startActivity(intent);
        }
    }

    private void clickRandomPoint() {
        Random random = new Random();
        int x = random.nextInt(1080);  // 假设屏幕宽度为1080px
        int y = random.nextInt(1920);  // 假设屏幕高度为1920px

        // 生成点击事件
        Log.d("AccessibilityService", "点击随机点: x = " + x + ", y = " + y);
//        performGlobalAction(GLOBAL_ACTION_HOME);  // 点击HOME按钮作为演示
    }
}
