package com.team.hellochat.manager;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.team.hellochat.utils.PreferenceUtil;

import static com.team.hellochat.app.App.SharedLabel.IS_LOGIN;
import static com.team.hellochat.app.App.SharedLabel.TOKEN;
import static com.team.hellochat.app.App.SharedLabel.USER_ID;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class LogInManager {
    private static LogInManager instance;

    private int uid;
    private String token;
    private boolean isLogin;

    public static LogInManager getInstance() {
        if (instance == null) {
            synchronized (LogInManager.class) {
                instance = new LogInManager();
            }
        }
        return instance;
    }

    public static LogInManager getInstance(Context context) {
        if (instance == null) {
            synchronized (LogInManager.class) {
                instance = new LogInManager();
            }
        }
        instance.uid = new PreferenceUtil(context).getInt(USER_ID);
        instance.token = new PreferenceUtil(context).getString(TOKEN);
        instance.isLogin = new PreferenceUtil(context).getBoolean(IS_LOGIN);
        return instance;
    }

    public void setUid(Context context, int uid) {
        this.uid = uid;
        save(context);
    }

    public void setToken(Context context, String token) {
        this.token = token;
    }

    public void setLog(Context context, boolean log) {
        isLogin = log;
    }

    private void save(Context context) {
        Editor editor = new PreferenceUtil(context).getEditor();
        editor.putInt(USER_ID, uid);
        editor.putString(TOKEN, token);
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.apply();
    }

    public int getUid() {
        return uid;
    }

    public String getToken() {
        return token;
    }

    public boolean isLogin() {
        return isLogin;
    }
}
