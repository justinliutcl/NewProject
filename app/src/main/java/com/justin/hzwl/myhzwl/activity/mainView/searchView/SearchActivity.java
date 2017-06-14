package com.justin.hzwl.myhzwl.activity.mainView.searchView;

import android.os.Bundle;
import android.view.View;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;

public class SearchActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
