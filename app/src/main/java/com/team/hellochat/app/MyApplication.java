package com.team.hellochat.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import com.squareup.leakcanary.LeakCanary;
import com.umeng.commonsdk.UMConfigure;

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

        UMConfigure.init(this, "5cba84070cafb2b132000337", "OuYu", UMConfigure.DEVICE_TYPE_PHONE, null);

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
        return activitys.size();
    }

    public void remove() {
        for (Object activity : activitys) {
            ((Activity) activity).finish();
        }
    }

    // 遍历所有Activity并finish
    public void destroy() {
        for (Object activity : activitys) {
            ((Activity) activity).finish();
        }
        System.exit(0);
    }
}
