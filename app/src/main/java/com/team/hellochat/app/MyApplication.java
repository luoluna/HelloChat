package com.team.hellochat.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.team.hellochat.manager.AddressBookManager;
import com.team.hellochat.manager.ChatRoomListManager;
import com.team.hellochat.manager.LogInManager;
import com.team.hellochat.manager.SettingManager;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.FacilityUtil;
import com.team.hellochat.utils.MD5Util;
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

        UMConfigure.init(this, App.UM_APP_ID,
                "OuYu", UMConfigure.DEVICE_TYPE_PHONE,
                MD5Util.getMD5(FacilityUtil.getAndroidID(this)));

        // 初始化单例管理器
        SettingManager.getInstance(this);
        LogInManager.getInstance(this);
        UserManager.getInstance(this);
        AddressBookManager.getInstance(this);
        ChatRoomListManager.getInstance(this);
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
