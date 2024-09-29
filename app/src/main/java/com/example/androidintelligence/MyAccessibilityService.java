package com.example.androidintelligence;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Handler;
import android.view.accessibility.AccessibilityEvent;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MyAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // 无操作，在此项目中我们只关注服务的其他功能
    }

    @Override
    public void onInterrupt() {
        Toast.makeText(this, "无障碍服务被中断", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Toast.makeText(this, "无障碍服务已连接", Toast.LENGTH_SHORT).show();
        // 设置无障碍服务信息
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);

        // 打开微信应用
        new Handler().postDelayed(() -> {
            Utils.launchWeChat(MyAccessibilityService.this);
            clickRandomPoint();
        }, 1000);
    }

    private void clickRandomPoint() {
        Random random = new Random();
        int x = random.nextInt(1080);  // 假设屏幕宽度为1080px
        int y = random.nextInt(1920);  // 假设屏幕高度为1920px

        // 生成点击事件
        Log.d("AccessibilityService", "点击随机点: x = " + x + ", y = " + y);
        performGlobalAction(GLOBAL_ACTION_HOME);  // 点击HOME按钮作为演示
    }
}