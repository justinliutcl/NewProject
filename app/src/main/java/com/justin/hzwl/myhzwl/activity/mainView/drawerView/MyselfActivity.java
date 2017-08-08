package com.justin.hzwl.myhzwl.activity.mainView.drawerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.mainView.loginView.LoginActivity;
import com.justin.hzwl.myhzwl.activity.mainView.loginView.ResetcodeActivity;

import util.SharedUtil;

public class MyselfActivity extends BaseActivity {
    View back_iv;
    View breakUp;
    TextView phoneNum;
    LinearLayout resetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);
    }

    @Override
    public void init() {
        back_iv = findViewById(R.id.back_iv);
        breakUp = findViewById(R.id.breakUp);
        resetCode = (LinearLayout) findViewById(R.id.reset_code);
        phoneNum = (TextView) findViewById(R.id.phoneNum);
        phoneNum.setText(SharedUtil.getInstance(this).getPhoneNum());
    }

    @Override
    public void setListener() {
        back_iv.setOnClickListener(this);
        resetCode.setOnClickListener(this);
        breakUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.breakUp:
                SharedUtil.getInstance(this).setUserId(null);
                SharedUtil.getInstance(this).setPhoneNum(null);
                jumpToFinish(this, LoginActivity.class);
                break;
            case R.id.reset_code:
                jumpTo(this, ResetcodeActivity.class);
                break;
        }
    }
}
