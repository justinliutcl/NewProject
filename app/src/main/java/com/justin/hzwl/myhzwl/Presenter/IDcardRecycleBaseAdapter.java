package com.justin.hzwl.myhzwl.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.hzwl.myhzwl.R;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */

public class IDcardRecycleBaseAdapter extends RecyclerView.Adapter<IDcardRecycleBaseAdapter.ViewHolder> {
    Context context;
    List<EidData>list;

    public IDcardRecycleBaseAdapter(Context context, List<EidData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_idcard_approve,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
