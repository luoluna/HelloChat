package com.team.hellochat.preferences;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.hellochat.app.App;
import com.team.hellochat.bean.ChatMessage;
import com.team.hellochat.utils.PreferenceUtil;

import java.io.IOException;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class MessageManager {
    private static MessageManager instance;

    private ChatMessage messages;

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
        try {
            instance.messages = new ObjectMapper().readValue(new PreferenceUtil(context, name).getString(App.SharedLabel.PASSWORD), ChatMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
