package com.team.hellochat.app;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class App {
    public static final String SHARED_PREFERENCE = "ChatLuo";
    public static final String L = "12";
    /**
     * 友盟+申请的APPId
     */
    public static final String UM_APP_ID = "5cba84070cafb2b132000337";

    public class SharedLabel {
        public static final String NICKNAME = "nickname";
        public static final String PASSWORD = "password";
        public static final String IS_LOGIN = "isLogin";
        public static final String IS_FIRST = "isFirst";
        public static final String USER_ID = "uid";
        public static final String MESSAGE_INFO = "message_info";
        public static final String LOG_NAME = "log_name";
        public static final String TOKEN = "token";
        public static final String CHAT_ROOM_LIST = "chat_room_list";
    }

    public class IntentLabel {
        public static final String MESSAGE_TYPE = "message_type";
        public static final String MESSAGE_INFO = "message_info";
        public static final String MESSAGE_FILE = "message_file";
        public static final String CHAT_ROOM_TYPE = "chat_room_type";
        public static final String CHAT_ROOM_TITLE = "chat_room_title";
    }
}
