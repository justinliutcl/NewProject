package com.justin.hzwl.myhzwl.activity.mainView.loginView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.MainActivity;

public class AccreditActivity extends BaseActivity{
    ImageView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accredit);
    }

    @Override
    public void init() {
        login = (ImageView) findViewById(R.id.login);
    }

    @Override
    public void setListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                jumpToFinish(this,MainActivity.class);
                break;
        }
    }
}
