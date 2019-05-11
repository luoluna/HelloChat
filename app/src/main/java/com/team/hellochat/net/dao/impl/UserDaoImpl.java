package com.team.hellochat.net.dao.impl;

import com.team.hellochat.net.dao.BaseDao;
import com.team.hellochat.net.dao.UserDao;
import com.team.hellochat.net.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao {
    public int insertUser(User user) {
        String sql = "insert into user (u_user,u_nickName, u_telephone, u_passWord)values(?,?,?,?)";
        Object[] obj = {user.getU_nickName(), user.getU_nickName(), user.getU_telephone(), user.getU_passWord()};
        int num = super.update(sql, obj);
        return num;
    }

    public User selectUserById(int u_ID) {
        String sql = "select * from user where u_ID=?";
        Object[] objects = {Integer.valueOf(u_ID)};
        ResultSet rs = super.getResultSet(sql, objects);
        User user = null;
        try {
            if (rs.next()) {
                user = new User();

                user.setU_ID(rs.getInt("u_ID"));
                user.setU_nickName(rs.getString("u_nickName"));
                user.setU_passWord(rs.getString("u_passWord"));
                user.setU_signaTure(rs.getString("u_signaTure"));
                user.setU_sex(rs.getByte("u_sex"));
                user.setU_birthday(rs.getDate("u_birthday"));
                user.setU_telephone(rs.getString("u_telephone"));
                user.setU_name(rs.getString("u_name"));
                user.setU_email(rs.getString("u_email"));
                user.setU_intro(rs.getString("u_intro"));
                user.setU_headPortrait(rs.getString("u_headPortrait"));
                user.setU_shengXiao(rs.getString("u_shengXiao"));
                user.setU_age(rs.getInt("u_age"));
                user.setU_constellation(rs.getString("u_constellation"));
                user.setU_bloodType(rs.getString("u_bloodType"));
                user.setU_schoolTag(rs.getString("u_schoolTag"));
                user.setU_vocation(rs.getString("u_vocation"));
                user.setU_nationID(rs.getInt("u_nationID"));
                user.setU_provinceID(rs.getInt("u_provinceID"));
                user.setU_cityID(rs.getInt("u_cityID"));
                user.setU_userStateID(rs.getInt("u_userStateID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User selectExistByUserPass(String u_User,String pass) {
        String sql = "select * from user where u_user=? and u_passWord=?";
        Object[] objects = {u_User,pass};
        ResultSet rs = super.getResultSet(sql, objects);
        User user = null;
        try {
            if (rs.next()) {
                user = new User();

                user.setU_ID(rs.getInt("u_ID"));
                user.setU_nickName(rs.getString("u_nickName"));
                user.setU_passWord(rs.getString("u_passWord"));
                user.setU_signaTure(rs.getString("u_signaTure"));
                user.setU_sex(rs.getByte("u_sex"));
                user.setU_birthday(rs.getDate("u_birthday"));
                user.setU_telephone(rs.getString("u_telephone"));
                user.setU_name(rs.getString("u_name"));
                user.setU_email(rs.getString("u_email"));
                user.setU_intro(rs.getString("u_intro"));
                user.setU_headPortrait(rs.getString("u_headPortrait"));
                user.setU_shengXiao(rs.getString("u_shengXiao"));
                user.setU_age(rs.getInt("u_age"));
                user.setU_constellation(rs.getString("u_constellation"));
                user.setU_bloodType(rs.getString("u_bloodType"));
                user.setU_schoolTag(rs.getString("u_schoolTag"));
                user.setU_vocation(rs.getString("u_vocation"));
                user.setU_nationID(rs.getInt("u_nationID"));
                user.setU_provinceID(rs.getInt("u_provinceID"));
                user.setU_cityID(rs.getInt("u_cityID"));
                user.setU_userStateID(rs.getInt("u_userStateID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int updateUserById(User user) {
        String sql = "update user set u_nickName = ? , u_signaTure = ?, u_sex = ?, u_birthday = ?, u_name = ?, u_email = ?, u_intro = ?, u_headPortrait = ?, u_shengXiao = ?, u_age = ?, u_constellation = ?, u_bloodType = ?, u_schoolTag = ?, u_vocation = ?, u_nationID = ?, u_provinceID = ?, u_cityID = ? where u_ID = ? ;";
        Object[] obj = {user.getU_nickName(), user.getU_signaTure(), Byte.valueOf(user.getU_sex()),
                user.getU_birthday(), user.getU_name(), user.getU_email(), user.getU_intro(), user.getU_headPortrait(),
                user.getU_shengXiao(), Integer.valueOf(user.getU_age()), user.getU_constellation(),
                user.getU_bloodType(), user.getU_schoolTag(), user.getU_vocation(),
                Integer.valueOf(user.getU_nationID()), Integer.valueOf(user.getU_provinceID()),
                Integer.valueOf(user.getU_cityID()), Integer.valueOf(user.getU_ID())};
        int num = super.update(sql, obj);
        return num;
    }

    public int changePwd(User user) {
        String sql = "update user set u_passWord = ? where u_id = ?";
        System.out.println(user.getU_ID() + "____");
        Object[] obj = {user.getU_passWord(), user.getU_ID()};
        int num = super.update(sql, obj);
        return num;
    }
}
