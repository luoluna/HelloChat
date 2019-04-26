package com.team.hellochat.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class AddressBook {
    private Integer id;
    private int friendsCount;
    private int groupsCount;
    private List<Friend> friends = new ArrayList<>();
    private List<DiscussionGroup> groups = new ArrayList<>();

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public int getGroupsCount() {
        return groupsCount;
    }

    public void setGroupsCount(int groupsCount) {
        this.groupsCount = groupsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
