package com.justin.hzwl.myhzwl;

import android.app.Dialog;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    ImageView mDrawerMenu,mAlermMenu,mSearch;
    DrawerLayout drawerLayout;
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

    }

    @Override
    public void setListener() {
        mDrawerMenu.setOnClickListener(this);
        mAlermMenu.setOnClickListener(this);
        mSearch.setOnClickListener(this);
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
        }
    }
}
