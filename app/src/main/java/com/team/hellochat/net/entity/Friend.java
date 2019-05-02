package com.team.hellochat.net.entity;

import java.util.Date;

public class Friend {
	private int f_id;
	private int f_userID;
	private int f_firendID;
	private String f_name;
	private int f_friendType;
	private String f_friendGroups;
	private int f_friendState;
	private Date f_createTime;

	public int getF_id() {
		return this.f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public int getF_userID() {
		return this.f_userID;
	}

	public void setF_userID(int f_userID) {
		this.f_userID = f_userID;
	}

	public int getF_firendID() {
		return this.f_firendID;
	}

	public void setF_firendID(int f_firendID) {
		this.f_firendID = f_firendID;
	}

	public String getF_name() {
		return this.f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public int getF_friendType() {
		return this.f_friendType;
	}

	public void setF_friendType(int f_friendType) {
		this.f_friendType = f_friendType;
	}

	public String getF_friendGroups() {
		return this.f_friendGroups;
	}

	public void setF_friendGroups(String f_friendGroups) {
		this.f_friendGroups = f_friendGroups;
	}

	public int getF_friendState() {
		return this.f_friendState;
	}

	public void setF_friendState(int f_friendState) {
		this.f_friendState = f_friendState;
	}

	public Date getF_createTime() {
		return this.f_createTime;
	}

	public void setF_createTime(Date f_createTime) {
		this.f_createTime = f_createTime;
	}

	public String toJson() {
		return

		"{\"f_id\":" + this.f_id + ",\"f_userID\":" + this.f_userID + ",\"f_firendID\":" + this.f_firendID
				+ ",\"f_name\":\"" + this.f_name + "\",\"f_friendType\":" + this.f_friendType + ",\"f_friendGroups\":\""
				+ this.f_friendGroups + "\",\"f_friendState\":" + this.f_friendState + ",\"f_createTime\":\""
				+ this.f_createTime + "\"}";
	}

	public String toString() {
		return

		"Friend [getF_id()=" + getF_id() + ", getF_userID()=" + getF_userID() + ", getF_firendID()=" + getF_firendID()
				+ ", getF_name()=" + getF_name() + ", getF_friendType()=" + getF_friendType() + ", getF_friendGroups()="
				+ getF_friendGroups() + ", getF_friendState()=" + getF_friendState() + ", getF_createTime()="
				+ getF_createTime() + ", toJson()=" + toJson() + "]";
	}
}
