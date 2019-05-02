package com.team.hellochat.net.service;

import java.util.List;

public abstract interface ChatGroupInfoServices {
	//加群
	public abstract int addGroup(int c_id, int u_id);
	//退群
	public abstract int removeGroup(int c_id, int u_id);
	//查询群成员
	public abstract List<Integer> usersGroup(int c_id);
}
