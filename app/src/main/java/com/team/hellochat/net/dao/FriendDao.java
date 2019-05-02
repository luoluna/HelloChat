package com.team.hellochat.net.dao;

import com.team.hellochat.net.entity.Friend;

import java.util.List;

public abstract interface FriendDao {
	
	public abstract int insertFriend(Friend paramFriend);

	public abstract List<Friend> selectUserById(int paramInt);

	public abstract int updateFriendById(Friend paramFriend);

	public abstract int deleteFriendById(Friend paramFriend);
}
