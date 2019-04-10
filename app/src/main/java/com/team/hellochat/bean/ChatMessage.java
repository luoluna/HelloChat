package com.team.hellochat.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatMessage {
    private int id;
    private int uid;
    private String headPhoto;
    private boolean isSelf;
    private String message;
    private String nickname;
    private long time;

    private List<MessageInfo> list;

    public ChatMessage() {
    }

    public ChatMessage(int id, int uid, String headPhoto, boolean isSelf, String message, String nickname, long time) {
        this.id = id;
        this.uid = uid;
        this.headPhoto = headPhoto;
        this.isSelf = isSelf;
        this.message = message;
        this.nickname = nickname;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<MessageInfo> getList() {
        return list;
    }

    public void setList(List<MessageInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", uid=" + uid +
                ", headPhoto='" + headPhoto + '\'' +
                ", isSelf=" + isSelf +
                ", message='" + message + '\'' +
                ", nickname='" + nickname + '\'' +
                ", time=" + time +
                ", list=" + list +
                '}';
    }

    public static void main(String[] a) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage("sds");
        chatMessage.setHeadPhoto("http");
        List<MessageInfo> list = new ArrayList<>();
        MessageInfo messageInfo = new MessageInfo(1, "http", "55210326", MessageType.IMAGE, "[img]http://image.com/12432.png[/img]");
        System.out.print(MessageType.getName(MessageType._IMAGE));
        list.add(messageInfo);
        list.add(messageInfo);
        list.add(messageInfo);
        chatMessage.setList(list);
        String json = "{\"id\":0,\"uid\":0,\"headPhoto\":\"http\",\"message\":\"sds\",\"nickname\":null,\"time\":0,\"list\":[{\"uid\":1,\"headPictureUri\":\"http\",\"time\":\"55210326\",\"type\":\"IMAGE\",\"information\":\"[img]http://image.com/12432.png[/img]\"},{\"uid\":1,\"headPictureUri\":\"http\",\"time\":\"55210326\",\"type\":\"IMAGE\",\"information\":\"[img]http://image.com/12432.png[/img]\"},{\"uid\":1,\"headPictureUri\":\"http\",\"time\":\"55210326\",\"type\":\"IMAGE\",\"information\":\"[img]http://image.com/12432.png[/img]\"}],\"self\":false}";
        try {
//            System.out.print(new ObjectMapper().readValue(json, ChatMessage.class));
            System.out.print(new ObjectMapper().writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
