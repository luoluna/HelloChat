package com.team.hellochat.net.entity;

import java.util.Date;

public class User {
	private int u_ID;
	private String u_nickName;
	private String u_passWord;
	private String u_signaTure;
	private byte u_sex;
	private Date u_birthday;
	private String u_telephone;
	private String u_name;
	private String u_email;
	private String u_intro;
	private String u_headPortrait;
	private String u_shengXiao;
	private int u_age;
	private String u_constellation;
	private String u_bloodType;
	private String u_schoolTag;
	private String u_vocation;
	private int u_nationID;
	private int u_provinceID;
	private int u_cityID;
	private int u_userStateID;
	private Date u_createTime;

	public int getU_ID() {
		return this.u_ID;
	}

	public void setU_ID(int u_ID) {
		this.u_ID = u_ID;
	}

	public String getU_nickName() {
		return this.u_nickName;
	}

	public void setU_nickName(String u_nickName) {
		this.u_nickName = u_nickName;
	}

	public String getU_passWord() {
		return this.u_passWord;
	}

	public void setU_passWord(String u_passWord) {
		this.u_passWord = u_passWord;
	}

	public String getU_signaTure() {
		return this.u_signaTure;
	}

	public void setU_signaTure(String u_signaTure) {
		this.u_signaTure = u_signaTure;
	}

	public byte getU_sex() {
		return this.u_sex;
	}

	public void setU_sex(byte u_sex) {
		this.u_sex = u_sex;
	}

	public Date getU_birthday() {
		return this.u_birthday;
	}

	public void setU_birthday(Date u_birthday) {
		this.u_birthday = u_birthday;
	}

	public String getU_telephone() {
		return this.u_telephone;
	}

	public void setU_telephone(String u_telephone) {
		this.u_telephone = u_telephone;
	}

	public String getU_name() {
		return this.u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_email() {
		return this.u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_intro() {
		return this.u_intro;
	}

	public void setU_intro(String u_intro) {
		this.u_intro = u_intro;
	}

	public String getU_headPortrait() {
		return this.u_headPortrait;
	}

	public void setU_headPortrait(String u_headPortrait) {
		this.u_headPortrait = u_headPortrait;
	}

	public String getU_shengXiao() {
		return this.u_shengXiao;
	}

	public void setU_shengXiao(String u_shengXiao) {
		this.u_shengXiao = u_shengXiao;
	}

	public int getU_age() {
		return this.u_age;
	}

	public void setU_age(int u_age) {
		this.u_age = u_age;
	}

	public String getU_constellation() {
		return this.u_constellation;
	}

	public void setU_constellation(String u_constellation) {
		this.u_constellation = u_constellation;
	}

	public String getU_bloodType() {
		return this.u_bloodType;
	}

	public void setU_bloodType(String u_bloodType) {
		this.u_bloodType = u_bloodType;
	}

	public String getU_schoolTag() {
		return this.u_schoolTag;
	}

	public void setU_schoolTag(String u_schoolTag) {
		this.u_schoolTag = u_schoolTag;
	}

	public String getU_vocation() {
		return this.u_vocation;
	}

	public void setU_vocation(String u_vocation) {
		this.u_vocation = u_vocation;
	}

	public int getU_nationID() {
		return this.u_nationID;
	}

	public void setU_nationID(int u_nationID) {
		this.u_nationID = u_nationID;
	}

	public int getU_provinceID() {
		return this.u_provinceID;
	}

	public void setU_provinceID(int u_provinceID) {
		this.u_provinceID = u_provinceID;
	}

	public int getU_cityID() {
		return this.u_cityID;
	}

	public void setU_cityID(int u_cityID) {
		this.u_cityID = u_cityID;
	}

	public int getU_userStateID() {
		return this.u_userStateID;
	}

	public void setU_userStateID(int u_userStateID) {
		this.u_userStateID = u_userStateID;
	}

	public Date getU_createTime() {
		return this.u_createTime;
	}

	public void setU_createTime(Date u_createTime) {
		this.u_createTime = u_createTime;
	}

	public String toJson() {
		return

		"{\"u_ID\":" + this.u_ID + ",\"u_nickName\":\"" + this.u_nickName + "\",\"u_passWord\":\"" + this.u_passWord
				+ "\",\"u_signaTure\":\"" + this.u_signaTure + "\",\"u_sex\":" + this.u_sex + ",\"u_birthday\":\""
				+ this.u_birthday + "\",\"u_telephone\":\"" + this.u_telephone + "\",\"u_name\":\"" + this.u_name
				+ "\",\"u_email\":\"" + this.u_email + "\",\"u_intro\":\"" + this.u_intro + "\",\"u_headPortrait\":\""
				+ this.u_headPortrait + "\",\"u_shengXiao\":\"" + this.u_shengXiao + "\",\"u_age\":\"" + this.u_age
				+ "\",\"u_constellation\":\"" + this.u_constellation + "\",\"u_bloodType\":\"" + this.u_bloodType
				+ "\",\"u_schoolTag\":\"" + this.u_schoolTag + "\",\"u_vocation\":" + this.u_vocation
				+ ",\"u_nationID\":" + this.u_nationID + ",\"u_provinceID\":" + this.u_provinceID + ",\"u_cityID\":"
				+ this.u_cityID + ",\"u_userStateID\":" + this.u_userStateID + "}";
	}

	public String toString() {
		return

		"User [getU_ID()=" + getU_ID() + ", getU_nickName()=" + getU_nickName() + ", getU_passWord()=" + getU_passWord()
				+ ", getU_signaTure()=" + getU_signaTure() + ", getU_sex()=" + getU_sex() + ", getU_birthday()="
				+ getU_birthday() + ", getU_telephone()=" + getU_telephone() + ", getU_name()=" + getU_name()
				+ ", getU_email()=" + getU_email() + ", getU_intro()=" + getU_intro() + ", getU_headPortrait()="
				+ getU_headPortrait() + ", getU_shengXiao()=" + getU_shengXiao() + ", getU_age()=" + getU_age()
				+ ", getU_constellation()=" + getU_constellation() + ", getU_bloodType()=" + getU_bloodType()
				+ ", getU_schoolTag()=" + getU_schoolTag() + ", getU_vocation()=" + getU_vocation()
				+ ", getU_nationID()=" + getU_nationID() + ", getU_provinceID()=" + getU_provinceID()
				+ ", getU_cityID()=" + getU_cityID() + ", getU_userStateID()=" + getU_userStateID() + "]";
	}
}
