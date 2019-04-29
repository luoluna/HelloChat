package com.team.hellochat.interf;

public interface ReqCallBack<T> {
    /**
     * 相应成功
     *
     * @param result
     */
    void onReqSuccess(T result);

    /**
     * 相应失败
     *
     * @param errorMsg
     */
    void onReqFailed(String errorMsg);
}
