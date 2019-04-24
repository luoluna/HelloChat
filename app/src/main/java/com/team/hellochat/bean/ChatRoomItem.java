package com.team.hellochat.bean;

import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.MD5Util;

/**
 * Created by Sweven on 2019/4/24.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoomItem {
    private int id;
    private int withUid;
    private String title;
    private String icon;
    private boolean isTop;

    public String getFile(){
        return MD5Util.getMD5(UserManager.getInstance().getUid() +"whith"+withUid);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWithUid() {
        return withUid;
    }

    public void setWithUid(int withUid) {
        this.withUid = withUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }
}
