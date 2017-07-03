package com.justin.hzwl.myhzwl.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.mainView.NewsActivity;
import com.justin.hzwl.myhzwl.activity.mainView.drawerView.AboutActivity;
import com.justin.hzwl.myhzwl.activity.mainView.drawerView.HistoryActivity;
import com.justin.hzwl.myhzwl.activity.mainView.drawerView.MyselfActivity;
import com.justin.hzwl.myhzwl.activity.mainView.drawerView.WalletActivity;
import com.justin.hzwl.myhzwl.activity.mainView.searchView.SearchActivity;

import util.NfcUtils;
import util.NormalDialog;

public class MainActivity extends BaseActivity {
    ImageView mDrawerMenu,mAlermMenu,mSearch,heard;
    DrawerLayout drawerLayout;
    LinearLayout feedback,qb,history,about,self;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switch (NfcUtils.initNFCData(this)){
            case NfcUtils.NFC_TYPE_NOT:
                NormalDialog.showSimpleDialog(this,"对不起!\n您的手机不支持NFC");
                break;
            case NfcUtils.NFC_TYPE_HAVE:

                break;
            case NfcUtils.NFC_TYPE_CLOSE:

                break;
        }
    }

    @Override
    public void init() {
        mDrawerMenu = (ImageView) findViewById(R.id.drawer_menu_iv);
        mAlermMenu = (ImageView) findViewById(R.id.alerm_menu_iv);
        mSearch = (ImageView) findViewById(R.id.sarch_iv);
        heard = (ImageView) findViewById(R.id.head_iv);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        feedback = (LinearLayout) findViewById(R.id.feedback);
        qb = (LinearLayout) findViewById(R.id.qb_ll);
        history = (LinearLayout) findViewById(R.id.history_ll);
        about = (LinearLayout) findViewById(R.id.about_ll);
        self = (LinearLayout) findViewById(R.id.self_layout);
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
        self.setOnClickListener(this);
        heard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawer_menu_iv:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.alerm_menu_iv:
                jumpTo(this,NewsActivity.class);
                break;
            case R.id.sarch_iv:
                jumpTo(this,SearchActivity.class);
                break;
            case R.id.qb_ll:
                jumpTo(this,WalletActivity.class);
                break;
            case R.id.history_ll:
                jumpTo(this,HistoryActivity.class);
                break;
            case R.id.about_ll:
                jumpTo(this,AboutActivity.class);
                break;
            case R.id.feedback:
                jumpTo(this,FeedbackActivity.class);
                break;
            case R.id.head_iv:
                jumpTo(this,MyselfActivity.class);
                break;
            case R.id.self_layout:
                jumpTo(this,MyselfActivity.class);
                break;
        }
    }

}
