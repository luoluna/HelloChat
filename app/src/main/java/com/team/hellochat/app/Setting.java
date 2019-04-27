package com.team.hellochat.app;

import com.team.hellochat.manager.UserManager;
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
}
