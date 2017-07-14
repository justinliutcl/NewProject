package com.justin.hzwl.myhzwl.modul.callbackInterface;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by ASUS on 2017/7/13.
 */

public  class HttpClickCallBack {
    public interface ClickSuccessCallBack {
        void onSuccess(String json);
    }
    public interface FailureCallBack {
        void onFailure(Call call, IOException e);
    }
    private ClickSuccessCallBack successCall;

    private FailureCallBack failureCall;

    public HttpClickCallBack(ClickSuccessCallBack successCall, FailureCallBack failureCall) {
        this.successCall = successCall;
        this.failureCall = failureCall;
    }

    public HttpClickCallBack(FailureCallBack failureCall) {

        this.failureCall = failureCall;
    }

    public HttpClickCallBack(ClickSuccessCallBack successCall) {

        this.successCall = successCall;
    }

    public ClickSuccessCallBack getSuccessCall() {
        return successCall;
    }

    public void setSuccessCall(ClickSuccessCallBack successCall) {
        this.successCall = successCall;
    }

    public FailureCallBack getFailureCall() {
        return failureCall;
    }

    public void setFailureCall(FailureCallBack failureCall) {
        this.failureCall = failureCall;
    }
}

