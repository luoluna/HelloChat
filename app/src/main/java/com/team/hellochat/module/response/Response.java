package com.team.hellochat.module.response;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class Response {
    private int code = 0;
    private String msg;
    private JSONObject data;

    public Response(String json) {
        Log.v("json-string", json);
        try {
            JSONObject js = new JSONObject(json);
            code = js.optInt("code");
            msg = js.optString("msg");
            data = js.optJSONObject("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public JSONObject getData() {
        return data;
    }
}
