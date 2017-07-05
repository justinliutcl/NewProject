package com.justin.hzwl.myhzwl.modul;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.hzwl.myhzwl.R;

/**
 * Created by ASUS on 2017/6/14.
 */

public class ConsumeDesFragment extends Fragment {
    View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_recharge, container, false);
        return root;
    }
}
