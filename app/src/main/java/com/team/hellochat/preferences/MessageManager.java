package com.team.hellochat.preferences;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.hellochat.app.App;
import com.team.hellochat.bean.ChatMessage;
import com.team.hellochat.utils.PreferenceUtil;

import java.util.List;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class MessageManager {
    private static MessageManager instance;

    private List<ChatMessage> list;

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

    public static MessageManager getInstance(Context context, String name) throws JsonProcessingException {
        if (instance == null) {
            synchronized (MessageManager.class) {
                if (instance == null) {
                    instance = new MessageManager();
                }
            }
        }
//        instance.list = new ObjectMapper().readValues(new PreferenceUtil(context, name).getString(App.SharedLabel.PASSWORD));
        return instance;
    }

}
