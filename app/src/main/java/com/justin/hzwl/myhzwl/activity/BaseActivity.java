package com.justin.hzwl.myhzwl.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.modul.callbackInterface.HttpClickCallBack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import views.BackView;

/**
 * Created by ASUS on 2017/6/11.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, HttpClickCallBack.ClickSuccessCallBack, HttpClickCallBack.FailureCallBack {
    public BackView mBackView;
    private boolean isOnCreate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        isOnCreate = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOnCreate) {
            isOnCreate = false;
            mBackView = (BackView) findViewById(R.id.back);
            if (mBackView != null) {
                mBackView.setTitleBackClickedListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BaseActivity.this.finish();
                    }
                });
            }
            init();
            setListener();
        }
    }

    public abstract void init();

    public abstract void setListener();

    public void jumpTo(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }

    public void jumpToFinish(Activity context, Class cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
        context.finish();
    }

    public void jumpToWithValue(Context context, Class cls, HashMap<String, String> map) {
        Intent intent = new Intent(context, cls);
        Set<HashMap.Entry<String, String>> entry = map.entrySet();
        for (HashMap.Entry<String, String> e : entry) {
            intent.putExtra(e.getKey(),e.getValue());
        }
        startActivity(intent);
    }

    public void jumpToFinishWithValue(Context context, Class cls, HashMap<String, String> map) {
        Intent intent = new Intent(context, cls);
        Set<HashMap.Entry<String, String>> entry = map.entrySet();
        for (HashMap.Entry<String, String> e : entry) {
            intent.putExtra(e.getKey(),e.getValue());
        }
        startActivity(intent);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onSuccess(String json) {

    }

    public void show(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}