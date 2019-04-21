package com.team.hellochat.manager;

import android.content.Context;

import com.team.hellochat.app.App;
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
    private String name;

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

    public static MessageManager getInstance(Context context, String name) {
        if (instance == null) {
            synchronized (MessageManager.class) {
                if (instance == null) {
                    instance = new MessageManager();
                }
            }
        }
        instance.name = name;
        instance.messages = JsonUtil.jsonToObject(new PreferenceUtil(context, instance.name).getString(App.SharedLabel.MESSAGE_INFO), new ChatMessage());
        return instance;
    }

    public void clearMessage() {
        MessageManager.instance = null;
    }

    public void addMessageInfo(Context context, String name, MessageInfo messageInfo) {
        if (messages == null) {
            messages = new ChatMessage();
        }
        messages.getList().add(messageInfo);
        save(context, name);
    }

    public void setMessages(Context context, String name, ChatMessage messages) {
        this.messages = messages;
        save(context, name);
    }

    private void save(Context context, String name) {
        new PreferenceUtil(context, name).save(App.SharedLabel.MESSAGE_INFO, JsonUtil.object2Json(messages));
    }

    public String getName() {
        return name;
    }

    public ChatMessage getMessages() {
        return messages;
    }
}
