package com.team.hellochat.bean;

import java.util.List;

/**
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class AddressBook {
    private int id;
    private List<Friend> friends;
    private List<DiscussionGroup> groups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<DiscussionGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<DiscussionGroup> groups) {
        this.groups = groups;
    }
}
