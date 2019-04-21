package com.team.hellochat.module;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class Code {
    public static final int SUCCESS = 0;

    public static String getMsg(int code) {
        String msg = "";
        switch (code) {
            case 0:
                msg = "获取成功";
                break;
        }
        return msg;
    }
}
