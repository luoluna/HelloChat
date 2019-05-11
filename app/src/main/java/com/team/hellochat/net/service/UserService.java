package com.team.hellochat.net.service;

import com.team.hellochat.net.entity.User;

public abstract interface UserService {
	//注册用户
	public abstract int insertUser(User user);

	//查询用户
	public abstract User selectUserById(int u_id);

	User loginByUser(String user,String pass);

	//修改密码
	public abstract int updateUserById(User user);
	
	//验证密码
	public abstract boolean verifyPwd(User user);
	
	//修改密码
	public int changePwd(User user);
}
