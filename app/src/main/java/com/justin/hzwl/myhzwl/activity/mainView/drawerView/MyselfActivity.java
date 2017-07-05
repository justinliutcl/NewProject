package com.justin.hzwl.myhzwl.activity.mainView.drawerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.justin.hzwl.myhzwl.R;

import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.mainView.loginView.ResetcodeActivity;

public class MyselfActivity extends BaseActivity{
    View back_iv;
    LinearLayout resetCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);
    }

    @Override
    public void init() {
        back_iv = findViewById(R.id.back_iv);
        resetCode = (LinearLayout) findViewById(R.id.reset_code);

    }

    @Override
    public void setListener() {
        back_iv.setOnClickListener(this);
        resetCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.reset_code:
                jumpTo(this, ResetcodeActivity.class);
                break;
        }
    }
}
