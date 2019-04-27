package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/27.
 * Email:sweventears@Foxmail.com
 */
public class Collect extends User {
    private boolean isCollect;
    private boolean isChecked;

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
