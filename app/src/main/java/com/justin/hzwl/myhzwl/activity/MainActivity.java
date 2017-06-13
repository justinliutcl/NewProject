package com.justin.hzwl.myhzwl.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;

public class MainActivity extends BaseActivity {
    ImageView mDrawerMenu,mAlermMenu,mSearch;
    DrawerLayout drawerLayout;
    LinearLayout feedback;
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
    }

    @Override
    public void setListener() {
        mDrawerMenu.setOnClickListener(this);
        mAlermMenu.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        feedback.setOnClickListener(this);
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

                break;
            case R.id.feedback:
                Intent intent = new Intent(this,FeedbackActivity.class);
                startActivity(intent);
                break;
        }
    }
}
