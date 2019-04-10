package com.team.hellochat.preferences;

import android.app.Activity;

import com.team.hellochat.app.App;
import com.team.hellochat.utils.PreferenceUtil;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class UserManager {
    private static UserManager instance;

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
        user = new PreferenceUtil(activity).getString(App.SharedLabel.USER);
        loginAccount = new PreferenceUtil(activity).getString(App.SharedLabel.LOGIN_ACCOUNT);
        password = new PreferenceUtil(activity).getString(App.SharedLabel.PASSWORD);
        return this;
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
