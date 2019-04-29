package com.team.hellochat.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.team.hellochat.interf.ReqCallBack;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpRequestManager {
    private static volatile OkhttpRequestManager mInstance;//单例引用
    private OkHttpClient mOkHttpClient;//
    private Handler okHttpHandler;//全局处理子线程和主线程通信

    public OkhttpRequestManager(Context context) {
        //初始化OKHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, java.util.concurrent.TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //初始化Handler
        okHttpHandler = new Handler(context.getMainLooper());
    }

    /**
     * 获取单例引用
     */
    public static OkhttpRequestManager getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (OkhttpRequestManager.class) {
                if (mInstance == null) {
                    mInstance = new OkhttpRequestManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * get异步请求
     *
     * @param url      接口地址
     * @param callBack 请求数据回调
     * @param <T>      数据泛型
     */
    public <T> Call getAsynHttp(String url, final ReqCallBack<T> callBack) {
        try {
            final Request request = new Request.Builder().url(url).build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("get-http", "访问失败" + e);
                    faileCallBack("访问失败", callBack);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        successCallBakc((T) string, callBack);
                    } else {
                        faileCallBack("服务器错误", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            return null;
        }
    }

    public void canclenet(String url) {
        final Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.cancel();
        mOkHttpClient.dispatcher().cancelAll();
    }

    /**
     * post异步请求
     *
     * @param url       接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求回调
     * @param <T>       数据泛型
     * @return d
     */
    public <T> Call postAsynHttp(String url, HashMap<String, String> paramsMap, final ReqCallBack<T> callBack) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                builder.add(key, paramsMap.get(key));
            }
            RequestBody body = builder.build();
            Request request = new Request.Builder().url(url).post(body).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    faileCallBack("访问失败", callBack);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        successCallBakc((T) string, callBack);
                    } else {
                        faileCallBack("服务器错误", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * post异步请求
     *
     * @param url      接口地址
     * @param data     请求参数
     * @param callBack 请求回调
     * @param <T>      数据泛型
     * @return d
     */
    public <T> Call postAsynHttp(String url, String data, final ReqCallBack<T> callBack) {
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), data);
            Request request = new Request.Builder().url(url).post(body).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("post-http", "访问失败" + e);
                    faileCallBack("访问失败", callBack);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        successCallBakc((T) string, callBack);
                    } else {
                        faileCallBack("服务器错误", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            return null;
        }
    }

    private <T> void successCallBakc(final T result, final ReqCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqSuccess(result);
                }
            }
        });
    }

    private <T> void faileCallBack(final String errorMsg, final ReqCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqFailed(errorMsg);
                }
            }
        });
    }
}
