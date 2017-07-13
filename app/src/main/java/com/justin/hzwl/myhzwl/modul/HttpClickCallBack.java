package com.justin.hzwl.myhzwl.modul;

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
}

