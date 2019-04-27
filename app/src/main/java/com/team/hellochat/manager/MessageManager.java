package com.team.hellochat.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.team.hellochat.app.App;
import com.team.hellochat.app.Setting;
import com.team.hellochat.bean.ChatMessage;
import com.team.hellochat.bean.MessageInfo;
import com.team.hellochat.utils.JsonUtil;
import com.team.hellochat.utils.PreferenceUtil;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class MessageManager {
    private static MessageManager instance;

    private ChatMessage messages;
    private String file;
    private int withId;

    public static MessageManager getInstance() {
        if (instance == null) {
            synchronized (MessageManager.class) {
                if (instance == null) {
                    instance = new MessageManager();
                }
            }
        }
        return instance;
    }

    public static MessageManager getInstance(Context context, int withId) {
        if (instance == null) {
            synchronized (MessageManager.class) {
                if (instance == null) {
                    instance = new MessageManager();
                }
            }
        }
        instance.withId = withId;
        instance.file = Setting.getChatRoomFile(instance.withId);
        instance.messages = JsonUtil.jsonToObject(new PreferenceUtil(context, instance.file).getString(App.SharedLabel.MESSAGE_INFO), new ChatMessage());
        instance.messages.setFile(instance.file);
        return instance;
    }

    public static MessageManager getInstance(Context context, String file) {
        if (instance == null) {
            synchronized (MessageManager.class) {
                if (instance == null) {
                    instance = new MessageManager();
                }
            }
        }
        instance.file = file;
        instance.messages = JsonUtil.jsonToObject(new PreferenceUtil(context, instance.file).getString(App.SharedLabel.MESSAGE_INFO), new ChatMessage());
        instance.messages.setFile(instance.file);
        return instance;
    }

    public void clearMessage() {
        MessageManager.instance = new MessageManager();
    }

    public void addMessageInfo(Context context, String name, MessageInfo messageInfo) {
        MessageManager.getInstance().clearMessage();
        messages = MessageManager.getInstance(context, name).getMessages();
        this.file = name;
        messages.setFile(name);
        messages.getList().add(0,messageInfo);
        save(context);
    }

    public void setMessages(Context context, String name, ChatMessage messages) {
        this.file = name;
        this.messages = messages;
        this.messages.setFile(name);
        save(context);
    }

    private void save(Context context) {
        SharedPreferences.Editor editor = new PreferenceUtil(context, file).getEditor();
        editor.putString(App.SharedLabel.MESSAGE_INFO, JsonUtil.object2Json(messages));
        editor.apply();
    }

    public String getFile() {
        return file;
    }

    public ChatMessage getMessages() {
        return messages;
    }
}
