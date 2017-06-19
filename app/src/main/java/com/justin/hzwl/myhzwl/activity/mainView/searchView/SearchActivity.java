package com.justin.hzwl.myhzwl.activity.mainView.searchView;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;

public class SearchActivity extends BaseActivity{
    ImageView phone;
    ValueAnimator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        phone = (ImageView) findViewById(R.id.phone);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int right = phone.getWidth();
                v = ValueAnimator.ofInt(0, right);
                v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int p = (int) animation.getAnimatedValue();
                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) phone.getLayoutParams();
                        layoutParams.setMargins(0, 0, p, layoutParams.bottomMargin);
                        phone.setLayoutParams(layoutParams);

                    }
                });
                v.setDuration(1700);
                v.setInterpolator(new AccelerateDecelerateInterpolator());
                v.setRepeatCount(ValueAnimator.INFINITE);
                v.start();
            }
        }, 300);
    }

    @Override
    public void init() {
        mBackView.getTitleRightWrapper().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(SearchActivity.this, HelpActivity.class);
            }
        });
    }

    @Override
    public void setListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        v.cancel();
    }
}
