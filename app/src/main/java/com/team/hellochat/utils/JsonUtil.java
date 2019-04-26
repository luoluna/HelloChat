package com.team.hellochat.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.hellochat.bean.ChatMessage;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class JsonUtil {

    public static String object2Json(Object o) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T jsonToObject(String json, T t) {
        Class aClass = t.getClass();
        try {
            t = (T) new ObjectMapper().readValue(json, aClass);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static void main(String[] a) {
        String json="{\"content\":null,\"file\":\"A6F9B2AE23EDF0C5AAC2F057A60AF806\",\"id\":0,\"list\":[{\"avatar\":20,\"information\":\"请问哦\",\"nickname\":null,\"time\":1556247552558,\"type\":\"TEXT\",\"uid\":0,\"read\":true}],\"lastMessage\":{\"avatar\":20,\"information\":\"请问哦\",\"nickname\":null,\"time\":1556247552558,\"type\":\"TEXT\",\"uid\":0,\"read\":true},\"lastTime\":1556247552558,\"noReadCount\":0}";
        ChatMessage message= jsonToObject(json,new ChatMessage());
        System.out.print(message.getFile());
    }
}
