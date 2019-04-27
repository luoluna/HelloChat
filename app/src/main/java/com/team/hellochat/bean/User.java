package com.team.hellochat.bean;

import com.team.hellochat.app.Setting;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class User {
    private int id;
    /**
     * 用户名，不可修改
     */
    private String user = "";
    /**
     * 昵称
     */
    private String nickname = "";
    /**
     * 电话
     */
    private String phone = "";
    /**
     * 电子邮箱
     */
    private String email = "";
    /**
     * 地址
     */
    private String address = "";
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Sex sex = Sex.SECRECY;
    /**
     * 头像id
     */
    private int avatar;
    /**
     * 信用点
     */
    private int creditPoint;

    public int getCreditPoint() {
        return creditPoint;
    }

    public void setCreditPoint(int creditPoint) {
        this.creditPoint = creditPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNickname() {
        return nickname = Setting.isEmpty(nickname) ? user : nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
