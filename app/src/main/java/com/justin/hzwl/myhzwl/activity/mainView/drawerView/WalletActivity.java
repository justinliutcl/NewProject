package com.justin.hzwl.myhzwl.activity.mainView.drawerView;

import android.os.Bundle;
import android.view.View;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.mainView.blanceView.BlancedesActivity;
import com.justin.hzwl.myhzwl.activity.mainView.blanceView.RechangeActivity;

public class WalletActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
    }

    @Override
    public void init() {
        findViewById(R.id.rechange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(WalletActivity.this, RechangeActivity.class);
            }
        });
    }

    @Override
    public void setListener() {
        mBackView.setTitleRightWrapperClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(WalletActivity.this, BlancedesActivity.class);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
