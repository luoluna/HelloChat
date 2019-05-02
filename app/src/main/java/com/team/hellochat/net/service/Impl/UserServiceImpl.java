package com.team.hellochat.net.service.Impl;

import com.team.hellochat.net.dao.UserDao;
import com.team.hellochat.net.dao.impl.UserDaoImpl;
import com.team.hellochat.net.entity.User;
import com.team.hellochat.net.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	public int insertUser(User user) {
		return this.userDao.insertUser(user);
	}

	public User selectUserById(int u_ID) {
		return this.userDao.selectUserById(u_ID);
	}

	public int updateUserById(User user) {
		return this.userDao.updateUserById(user);
	}

	public static void main(String[] args) {
		User user = new User();
		user.setU_nickName("小ai");
		user.setU_telephone("95593");
		user.setU_passWord("99999");

		new UserServiceImpl().insertUser(user);
		System.out.println(new UserServiceImpl().selectUserById(2).toJson());
	}

	@Override
	public boolean verifyPwd(User user) {
		if(userDao.selectUserById(user.getU_ID())==null)
			return false;
		if(userDao.selectUserById(user.getU_ID()).getU_passWord().equals(user.getU_passWord()))
			return true;
		return false;
	}

	@Override
	public int changePwd(User user) {
		int num = userDao.changePwd(user);
		return num;
	}
}
