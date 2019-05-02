package com.team.hellochat.net.service.Impl;

import com.team.hellochat.net.dao.GroupDao;
import com.team.hellochat.net.dao.impl.GroupDaoImpl;
import com.team.hellochat.net.entity.Group;
import com.team.hellochat.net.service.GroupService;

public class GroupServiceImpl implements GroupService {
    private GroupDao groupdao = new GroupDaoImpl();

    public int insertGroup(Group group) {
        return this.groupdao.insertGroup(group);
    }

    public int deleteGroup(int c_id) {
        return this.groupdao.deleteGroup(c_id);
    }

    public int updateGroup(int c_id, int c_num) {
        return this.groupdao.updateGroup(c_id, c_num);
    }

    public Group selectGroup(int c_id) {
        return this.groupdao.selectGroup(c_id);
    }

    public static void main(String[] args) {
        Group g = new Group();
        g.setC_describle("第一个群");
        g.setC_name("聊天群");
        g.setU_id(1);
        new GroupServiceImpl().insertGroup(g);
    }
}
