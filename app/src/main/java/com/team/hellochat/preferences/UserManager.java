package com.team.hellochat.preferences;

import android.app.Activity;
import android.content.Context;

import com.team.hellochat.app.App;
import com.team.hellochat.utils.PreferenceUtil;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class UserManager {
    private static UserManager instance;

    private int uid;
    private String user;
    private String loginAccount;
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

    public UserManager putData(Activity activity) {
        uid = new PreferenceUtil(activity).getInt(App.SharedLabel.USER_ID);
        user = new PreferenceUtil(activity).getString(App.SharedLabel.USER);
        loginAccount = new PreferenceUtil(activity).getString(App.SharedLabel.LOGIN_ACCOUNT);
        password = new PreferenceUtil(activity).getString(App.SharedLabel.PASSWORD);
        return this;
    }

    public void setUid(Context context,int uid) {
        this.uid = uid;
    }

    public void setUser(Context context,String user) {
        this.user = user;
    }

    public void setLoginAccount(Context context,String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public void setPassword(Context context,String password) {
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public String getUser() {
        return user;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public String getPassword() {
        return password;
    }

}
