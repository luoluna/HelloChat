package com.team.hellochat.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/3/27.
 * Email:sweventears@Foxmail.com
 */
public class MyApplication extends Application {
    public static List<Object> activitys = new ArrayList<>();
    private static MyApplication instance;
    public static volatile Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (!activitys.contains(activity))
            activitys.add(activity);
    }

    public static int num() {
        int qq = activitys.size();
        return qq;
    }

    public void remove() {
        for (Object activity : activitys) {
            ((Activity) activity).finish();
        }
    }

    // 遍历所有Activity并finish
    public void destory() {
//        activitys.clear();
        for (Object activity : activitys) {
            ((Activity) activity).finish();
        }
        System.exit(0);
    }
}
