package com.justin.hzwl.myhzwl.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.mainView.drawerView.AboutActivity;
import com.justin.hzwl.myhzwl.activity.mainView.drawerView.WalletActivity;

import util.HttpUtil;

public class MainActivity extends BaseActivity {
    ImageView mDrawerMenu,mAlermMenu,mSearch;
    DrawerLayout drawerLayout;
    LinearLayout feedback,qb,history,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {
        mDrawerMenu = (ImageView) findViewById(R.id.drawer_menu_iv);
        mAlermMenu = (ImageView) findViewById(R.id.alerm_menu_iv);
        mSearch = (ImageView) findViewById(R.id.sarch_iv);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        feedback = (LinearLayout) findViewById(R.id.feedback);
        qb = (LinearLayout) findViewById(R.id.qb_ll);
        history = (LinearLayout) findViewById(R.id.history_ll);
        about = (LinearLayout) findViewById(R.id.about_ll);
    }

    @Override
    public void setListener() {
        mDrawerMenu.setOnClickListener(this);
        mAlermMenu.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        feedback.setOnClickListener(this);
        qb.setOnClickListener(this);
        history.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawer_menu_iv:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.alerm_menu_iv:

                break;
            case R.id.sarch_iv:
                HttpUtil.getHttpUtilInstance().getExecute("http://www.baidu.com");
                break;
            case R.id.qb_ll:
                jumpTo(this,WalletActivity.class);
                break;
            case R.id.history_ll:
                jumpTo(this,FeedbackActivity.class);
                break;
            case R.id.about_ll:
                jumpTo(this,AboutActivity.class);
                break;
            case R.id.feedback:
                jumpTo(this,FeedbackActivity.class);
                break;
        }
    }

}
