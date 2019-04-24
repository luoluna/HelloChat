package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/10.
 * Email:sweventears@Foxmail.com
 */
public class MessageInfo {
    private int uid;
    private String nickname;
    private String headPictureUri;
    private long time;
    private MessageType type;
    private String information;
    private boolean isRead;

    public MessageInfo() {
    }

    public MessageInfo(int uid, String nickname, String headPictureUri, long time, MessageType type, String information) {
        this.uid = uid;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPictureUri() {
        return headPictureUri;
    }

    public void setHeadPictureUri(String headPictureUri) {
        this.headPictureUri = headPictureUri;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
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
