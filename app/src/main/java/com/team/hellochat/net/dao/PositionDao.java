package com.team.hellochat.net.dao;

import com.team.hellochat.net.entity.User;

import java.util.List;

public abstract interface PositionDao {
	public abstract int insertPosition(int paramInt1, int paramInt2, int paramInt3);

	public abstract int deletePosition(int paramInt);

	public abstract List<User> selectUser(int paramInt1, int paramInt2, int paramInt3);
}
