package com.team.hellochat.net.dao.impl;

import com.team.hellochat.net.dao.BaseDao;
import com.team.hellochat.net.dao.ChatGroupInfoDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatGroupInfoDaoImpl extends BaseDao implements ChatGroupInfoDao {
	public int addGroup(int c_id, int u_id) {
		String sql = "insert into chatgroupinfo (c_id,u_id,createTime) values (?,?,?)";
		Object[] obj = { Integer.valueOf(c_id), Integer.valueOf(u_id), new Date() };
		int num = super.update(sql, obj);
		new GroupDaoImpl().updateGroup(c_id, usersGroup(c_id).size());
		return num;
	}

	public int removeGroup(int c_id, int u_id) {
		String sql = "delete from chatgroupinfo where c_id = ? and u_id = ?";
		Object[] obj = { c_id, u_id };
		int num = super.update(sql, obj);
		new GroupDaoImpl().updateGroup(c_id, usersGroup(c_id).size());
		return num;
	}

	public List<Integer> usersGroup(int c_id) {
		String sql = "select u_id from chatgroupinfo where c_id = ?";
		Object[] obj = { c_id };
		ResultSet rs = super.getResultSet(sql, obj);
		List<Integer> list = new ArrayList<Integer>();
		try {
			while (rs.next()) {
				list.add(rs.getInt("u_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
