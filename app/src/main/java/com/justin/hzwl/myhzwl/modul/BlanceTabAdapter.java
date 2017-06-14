package com.justin.hzwl.myhzwl.modul;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by ASUS on 2017/6/14.
 */

public class BlanceTabAdapter extends FragmentPagerAdapter {
    List<Fragment> view;
    List<String> name;

    public BlanceTabAdapter(List<Fragment> view, List<String> name,FragmentManager fm) {
        super(fm);
        this.view = view;
        this.name = name;
    }

    public BlanceTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return view.get(position);
    }

    @Override
    public int getCount() {
        return view.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return name.get(position % name.size());
    }
}
