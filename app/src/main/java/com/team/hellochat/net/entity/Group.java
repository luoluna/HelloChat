package com.team.hellochat.net.entity;

import java.util.Date;

public class Group {
	private int c_id;
	private int u_id;
	private String c_name;
	private int c_number;
	private String c_describle;
	private Date c_createTime;

	public int getC_id() {
		return this.c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return this.c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public int getC_number() {
		return this.c_number;
	}

	public void setC_number(int c_number) {
		this.c_number = c_number;
	}

	public String getC_describle() {
		return this.c_describle;
	}

	public void setC_describle(String c_describle) {
		this.c_describle = c_describle;
	}

	public int getU_id() {
		return this.u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public Date getC_createTime() {
		return this.c_createTime;
	}

	public void setC_createTime(Date c_createTime) {
		this.c_createTime = c_createTime;
	}
}
