package com.team.hellochat.manager;

import android.content.Context;

import com.team.hellochat.bean.ChatRoomItem;
import com.team.hellochat.utils.JsonUtil;
import com.team.hellochat.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import static com.team.hellochat.app.App.SharedLabel.CHAT_ROOM_LIST;

/**
 * Created by Sweven on 2019/4/24.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoomListManager {
    private static ChatRoomListManager instance;

    private ChatRoomList chatRoomList;

    public static ChatRoomListManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ChatRoomListManager.class) {
                instance = new ChatRoomListManager();
            }
        }

        instance.chatRoomList = JsonUtil.jsonToObject(new PreferenceUtil(context).getString(CHAT_ROOM_LIST), new ChatRoomList());
        return instance;
    }


    public void addListItem(Context context, ChatRoomItem item) {
        chatRoomList.getList().add(0, item);
        save(context);
    }

    private void save(Context context) {
        String value = JsonUtil.object2Json(chatRoomList);
        new PreferenceUtil(context).save(CHAT_ROOM_LIST, value);
    }

    public List<ChatRoomItem> getList() {
        return chatRoomList.getList();
    }

    static class ChatRoomList {
        private List<ChatRoomItem> list = new ArrayList<>();

        public List<ChatRoomItem> getList() {
            return list;
        }

        public void setList(List<ChatRoomItem> list) {
            this.list = list;
        }

    }
}
