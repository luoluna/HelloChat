package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class DiscussionGroup {
    private int id;
    private String group = "";
    private String title = "";
    private String imageUri = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
