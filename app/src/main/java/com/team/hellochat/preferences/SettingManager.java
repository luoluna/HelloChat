package com.team.hellochat.preferences;

import android.content.Context;

/**
 * Created by Sweven on 2019/4/20.
 * Email:sweventears@Foxmail.com
 */
public class SettingManager {
    private static SettingManager instance;

    public static SettingManager getInstance() {
        if (instance == null) {
            synchronized (SettingManager.class) {
                instance = new SettingManager();
            }
        }
        return instance;
    }

    public static SettingManager getInstance(Context context) {
        if (instance == null) {
            synchronized (SettingManager.class) {
                instance = new SettingManager();
            }
        }
        return instance;
    }
}
