package com.justin.hzwl.myhzwl.activity.mainView.loginView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;

import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.MainActivity;

public class LoginActivity extends BaseActivity{
    ImageView login;
    TextView regist,forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void init() {
        login = (ImageView) findViewById(R.id.login);
        regist = (TextView) findViewById(R.id.regist);
        forget = (TextView) findViewById(R.id.forget);
    }

    @Override
    public void setListener() {
        login.setOnClickListener(this);
        regist.setOnClickListener(this);
        forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                jumpToFinish(this,MainActivity.class);
                break;
            case R.id.regist:
                jumpTo(this,RegistActivity.class);
                break;
            case R.id.forget:
                jumpTo(this,ForgetActivity.class);
                break;
        }
    }
}
