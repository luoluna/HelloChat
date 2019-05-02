package com.team.hellochat.net.service.Impl;

import com.team.hellochat.net.dao.ChatGroupInfoDao;
import com.team.hellochat.net.dao.GroupDao;
import com.team.hellochat.net.dao.impl.ChatGroupInfoDaoImpl;
import com.team.hellochat.net.dao.impl.GroupDaoImpl;
import com.team.hellochat.net.service.ChatGroupInfoServices;

import java.util.List;

public class ChatGroupInfoServicesImpl implements ChatGroupInfoServices {
	private ChatGroupInfoDao chatGroupInfoDao = new ChatGroupInfoDaoImpl();
	private GroupDao groupDao = new GroupDaoImpl();

	public int addGroup(int c_id, int u_id) {
		int num = this.chatGroupInfoDao.addGroup(c_id, u_id);
		int num1 = this.groupDao.updateGroup(c_id, this.chatGroupInfoDao.usersGroup(c_id).size());
		return num+num1;
	}

	public int removeGroup(int c_id, int u_id) {
		int num = this.chatGroupInfoDao.removeGroup(c_id, u_id);
		int num1 = this.groupDao.updateGroup(c_id, this.chatGroupInfoDao.usersGroup(c_id).size());
		return num+num1;
	}

	public List<Integer> usersGroup(int c_id) {
		return this.chatGroupInfoDao.usersGroup(c_id);
	}

	public static void main(String[] args) {
		System.out.println(new ChatGroupInfoServicesImpl().usersGroup(3));
	}
}
