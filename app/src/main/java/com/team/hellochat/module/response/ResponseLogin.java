package com.team.hellochat.module.response;

import com.team.hellochat.bean.Sex;
import com.team.hellochat.bean.User;
import com.team.hellochat.module.Code;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class ResponseLogin extends Response {

    private User user;


    public ResponseLogin(String json) {
        super(json);
        user = new User();
        if (getCode() >= Code.SUCCESS) {
            user.setId(6);
            user.setUser("沐兰");
            user.setNickname("Y喔卡i");
            user.setAge(22);
            user.setPhone("15528123701");
            user.setEmail("2653922416@foxmail.com");
            user.setAddress("四川成都市新都区北新上锦一期三栋一单元1706");
            user.setAvatar(32);
            user.setSex(Sex.getSex(Sex._SECRECY));
        }
    }
}
