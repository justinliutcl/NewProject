package com.justin.hzwl.myhzwl.modul;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.Presenter.BlanceRecycleBaseAdapter;
import com.justin.hzwl.myhzwl.Presenter.EidData;
import com.justin.hzwl.myhzwl.Presenter.EidRecycleBaseAdapter;
import com.justin.hzwl.myhzwl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/6/14.
 */

public class ConsumeDesFragment extends Fragment {
    View root;
    LinearLayout empty_layout;
    TextView noMore_tv;
    BlanceRecycleBaseAdapter adapter;
    List<EidData> list;
    RecyclerView dataList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_recharge, container, false);
        empty_layout = (LinearLayout) root.findViewById(R.id.empty_layout);
        noMore_tv = (TextView) root.findViewById(R.id.nomore_tv);
        dataList = (RecyclerView) root.findViewById(R.id.datalist);
        list = new ArrayList<>();
        list.add(new EidData());
        list.add(new EidData());
        list.add(new EidData());
        list.add(new EidData());
        list.add(new EidData());
        list.add(new EidData());
        list.add(new EidData());
        list.add(new EidData());
        adapter = new BlanceRecycleBaseAdapter(getActivity(),list);
        dataList.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataList.setAdapter(adapter);

        return root;
    }
}
