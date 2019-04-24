package com.team.hellochat.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatMessage {
    private int id;
    private String name;
    private String content;
    private List<MessageInfo> list = new ArrayList<>();

    public ChatMessage() {
    }

    public int getNoReadCount() {
        int count = 0;
        for (MessageInfo info : list) {
            if (!info.isRead()) {
                count++;
            }
        }
        return count;
    }

    public long getLastTime() {
        return list.get(list.size() - 1).getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
