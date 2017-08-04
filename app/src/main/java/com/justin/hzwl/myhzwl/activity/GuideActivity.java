package com.justin.hzwl.myhzwl.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.mainView.loginView.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import util.SharedUtil;

public class GuideActivity extends BaseActivity {
    TextView submit;
    ViewPager pager;
    List<ImageView>list;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        if(SharedUtil.getInstance(this).getFirst()){
            jumpToFinish(GuideActivity.this,LoginActivity.class);
        }


    }

    @Override
    public void init() {
        submit = (TextView) findViewById(R.id.submit);
        pager = (ViewPager) findViewById(R.id.pager);
        list = new ArrayList<>();
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView2 = new ImageView(this);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(this).load(R.drawable.guide_1).into(imageView);
        imageView2.setBackgroundResource(R.drawable.guide_2);
//        Glide.with(this).load(R.drawable.guide_2).into(imageView2);
        list.add(imageView);
        list.add(imageView2);
        adapter = new CustomAdapter();
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    submit.setVisibility(View.VISIBLE);
                }else{
                    submit.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setListener() {
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                jumpToFinish(GuideActivity.this,LoginActivity.class);
                SharedUtil.getInstance(this).setFirst();
                break;
        }
    }
    class CustomAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
