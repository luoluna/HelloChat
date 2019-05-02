package com.team.hellochat.net.dao.impl;

import com.team.hellochat.net.dao.BaseDao;
import com.team.hellochat.net.dao.PositionDao;
import com.team.hellochat.net.entity.User;

import java.util.List;

public class PositionDaoImpl extends BaseDao implements PositionDao {
	public int insertPosition(int u_id, int longitude, int latitude) {
		String sql = "insert into position (u_id,longitude,latitude) values (?,?,?)";
		Object[] obj = { Integer.valueOf(u_id), Integer.valueOf(longitude), Integer.valueOf(latitude) };
		int num = super.update(sql, obj);
		return num;
	}

	public int deletePosition(int u_id) {
		String sql = "delete from position where u_id = ?";
		Object[] obj = { sql };
		int num = super.update(sql, obj);
		return num;
	}

	public List<User> selectUser(int u_id, int longitude, int latitude) {
		//String sql = "select u_id from position ";
		return null;
	}
}
