package com.team.hellochat.net.service;

import com.team.hellochat.net.entity.Group;

public abstract interface GroupService {
	//创建群
	public abstract int insertGroup(Group paramGroup);
	//通过群号删除群
	public abstract int deleteGroup(int c_id);
	//更新群人数
	public abstract int updateGroup(int c_id, int c_num);
	//查询群信息
	public abstract Group selectGroup(int c_id);
}
