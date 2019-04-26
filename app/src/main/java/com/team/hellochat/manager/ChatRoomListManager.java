package com.team.hellochat.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.team.hellochat.bean.ChatRoomItem;
import com.team.hellochat.utils.JsonUtil;
import com.team.hellochat.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.team.hellochat.app.App.SharedLabel.CHAT_ROOM_LIST;

/**
 * Created by Sweven on 2019/4/24.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoomListManager {
    private static ChatRoomListManager instance;

    private ChatRoomList chatRoomList;

    public static ChatRoomListManager getInstance() {
        if (instance == null) {
            synchronized (ChatRoomListManager.class) {
                instance = new ChatRoomListManager();
            }
        }
        return instance;
    }

    public void sort() {
        Set<ChatRoomItem> set = new HashSet<>();
        Collections.sort(chatRoomList.getList(), new Comparator<ChatRoomItem>() {
            @Override
            public int compare(ChatRoomItem o1, ChatRoomItem o2) {
                return (int) (o1.getLastMessage().getTime() - o2.getLastMessage().getTime());
            }
        });
    }

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
        if (!hasFile(item.getFile())) {
            chatRoomList.getList().add(0, item);
            save(context);
        }
    }

    public boolean hasFile(String file) {
        for (int i = 0; i < getList().size(); i++) {
            if (getList().get(i).getFile().equals(file)) {
                return true;
            }
        }
        return false;
    }

    private void save(Context context) {
        String value = JsonUtil.object2Json(chatRoomList);
        SharedPreferences.Editor editor = new PreferenceUtil(context).getEditor();
        editor.putString(CHAT_ROOM_LIST, value);
        editor.apply();
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
