package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/10.
 * Email:sweventears@Foxmail.com
 */
public class MessageInfo {
    private int uid;
    private String headPictureUri;
    private String time;
    private MessageType type;
    private String information;

    public MessageInfo() {
    }

    public MessageInfo(int uid, String headPictureUri, String time, MessageType type, String information) {
        this.uid = uid;
        this.headPictureUri = headPictureUri;
        this.time = time;
        this.type = type;
        this.information = information;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getHeadPictureUri() {
        return headPictureUri;
    }

    public void setHeadPictureUri(String headPictureUri) {
        this.headPictureUri = headPictureUri;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "uid=" + uid +
                ", headPictureUri='" + headPictureUri + '\'' +
                ", type=" + type +
                ", information='" + information + '\'' +
                '}';
    }
}
