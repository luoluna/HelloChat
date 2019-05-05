package com.team.hellochat.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.LogUtil;
import com.team.hellochat.utils.MD5Util;

import java.util.List;

/**
 * Created by Sweven on 2019/4/25.
 * Email:sweventears@Foxmail.com
 */
public class Setting {
    public static String getChatRoomFile(int withId) {
        return MD5Util.getMD5(UserManager.getInstance().getUid() + "with" + withId);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.equals("");
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode(Activity activity) {
        PackageManager manager = activity.getPackageManager();//获取包管理器
        try {
            //通过当前的包名获取包的信息
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);//获取包对象信息
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取版本名
     *
     * @return
     */
    public static String getVersionName(Activity activity) {
        PackageManager manager = activity.getPackageManager();
        try {
            //第二个参数代表额外的信息，例如获取当前应用中的所有的Activity
            PackageInfo packageInfo = manager.getPackageInfo(activity.getPackageName(), PackageManager.GET_ACTIVITIES
            );
            ActivityInfo[] activities = packageInfo.activities;
            showActivities(activities);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void showActivities(ActivityInfo[] activities) {
        for (ActivityInfo activity : activities) {
            new LogUtil("activity name").i(activity.name);
        }
    }

    public static void contact(Context activity){
        Intent intent=new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("tencent://message/?menu=yes&uin=2653922416&websitename=im.qq.com"));
        activity.startActivity(intent);
    }
}
