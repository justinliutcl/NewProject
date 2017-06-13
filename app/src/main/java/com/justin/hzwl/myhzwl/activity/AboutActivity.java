package com.justin.hzwl.myhzwl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;

import util.NormalDialog;

public class AboutActivity extends BaseActivity {
    TextView submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void init() {
        submit = (TextView) findViewById(R.id.submit_btn);

    }

    @Override
    public void setListener() {
        submit.setOnClickListener(this);
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
