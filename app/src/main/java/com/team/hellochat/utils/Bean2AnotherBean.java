package com.team.hellochat.utils;

import com.team.hellochat.bean.Discovery;
import com.team.hellochat.bean.Friend;
import com.team.hellochat.bean.User;

/**
 * Created by Sweven on 2019/4/30.
 * Email:sweventears@Foxmail.com
 */
public class Bean2AnotherBean {

    public static Friend User2Friend(User user) {
        Friend friend = new Friend();
        friend.setId(user.getId());
        friend.setCreditPoint(user.getCreditPoint());
        friend.setAvatar(user.getAvatar());
        friend.setNickname(user.getNickname());
        friend.setAddress(user.getAddress());
        friend.setAge(user.getAge());
        friend.setEmail(user.getEmail());
        friend.setHobby(user.getHobby());
        friend.setIdCard(user.getIdCard());
        friend.setPhone(user.getPhone());
        friend.setSex(user.getSex());
        friend.setSignature(user.getSignature());
        friend.setUser(user.getUser());
        return friend;
    }
    public static Discovery User2Discovery(User user) {
        Discovery discovery = new Discovery();
        discovery.setId(user.getId());
        discovery.setCreditPoint(user.getCreditPoint());
        discovery.setAvatar(user.getAvatar());
        discovery.setNickname(user.getNickname());
        discovery.setAddress(user.getAddress());
        discovery.setAge(user.getAge());
        discovery.setEmail(user.getEmail());
        discovery.setHobby(user.getHobby());
        discovery.setIdCard(user.getIdCard());
        discovery.setPhone(user.getPhone());
        discovery.setSex(user.getSex());
        discovery.setSignature(user.getSignature());
        discovery.setUser(user.getUser());
        return discovery;
    }
}
