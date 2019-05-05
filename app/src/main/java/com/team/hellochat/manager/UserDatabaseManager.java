package com.team.hellochat.manager;

import android.content.Context;

import com.team.hellochat.app.CreditRule;
import com.team.hellochat.bean.Discovery;
import com.team.hellochat.bean.Sex;
import com.team.hellochat.bean.User;
import com.team.hellochat.utils.Bean2AnotherBean;
import com.team.hellochat.utils.JsonUtil;
import com.team.hellochat.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.team.hellochat.app.App.SimulatedData.USER_LIST;

/**
 * Created by Sweven on 2019/4/27.
 * Email:sweventears@Foxmail.com
 */
public class UserDatabaseManager {
    private static UserDatabaseManager instance;

    private UserList list;

    public static UserDatabaseManager getInstance() {
        if (instance == null) {
            synchronized (UserDatabaseManager.class) {
                instance = new UserDatabaseManager();
            }
        }
        return instance;
    }

    public static UserDatabaseManager getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDatabaseManager.class) {
                instance = new UserDatabaseManager();
            }
        }
        String value = new PreferenceUtil(context, USER_LIST).getString(USER_LIST);
        instance.list = JsonUtil.jsonToObject(value, new UserList());
        if (instance.list.getUsers().size() > 0) {
            return instance;
        } else {
            new PreferenceUtil(context, USER_LIST).save(USER_LIST, defaultData());
            String s = new PreferenceUtil(context, USER_LIST).getString(USER_LIST);
            instance.list = JsonUtil.jsonToObject(s, new UserList());
            return instance;
        }
    }

    private static UserList createUsers() {
        UserList userList = new UserList();
        User user;

        user = new User();
        user.setNickname("(^.^)Y Ya!!");
        user.setId(1);
        user.setUser("萌动灵咒");
        user.setSex(Sex.MAN);
        user.setAddress("四川成都");
        user.setAvatar(20);
        user.setAge(16);
        user.setCreditPoint(406);
        user.setEmail("156431@qq.com");
        user.setPhone("15528123701");
        userList.getUsers().add(user);

        user = new User();
        user.setNickname("小学生");
        user.setId(2);
        user.setUser("小学生");
        user.setSex(Sex.MAN);
        user.setAddress("四川成都");
        user.setAvatar(17);
        user.setAge(22);
        user.setCreditPoint(390);
        user.setEmail("156431@qq.com");
        user.setPhone("15528123701");
        userList.getUsers().add(user);

        user = new User();
        user.setId(3);
        user.setAddress("江苏 上海");
        user.setAge(21);
        user.setAvatar(6);
        user.setCreditPoint(365);
        user.setPhone("15523120135");
        user.setEmail("thisMyWifi@sina.com");
        user.setNickname("问题儿童[S̲̅V̲̅I̲̅P̲̅]");
        user.setSex(Sex.SECRECY);
        user.setUser("月不过万不改名");
        userList.getUsers().add(user);

        user = new User();
        user.setId(4);
        user.setAddress("广西 南宁");
        user.setAge(23);
        user.setAvatar(11);
        user.setCreditPoint(0);
        user.setPhone("15523120135");
        user.setEmail("thisMyWifi@sina.com");
        user.setNickname("正在输入中……");
        user.setSex(Sex.MAN);
        user.setUser("明月桥尾");
        userList.getUsers().add(user);

        user = new User();
        user.setId(4);
        user.setAddress("广西 南宁");
        user.setAge(23);
        user.setAvatar(11);
        user.setCreditPoint(0);
        user.setPhone("15523120135");
        user.setEmail("124556894@sina.com");
        user.setNickname("正在输入中……");
        user.setSex(Sex.MAN);
        user.setUser("111111");
        userList.getUsers().add(user);

        return userList;
    }

    public void registerNewUser(Context context, User user) {
        list = UserDatabaseManager.getInstance(context).getList();
        user.setCreditPoint(new CreditRule(user).getCreditPoint());
        user.setAvatar(new Random().nextInt(36));
        user.setId(list.getUsers().size());
        list.getUsers().add(user);
        new PreferenceUtil(context, USER_LIST).save(USER_LIST, JsonUtil.object2Json(list));
        UserDatabaseManager.getInstance(context);
    }

    public UserList getList() {
        return list;
    }

    public User getUserByUid(int uid) {
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getId() == uid) {
                return list.getUsers().get(i);
            }
        }
        return null;
    }

    public User getUserByUser(String user) {
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getUser().equals(user)) {
                return list.getUsers().get(i);
            }
        }
        return null;
    }

    public User getUserByPhone(String phone) {
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getPhone().equals(phone)) {
                return list.getUsers().get(i);
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getEmail().equals(email)) {
                return list.getUsers().get(i);
            }
        }
        return null;
    }

    private String getPass(int uid) {
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getId() == uid) {
                return list.getUsers().get(i).getPassword();
            }
        }
        return null;
    }

    public String getPassByPhone(String phone) {
        List<User> users = list.getUsers();
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getPhone().equals(phone)) {
                return list.getUsers().get(i).getPassword();
            }
        }
        return null;
    }

    public String getPassByEmail(String email) {
        List<User> users = list.getUsers();
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getEmail().equals(email)) {
                return list.getUsers().get(i).getPassword();
            }
        }
        return null;
    }

    public String getPassByUser(String user) {
        List<User> users = list.getUsers();
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getUser().equals(user)) {
                return list.getUsers().get(i).getPassword();
            }
        }
        return null;
    }

    public List<Discovery> getDiscovery(Context context) {
        List<User> users = list.getUsers();
        int meId = UserManager.getInstance().getUid();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == meId) {
                users.remove(i);
                break;
            }
        }
        List<Discovery> discoveries = new ArrayList<>();
        for (User user : users) {
            Discovery discovery = Bean2AnotherBean.User2Discovery(user);
            discoveries.add(discovery);
        }
        return discoveries;
    }

    public boolean fixPass(String pass, String user) {
        for (int i = 0; i < list.getUsers().size(); i++) {
            if (list.getUsers().get(i).getUser().equals(user)) {
                list.getUsers().get(i).setPassword(pass);
                return true;
            }
        }
        return false;
    }

    static class UserList {
        private List<User> users = new ArrayList<>();

        List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }

    private static String defaultData() {
        return "{\"users\":" +
                "[{\"address\":\"\",\"age\":20,\"avatar\":12,\"creditPoint\":211,\"email\":\"2653922@qq.com\",\"hobby\":[\"打篮球\",\"唱歌\",\"看书\",null,null],\"id\":0,\"idCard\":\"\",\"nickname\":\"一书在手\",\"password\":\"qqqqqq\",\"phone\":\"15532543374\",\"sex\":\"SECRECY\",\"signature\":\"天地任逍遥\",\"user\":\"new\"}," +
                "{\"address\":\"\",\"age\":200,\"avatar\":3,\"creditPoint\":311,\"email\":\"243rer@foxmail.com\",\"hobby\":[\"宅漫\",null,null,null,null],\"id\":1,\"idCard\":\"51162219921020566\",\"nickname\":\"天涯无边\",\"password\":\"111111\",\"phone\":\"15523165464\",\"sex\":\"SECRECY\",\"signature\":\"emmm,没什么好说的\",\"user\":\"123\"}," +
                "{\"address\":\"\",\"age\":372,\"avatar\":2,\"creditPoint\":311,\"email\":\"4546yh3265@gmail.com\",\"hobby\":[null,null,null,null,null],\"id\":2,\"idCard\":\"51162219921020566\",\"nickname\":\"小学生\",\"password\":\"qqqqqq\",\"phone\":\"15521655485\",\"sex\":\"SECRECY\",\"signature\":\"回家了\",\"user\":\"张呀\"}," +
                "{\"address\":\"\",\"age\":22,\"avatar\":21,\"creditPoint\":281,\"email\":\"\",\"hobby\":[null,null,null,null,null],\"id\":3,\"idCard\":\"51162219921020566\",\"nickname\":\"天碰\",\"password\":\"qqqqqq\",\"phone\":\"15526478979\",\"sex\":\"SECRECY\",\"signature\":\"\",\"user\":\"天碰\"}," +
                "{\"address\":\"\",\"age\":31,\"avatar\":12,\"creditPoint\":281,\"email\":\"\",\"hobby\":[null,null,null,null,null],\"id\":4,\"idCard\":\"51162219921020566\",\"nickname\":\"丫丫\",\"password\":\"qqqqqq\",\"phone\":\"13265647567\",\"sex\":\"SECRECY\",\"signature\":\"\",\"user\":\"丫丫\"}," +
                "{\"address\":\"\",\"age\":16,\"avatar\":2,\"creditPoint\":281,\"email\":\"\",\"hobby\":[null,null,null,null,null],\"id\":5,\"idCard\":\"51162219921020566\",\"nickname\":\"user\",\"password\":\"qqqqqq\",\"phone\":\"15514888044\",\"sex\":\"SECRECY\",\"signature\":\"早就绝望了\",\"user\":\"user\"}," +
                "{\"address\":\"\",\"age\":20,\"avatar\":25,\"creditPoint\":181,\"email\":\"\",\"hobby\":[null,null,null,null,null],\"id\":6,\"idCard\":\"\",\"nickname\":\"和两\",\"password\":\"qqqqqq\",\"phone\":\"13244124857\",\"sex\":\"SECRECY\",\"signature\":\"\",\"user\":\"和两\"}," +
                "{\"address\":\"\",\"age\":0,\"avatar\":18,\"creditPoint\":181,\"email\":\"\",\"hobby\":[null,null,null,null,null],\"id\":7,\"idCard\":\"\",\"nickname\":\"不一样的验烟火\",\"password\":\"qqqqqq\",\"phone\":\"14778562645\",\"sex\":\"SECRECY\",\"signature\":\"\",\"user\":\"不一样的验烟火\"}]}";
    }
}
