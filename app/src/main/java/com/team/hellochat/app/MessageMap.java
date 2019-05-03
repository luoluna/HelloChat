package com.team.hellochat.app;

import com.team.hellochat.manager.AddressBookManager;
import com.team.hellochat.manager.UserManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sweven on 2019/5/3.
 * Email:sweventears@Foxmail.com
 */
public class MessageMap {
    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("你好", "你好啊");
        map.put("你叫什么", "我是" + "李天一" + "，你呢");
        map.put("我是" + UserManager.getInstance().getUser().getNickname(), "很高兴认识你");
        map.put("hello", "hi!");
        map.put("额", "莫名其妙");
        map.put("123456", "78910J");
        map.put("1", "我还2呢");
        map.put("？？", "咋呢？");
        map.put("??", "咋呢？");
        map.put("烦", "没有什么好烦的");
    }

    public static String getBack(String message) {
        String msg = "你说什么啊？我听不懂";
        try {
            msg = map.get(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
