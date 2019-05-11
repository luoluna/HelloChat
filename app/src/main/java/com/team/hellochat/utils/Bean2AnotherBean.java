package com.team.hellochat.utils;

import com.team.hellochat.app.CreditRule;
import com.team.hellochat.bean.Discovery;
import com.team.hellochat.bean.Friend;
import com.team.hellochat.bean.Sex;
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

    public static User User2NetUser(com.team.hellochat.net.entity.User netUser){
        User user= new User();
        user.setId(netUser.getU_ID());
        user.setAvatar(Integer.parseInt(netUser.getU_headPortrait()));
        user.setNickname(netUser.getU_nickName());
//        user1.setAddress(netUser.getAddress());
        user.setAge(netUser.getU_age());
        user.setEmail(netUser.getU_email());
//        user1.setHobby(netUser.getHobby());
//        user1.setIdCard(netUser.getU_ID());
        user.setPhone(netUser.getU_telephone());
        user.setSex(Sex.getSex(netUser.getU_sex()==1?Sex._MAN:Sex._WOMEN));
        user.setSignature(netUser.getU_signaTure());
        user.setCreditPoint(new CreditRule(user).getCreditPoint());
        user.setUser(netUser.getUser());
        return user;
    }
}
