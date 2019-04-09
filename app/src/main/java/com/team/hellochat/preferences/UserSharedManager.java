package com.team.hellochat.preferences;

import android.app.Activity;

import com.team.hellochat.app.App;
import com.team.hellochat.utils.PreferenceUtil;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class UserSharedManager {
    private static UserSharedManager instance;

    private String user;
    private String loginAccount;
    private String password;

    public static UserSharedManager getInstance() {
        if (instance == null) {
            synchronized (UserSharedManager.class) {
                if (instance == null) {
                    instance = new UserSharedManager();
                }
            }
        }
        return instance;
    }

    public UserSharedManager putData(Activity activity) {
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
