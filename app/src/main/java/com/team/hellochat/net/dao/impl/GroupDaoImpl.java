package com.team.hellochat.net.dao.impl;

import com.team.hellochat.net.dao.BaseDao;
import com.team.hellochat.net.dao.GroupDao;
import com.team.hellochat.net.entity.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GroupDaoImpl extends BaseDao implements GroupDao {
	public int insertGroup(Group group) {
		String sql = "insert into chatgroup (u_id,c_name,c_describe,c_createTime) values (?,?,?,?)";
		Object[] obj = { Integer.valueOf(group.getU_id()), group.getC_name(), group.getC_describle(), new Date() };
		int num = super.update(sql, obj);
		Object[] obj1 = { Integer.valueOf(group.getU_id()) };
		ResultSet rs = super.getResultSet("select c_id from chatgroup where u_id = ? order by c_id desc limit 1", obj1);
		int c_id = 0;
		try {
			if (rs.next()) {
				c_id = rs.getInt("c_id");
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		new ChatGroupInfoDaoImpl().addGroup(c_id, group.getU_id());
		return num;
	}

	public int deleteGroup(int c_id) {
		String sql = "delete from chatgroup where c_id = ?";
		Object[] obj = { Integer.valueOf(c_id) };
		int num = super.update(sql, obj);
		return num;
	}

	public int updateGroup(int c_id, int c_num) {
		String sql = "update chatgroup set c_number = ? where c_id = ?";
		Object[] obj = { c_num, Integer.valueOf(c_id) };
		int num = super.update(sql, obj);
		return num;
	}

	public Group selectGroup(int c_id) {
		String sql = "select * from chatgroup where c_id = ?";
		Object[] obj = {c_id};
		ResultSet rs = super.getResultSet(sql, obj);
		Group g = null;
		try {
			while (rs.next()){
				g = new Group();
				g.setC_id(rs.getInt("c_id"));
				g.setU_id(rs.getInt("u_id"));
				g.setC_name(rs.getString("c_name"));
				g.setC_number(rs.getInt("c_number"));
				g.setC_describle(rs.getString("c_describle"));
				g.setC_createTime(rs.getDate("c_createTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return g;
	}
}
