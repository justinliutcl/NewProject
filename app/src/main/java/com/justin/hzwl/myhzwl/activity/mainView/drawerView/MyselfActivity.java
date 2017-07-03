package com.justin.hzwl.myhzwl.activity.mainView.drawerView;

import android.os.Bundle;
import android.view.View;

import com.justin.hzwl.myhzwl.R;

import com.justin.hzwl.myhzwl.activity.BaseActivity;

public class MyselfActivity extends BaseActivity{
    View back_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);
    }

    @Override
    public void init() {
        back_iv = findViewById(R.id.back_iv);

    }

    @Override
    public void setListener() {
        back_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
        }
    }
}