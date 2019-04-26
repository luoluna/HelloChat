package com.team.hellochat.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.team.hellochat.utils.PreferenceUtil;

import static com.team.hellochat.app.App.SharedLabel.IS_FIRST;

/**
 * Created by Sweven on 2019/4/20.
 * Email:sweventears@Foxmail.com
 */
public class SettingManager {
    private static final String SETTING = "setting";
    private static SettingManager instance;

    private boolean isFirstInstall;

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
        instance.isFirstInstall = new PreferenceUtil(context, SETTING).getBoolean(IS_FIRST);
        return instance;
    }

    public void setFirstInstall(Context context, boolean firstInstall) {
        isFirstInstall = firstInstall;
        save(context);
    }

    private void save(Context context) {
        SharedPreferences.Editor editor= new PreferenceUtil(context, SETTING).getEditor();
        editor.putBoolean(IS_FIRST, isFirstInstall);
        editor.apply();
    }

    public boolean isFirstInstall() {
        return isFirstInstall;
    }
}
