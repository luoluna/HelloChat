package com.team.hellochat.manager;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.team.hellochat.app.App;
import com.team.hellochat.bean.User;
import com.team.hellochat.utils.JsonUtil;
import com.team.hellochat.utils.PreferenceUtil;

import static com.team.hellochat.activity.LoginActivity.EMAIL;
import static com.team.hellochat.activity.LoginActivity.PHONE;
import static com.team.hellochat.app.App.SharedLabel.LOG_NAME;
import static com.team.hellochat.app.App.SharedLabel.NICKNAME;
import static com.team.hellochat.app.App.SharedLabel.PASSWORD;
import static com.team.hellochat.app.App.SharedLabel.USER;
import static com.team.hellochat.app.App.SharedLabel.USER_ID;
import static com.team.hellochat.app.App.SharedLabel.USER_NAME;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class UserManager {
    private static UserManager instance;

    private int uid;
    private String username;
    private String nickname;
    private String loginName;
    private String password;
    private User user;

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
        instance.username = new PreferenceUtil(context).getString(App.SharedLabel.USER_NAME);
        instance.user = JsonUtil.jsonToObject(new PreferenceUtil(context).getString(App.SharedLabel.USER), new User());
        instance.nickname = new PreferenceUtil(context).getString(NICKNAME);
        instance.loginName = new PreferenceUtil(context).getString(LOG_NAME);
        instance.password = new PreferenceUtil(context).getString(PASSWORD);
        return instance;
    }

    public void setUsername(Context context, String username) {
        this.username = username;
        user.setUser(this.username);
        save(context);
    }

    public void setUser(Context context, User user) {
        this.user = user;
        save(context);
    }

    public void setAvatar(Context context, int avatar) {
        this.user.setAvatar(avatar);
        save(context);
    }

    public void setUid(Context context, int uid) {
        this.uid = uid;
        user.setId(this.uid);
        save(context);
    }

    public void setNickname(Context context, String nickname) {
        this.nickname = nickname;
        user.setNickname(this.nickname);
        save(context);
    }

    public void setLoginAccount(Context context, String loginAccount) {
        this.loginName = loginAccount;
        save(context);
    }

    public void setPassword(Context context, String password) {
        this.password = password;
        save(context);
    }

    private void save(Context context) {
        Editor editor = new PreferenceUtil(context).getEditor();
        editor.putInt(USER_ID, uid);
        editor.putString(USER_NAME, username);
        editor.putString(NICKNAME, nickname);
        editor.putString(LOG_NAME, loginName);
        editor.putString(PASSWORD, password);
        editor.putString(USER, JsonUtil.object2Json(user));
        editor.apply();
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
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

    public User getUser() {
        return user;
    }

    public void logIn(Context context, int logInAccount, String loginName, String pass) {
        this.loginName = loginName;
        this.password = pass;
        //default data
        if (logInAccount == PHONE) {
            user = UserDatabaseManager.getInstance().getUserByPhone(loginName);
        } else if (logInAccount == EMAIL) {
            user = UserDatabaseManager.getInstance().getUserByEmail(loginName);
        } else {
            user = UserDatabaseManager.getInstance().getUserByUser(loginName);
        }
        this.uid = user.getId();
        this.username = user.getUser();
        this.nickname = user.getNickname();

//        AddressBook addressBook = new AddressBook();
//        addressBook.setId(user.getId());
//        addressBook.setFriendsCount(1);
//        addressBook.setGroupsCount(0);
//        List<Friend> friends = new ArrayList<>();
//        Friend friend = new Friend();
//        friend.setId(16);
//        friend.setCreditPoint(372);
//        friend.setUser("香菜");
//        friend.setSex(Sex.WOMEN);
//        friend.setAddress("四川南充");
//        friend.setAvatar(13);
//        friend.setAge(17);
//        friend.setEmail("15644531@qq.com");
//        friend.setPhone("15520323701");
//        friends.add(friend);
//        addressBook.setFriends(friends);
//        AddressBookManager.getInstance().setAddressBook(context, addressBook);
        save(context);
    }
}
