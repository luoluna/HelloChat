package com.team.hellochat.net.dao;

import com.team.hellochat.net.entity.Group;

public abstract interface GroupDao {
	public abstract int insertGroup(Group paramGroup);

	public abstract int deleteGroup(int paramInt);

	public abstract int updateGroup(int paramInt1, int paramInt2);

	public abstract Group selectGroup(int paramInt);
}
