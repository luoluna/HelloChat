package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/24.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoomItem {
    private int withUid;
    private boolean isGroup;
    private String title;
    private int icon;
    private boolean isTop;
    private String file = "";
    private ChatMessage message;
    private MessageInfo lastMessage;

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public void setLastMessage(MessageInfo lastMessage) {
        this.lastMessage = lastMessage;
    }

    public MessageInfo getLastMessage() {
        return lastMessage;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }
}
