package com.team.hellochat.net.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private Connection conn = null;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			this.conn = DriverManager.getConnection(
					"jdbc:mysql://139.199.5.186:3306/im?useUnicode=true&characterEncoding=utf-8", "sweven", "luoluna");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("连接数据库成功！");
		return this.conn;
	}

	public int update(String sql, Object[] objects) {
		int num = -1;
		try {
			this.conn = getConnection();
			try {
				this.conn.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			PreparedStatement ps = this.conn.prepareStatement(sql);
			if ((objects != null) && (objects.length > 0)) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}

			System.out.println(ps.toString());
			num = ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public ResultSet getResultSet(String sql, Object[] objects) {
		ResultSet rs = null;
		try {
			this.conn = getConnection();

			PreparedStatement ps = this.conn.prepareStatement(sql);
			if ((objects != null) && (objects.length > 0)) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}

			rs = ps.executeQuery();
			System.out.println(ps.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
