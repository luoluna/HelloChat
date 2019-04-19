package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/1.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoom {
    private int id;
    private boolean isGroup;
    private String iconUri;
    private String title;
    private String message;
    // 未读消息数
    private int messageCount;
    private long putTime;

    public ChatRoom() {
    }

    public ChatRoom(int id, String iconUri, String title, String message, int messageCount, long putTime) {
        this.id = id;
        this.iconUri = iconUri;
        this.title = title;
        this.message = message;
        this.messageCount = messageCount;
        this.putTime = putTime;
    }

    public ChatRoom(int id, boolean isGroup, String iconUri, String title, String message, int messageCount, long putTime) {
        this.id = id;
        this.isGroup = isGroup;
        this.iconUri = iconUri;
        this.title = title;
        this.message = message;
        this.messageCount = messageCount;
        this.putTime = putTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public long getPutTime() {
        return putTime;
    }

    public void setPutTime(long putTime) {
        this.putTime = putTime;
    }
}
