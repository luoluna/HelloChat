package com.team.hellochat.app;

import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.MD5Util;

/**
 * Created by Sweven on 2019/4/25.
 * Email:sweventears@Foxmail.com
 */
public class Setting {
    public static String getChatRoomFile(int withId) {
        return MD5Util.getMD5(UserManager.getInstance().getUid() + "with" + withId);
    }
}
