package com.team.hellochat.bean;

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
}
