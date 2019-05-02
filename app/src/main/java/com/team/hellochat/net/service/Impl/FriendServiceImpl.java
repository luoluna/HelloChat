package com.team.hellochat.net.service.Impl;

import com.team.hellochat.net.dao.FriendDao;
import com.team.hellochat.net.dao.impl.FriendDaoImpl;
import com.team.hellochat.net.entity.Friend;
import com.team.hellochat.net.service.FriendService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FriendServiceImpl implements FriendService {
	private FriendDao friendDao = new FriendDaoImpl();

	public int insertFriend(Friend friend) {
		return this.friendDao.insertFriend(friend);
	}

	public HashMap<String, List<Friend>> selectUserById(int f_userID) {
		HashMap<String, List<Friend>> hm = new HashMap<String, List<Friend>>();
		Iterator<Friend> it = this.friendDao.selectUserById(f_userID).iterator();
		while (it.hasNext()) {
			Friend fri = (Friend) it.next();
			if (hm.containsKey(fri.getF_friendGroups())) {
				hm.get(fri.getF_friendGroups()).add(fri);
			} else {
				List<Friend> list = new ArrayList<Friend>();
				list.add(fri);
				hm.put(fri.getF_friendGroups(), list);
			}
		}

		return hm;
	}

	public int updateFriendById(Friend friend) {
		return this.friendDao.updateFriendById(friend);
	}

	public int deleteFriendById(Friend friend) {
		return this.friendDao.deleteFriendById(friend);
	}

	public static void main(String[] args) {
		Friend f = new Friend();
		f.setF_createTime(new Date());
		f.setF_firendID(4);
		f.setF_friendGroups("mm");
		f.setF_friendState(2);
		f.setF_friendType(2);
		f.setF_name("小东");
		f.setF_userID(1);
		new FriendServiceImpl().insertFriend(f);
	}
}
