package com.team.hellochat.net.dao;

import com.team.hellochat.net.entity.User;

public abstract interface UserDao {
	public abstract int insertUser(User paramUser);

	public abstract User selectUserById(int paramInt);

	public abstract int updateUserById(User paramUser);
	
	public abstract int changePwd(User user);
}
