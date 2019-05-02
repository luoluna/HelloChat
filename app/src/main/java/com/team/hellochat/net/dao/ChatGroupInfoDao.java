package com.team.hellochat.net.dao;

import java.util.List;

public abstract interface ChatGroupInfoDao {
	
	public abstract int addGroup(int c_id, int u_id);
	
	public abstract int removeGroup(int c_id, int u_id);
	
	public abstract List<Integer> usersGroup(int c_id);
}
