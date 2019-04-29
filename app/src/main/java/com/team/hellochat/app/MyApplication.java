package com.team.hellochat.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.team.hellochat.manager.AddressBookManager;
import com.team.hellochat.manager.ChatRoomListManager;
import com.team.hellochat.manager.CollectManager;
import com.team.hellochat.manager.LogInManager;
import com.team.hellochat.manager.SettingManager;
import com.team.hellochat.manager.UserDatabaseManager;
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
    public static volatile Context applicationContext;
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public static int num() {
        return activitys.size();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();

        UMConfigure.init(this, App.UM_APP_ID,
                "OuYu", UMConfigure.DEVICE_TYPE_PHONE,
                MD5Util.getMD5(FacilityUtil.getAndroidID(this)));

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

        // 初始化单例管理器
        UserDatabaseManager.getInstance(this);
        SettingManager.getInstance(this);
        LogInManager.getInstance(this);
        UserManager.getInstance(this);
        AddressBookManager.getInstance(this);
        ChatRoomListManager.getInstance(this);
        CollectManager.getInstance(this);
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (!activitys.contains(activity))
            activitys.add(activity);
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
