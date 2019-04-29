package com.team.hellochat.module;

import android.content.Context;

public interface ApiCallBack<T> {
    /**
     * 相应成功
     *
     * @param result
     */
    void onReqSuccess(Context context, T result);

    /**
     * 相应失败
     *
     * @param errorMsg
     */
    void onReqFailed(String errorMsg);
}
