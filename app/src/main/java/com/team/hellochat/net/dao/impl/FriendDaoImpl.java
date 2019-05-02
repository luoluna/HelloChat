package com.team.hellochat.net.dao.impl;

import com.team.hellochat.net.dao.BaseDao;
import com.team.hellochat.net.dao.FriendDao;
import com.team.hellochat.net.entity.Friend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDaoImpl extends BaseDao implements FriendDao {
	public int insertFriend(Friend friend) {
		String sql = "insert into friends (f_userID, f_firendID, f_name, f_friendType, f_friendGroups,f_friendState,f_createTime)values(?,?,?,?,?,?,?)";
		Object[] obj = { Integer.valueOf(friend.getF_userID()), Integer.valueOf(friend.getF_firendID()),
				friend.getF_name(), Integer.valueOf(friend.getF_friendType()), friend.getF_friendGroups(),
				Integer.valueOf(friend.getF_friendState()), friend.getF_createTime() };
		int num = super.update(sql, obj);
		return num;
	}

	public List<Friend> selectUserById(int f_userID) {
		String sql = "select * from friends where f_userID=?";
		Object[] objects = { Integer.valueOf(f_userID) };
		ResultSet rs = super.getResultSet(sql, objects);
		List<Friend> list = new ArrayList<Friend>();
		try {
			while (rs.next()) {
				Friend friend = new Friend();
				friend.setF_id(rs.getInt("f_id"));
				friend.setF_userID(rs.getInt("f_userID"));
				friend.setF_firendID(rs.getInt("f_firendID"));
				friend.setF_name(rs.getString("f_name"));
				friend.setF_friendType(rs.getInt("f_friendType"));
				friend.setF_friendGroups(rs.getString("f_friendGroups"));
				friend.setF_friendState(rs.getInt("f_friendState"));
				friend.setF_createTime(rs.getDate("f_createTime"));
				list.add(friend);
				friend = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateFriendById(Friend friend) {
		String sql = "update friends set f_name=? where f_userID=? and f_firendID=?";
		Object[] obj = { friend.getF_name(), Integer.valueOf(friend.getF_userID()),
				Integer.valueOf(friend.getF_firendID()) };
		int num = super.update(sql, obj);
		return num;
	}

	public int deleteFriendById(Friend friend) {
		String sql = "delete from friends where f_userID=? and f_firendID=?";
		Object[] obj = { Integer.valueOf(friend.getF_userID()), Integer.valueOf(friend.getF_firendID()) };
		int num = super.update(sql, obj);
		return num;
	}
}
