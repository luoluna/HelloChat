package com.team.hellochat.bean;

import java.util.List;

/**
 * Created by Sweven on 2019/4/1.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoom {
    private int id;
    private boolean isGroup;
    private String iconUri;
    private String title;
    // 未读消息数
    private int messageCount;
    private long putTime;
    private ChatMessage message;

    public ChatRoom() {
    }

    public ChatRoom(int id, boolean isGroup, String iconUri, String title, int messageCount, long putTime, ChatMessage message) {
        this.id = id;
        this.isGroup = isGroup;
        this.iconUri = iconUri;
        this.title = title;
        this.messageCount = messageCount;
        this.putTime = putTime;
        this.message = message;
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

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public int getMessageCount() {
        List<MessageInfo> list = message.getList();
        messageCount = 0;
        for (MessageInfo info : list) {
            if (!info.isRead()) {
                messageCount++;
            }
        }
        return messageCount;
    }

    public long getPutTime() {
        return putTime;
    }

    public void setPutTime(long putTime) {
        this.putTime = putTime;
    }
}
