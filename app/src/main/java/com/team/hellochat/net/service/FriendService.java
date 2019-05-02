package com.team.hellochat.net.service;

import com.team.hellochat.net.entity.Friend;

import java.util.HashMap;
import java.util.List;

public abstract interface FriendService {
    //添加好友
    public abstract int insertFriend(Friend friend);

    //查询好友列表
    public abstract HashMap<String, List<Friend>> selectUserById(int u_id);

    //修改好友信息
    public abstract int updateFriendById(Friend friend);

    //删除好友
    public abstract int deleteFriendById(Friend friend);
}
