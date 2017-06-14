package com.justin.hzwl.myhzwl.activity.mainView.blanceView;

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
import com.justin.hzwl.myhzwl.modul.BlanceTabAdapter;
import com.justin.hzwl.myhzwl.modul.ConsumeDesFragment;
import com.justin.hzwl.myhzwl.modul.ReChargeDesFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BlancedesActivity extends BaseActivity {
    TabLayout tab;
    ViewPager viewPager;
    List<Fragment> viewList;
    List<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blance_des);
    }

    @Override
    public void init() {
        viewList = new ArrayList<>();
        nameList = new ArrayList<>();
        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tab, 60, 60);
            }
        });
        initData();
    }

    private void initData() {
        ConsumeDesFragment consumeDesFragment = new ConsumeDesFragment();
        ReChargeDesFragment reChargeDesFragment = new ReChargeDesFragment();
        viewList.add(consumeDesFragment);
        viewList.add(reChargeDesFragment);
        nameList.add("消费明细");
        nameList.add("充值明细");
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
