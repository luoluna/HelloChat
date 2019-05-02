package com.team.hellochat.net.test;

import java.util.Scanner;

import com.team.hellochat.net.entity.Friend;
import com.team.hellochat.net.entity.Group;
import com.team.hellochat.net.entity.User;
import com.team.hellochat.net.service.FriendService;
import com.team.hellochat.net.service.GroupService;
import com.team.hellochat.net.service.Impl.ChatGroupInfoServicesImpl;
import com.team.hellochat.net.service.Impl.FriendServiceImpl;
import com.team.hellochat.net.service.Impl.GroupServiceImpl;
import com.team.hellochat.net.service.Impl.UserServiceImpl;
import com.team.hellochat.net.service.UserService;
import com.team.hellochat.net.socketServices.ChatClient;

public class Start {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        FriendService friendService = new FriendServiceImpl();
        GroupService groupService = new GroupServiceImpl();
        ChatGroupInfoServicesImpl chatGroupInfoServicesImpl = new ChatGroupInfoServicesImpl();
        User user = new User();
        Friend friend = new Friend();
        Group group = new Group();
        Scanner sc = new Scanner(System.in);
		
		/*System.out.println("注册");
		System.out.println("用户名：");
		String u_name = sc.nextLine();
		user.setU_nickName(u_name);
		System.out.println("电话：");
		String u_tel = sc.nextLine();
		user.setU_telephone(u_tel);
		System.out.println("密码：");
		String u_pwd = sc.nextLine();
		user.setU_passWord(u_pwd);
		userService.insertUser(user);*/

        System.out.println("登录");
        System.out.println("账号：");
        user.setU_ID(Integer.valueOf(sc.nextLine()));
        System.out.println("密码：");
        user.setU_passWord(sc.nextLine());
        if (userService.verifyPwd(user)) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登录失败");
        }
        user = userService.selectUserById(user.getU_ID());
        System.out.println(user);
		
		/*System.out.println("修改密码");
		System.out.println("新密码");
		user.setU_passWord(sc.nextLine());
		userService.changePwd(user);
		int mm = 0;
		while (mm++ < 3) {
			System.out.println("添加好友");
			friend.setF_userID(user.getU_ID());
			System.out.println("好友ID");
			friend.setF_firendID(sc.nextInt());
			friendService.insertFriend(friend);
		}*/
		
		/*System.out.println("删除好友");
		System.out.println("好友ID");
		friend.setF_firendID(sc.nextInt());
		friendService.deleteFriendById(friend);
		
		System.out.println("修改分组");
		System.out.println("好友ID");
		friend.setF_firendID(sc.nextInt());
		System.out.println("分组");
		friend.setF_friendGroups(sc.nextLine());
		friendService.updateFriendById(friend);*/
		
		/*System.out.println("创建群聊");
		System.out.println("群名称");
		group.setC_name(sc.nextLine());
		System.out.println("描述");
		group.setC_describle(sc.nextLine());
		group.setU_id(user.getU_ID());
		groupService.insertGroup(group);*/
		
		/*System.out.println("加入群");
		chatGroupInfoServicesImpl.addGroup(sc.nextInt(), user.getU_ID());
		
		System.out.println("退群");
		chatGroupInfoServicesImpl.removeGroup(sc.nextInt(), user.getU_ID());*/

        new ChatClient().chat(user);

        sc.close();

    }

}
