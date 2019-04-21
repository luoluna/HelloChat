package com.team.hellochat.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatMessage {
    private int id;
    private String content;
    private List<MessageInfo> list=new ArrayList<>();

    public ChatMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
