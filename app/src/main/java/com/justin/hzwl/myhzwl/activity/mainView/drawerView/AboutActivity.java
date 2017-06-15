package com.justin.hzwl.myhzwl.activity.mainView.drawerView;

import android.os.Bundle;
import android.view.View;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;

import util.NormalDialog;

public class AboutActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
            case R.id.submit_btn:
                NormalDialog.showSimpleDialog(this,"谢谢你的宝贵意见！");
                break;
        }
    }
}
