package com.justin.hzwl.myhzwl.activity.mainView.drawerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.mainView.blanceView.RuleActivity;
import com.justin.hzwl.myhzwl.modul.BlanceTabAdapter;
import com.justin.hzwl.myhzwl.modul.ConsumeDesFragment;
import com.justin.hzwl.myhzwl.modul.EidFragment;
import com.justin.hzwl.myhzwl.modul.FaceFragment;
import com.justin.hzwl.myhzwl.modul.IDCardFragment;
import com.justin.hzwl.myhzwl.modul.ReChargeDesFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/6/18.
 */

public class HistoryActivity extends BaseActivity {
    TabLayout tab;
    ViewPager viewPager;
    List<Fragment> viewList;
    List<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_des);
    }

    @Override
    public void init() {
        viewList = new ArrayList<>();
        nameList = new ArrayList<>();
        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.pager);
        initData();
    }

    private void initData() {
        EidFragment consumeDesFragment = new EidFragment();
        FaceFragment reChargeDesFragment = new FaceFragment();
        IDCardFragment idCardFragment = new IDCardFragment();
        viewList.add(consumeDesFragment);
        viewList.add(reChargeDesFragment);
        viewList.add(idCardFragment);
        nameList.add("el认证");
        nameList.add("二代身份证认证");
        nameList.add("人脸识别认证");
        BlanceTabAdapter adapter = new BlanceTabAdapter(viewList, nameList, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setupWithViewPager(viewPager);
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public void setListener() {
        mBackView.setTitleRightWrapperClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(HistoryActivity.this, RuleActivity.class);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
