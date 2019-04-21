package com.team.hellochat.manager;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.team.hellochat.utils.PreferenceUtil;

import static com.team.hellochat.app.App.SharedLabel.LOG_NAME;
import static com.team.hellochat.app.App.SharedLabel.NICKNAME;
import static com.team.hellochat.app.App.SharedLabel.PASSWORD;
import static com.team.hellochat.app.App.SharedLabel.USER_ID;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class UserManager {
    private static UserManager instance;

    private int uid;
    private String nickname;
    private String loginName;
    private String password;

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    public static UserManager getInstance(Context context) {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        instance.uid = new PreferenceUtil(context).getInt(USER_ID);
        instance.nickname = new PreferenceUtil(context).getString(NICKNAME);
        instance.loginName = new PreferenceUtil(context).getString(LOG_NAME);
        instance.password = new PreferenceUtil(context).getString(PASSWORD);
        return instance;
    }

    public void setUid(Context context, int uid) {
        this.uid = uid;
    }

    public void setUser(Context context, String user) {
        this.nickname = user;
    }

    public void setLoginAccount(Context context, String loginAccount) {
        this.loginName = loginAccount;
    }

    public void setPassword(Context context, String password) {
        this.password = password;
        save(context);
    }

    private void save(Context context) {
        Editor editor = new PreferenceUtil(context).getEditor();
        editor.putInt(USER_ID, uid);
        editor.putString(NICKNAME, nickname);
        editor.putString(LOG_NAME, loginName);
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public int getUid() {
        return uid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

}
