package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class Friend extends User {
    private String group;
    private String imageUri;
    private String msg;
    private String remark;

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRemark() {
        return (remark == null || remark.equals("")) ? getNickname() : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
