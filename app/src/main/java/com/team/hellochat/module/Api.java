package com.team.hellochat.module;

import android.content.Context;

import com.team.hellochat.R;
import com.team.hellochat.interf.ReqCallBack;
import com.team.hellochat.module.response.ResponseLogin;
import com.team.hellochat.utils.OkhttpRequestManager;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class Api {

    private static Api instance;

    public static Api getInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                instance = new Api();
            }
        }
        return instance;
    }

    public <T> Call get(Context context, String path, final ApiCallBack<T> callBack) {
        String url = "" + path;
        return OkhttpRequestManager.getmInstance(context).getAsynHttp(url,
                new ReqCallBack<Object>() {
                    @Override
                    public void onReqSuccess(Object result) {
                        callBack.onReqSuccess(context, (T) result);
                    }

                    @Override
                    public void onReqFailed(String errorMsg) {
                        //当前异常
                    }
                });
    }

    public <T> Call post(Context context, String path, String json, final ApiCallBack<T> callBack) {
        String url = "" + path;
        return OkhttpRequestManager.getmInstance(context).postAsynHttp(url, json,
                new ReqCallBack<Object>() {
                    @Override
                    public void onReqSuccess(Object result) {
                        callBack.onReqSuccess(context, (T) result);
                    }

                    @Override
                    public void onReqFailed(String errorMsg) {
                        //当前异常
                    }
                });
    }


    public void login(Context context, String name, String pass, ApiCallBack<ResponseLogin> callBack) {
        String json;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login_name", name);
            jsonObject.put("pass", pass);
            json = jsonObject.toString();
        } catch (JSONException e) {
            callBack.onReqFailed(context.getString(R.string.jsonError));
            e.printStackTrace();
            return;
        }

        post(context, "", json, new ApiCallBack<ResponseLogin>() {
            @Override
            public void onReqSuccess(Context context, ResponseLogin result) {
                callBack.onReqSuccess(context, result);
            }

            @Override
            public void onReqFailed(String errorMsg) {
                callBack.onReqFailed(errorMsg);
            }
        });
    }
}
