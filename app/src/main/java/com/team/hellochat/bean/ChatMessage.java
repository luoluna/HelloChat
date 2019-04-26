package com.team.hellochat.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatMessage {
    private int id;
    private String file;
    private String content;
    private List<MessageInfo> list = new ArrayList<>();
    private MessageInfo lastMessage;
    private long lastTime;
    private int noReadCount;

    public ChatMessage() {
    }

    public int getNoReadCount() {
        int count = 0;
        for (MessageInfo info : list) {
            if (!info.isRead()) {
                count++;
            }
        }
        return noReadCount = count;
    }

    private int getMessageCount() {
        return list.size();
    }

    public long getLastTime() {
        return lastTime = getLastMessage().getTime();
    }

    public MessageInfo getLastMessage() {
        return lastMessage = getMessageCount() > 0 ? list.get(getMessageCount() - 1) : new MessageInfo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MessageInfo> getList() {
        return list;
    }

    public void setList(List<MessageInfo> list) {
        this.list = list;
    }
}
