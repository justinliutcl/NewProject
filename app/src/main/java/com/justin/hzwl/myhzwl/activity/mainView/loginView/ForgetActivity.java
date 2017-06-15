package com.justin.hzwl.myhzwl.activity.mainView.loginView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;

public class ForgetActivity extends BaseActivity {
    ImageView login;
    TextView regist,forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
