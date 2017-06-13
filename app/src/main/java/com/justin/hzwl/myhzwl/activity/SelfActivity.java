package com.justin.hzwl.myhzwl.activity;

import android.os.Bundle;
import android.view.View;

import com.justin.hzwl.myhzwl.R;

public class SelfActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
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

                break;
        }
    }
}
