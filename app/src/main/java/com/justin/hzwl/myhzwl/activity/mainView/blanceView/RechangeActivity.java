package com.justin.hzwl.myhzwl.activity.mainView.blanceView;

import android.content.ContentValues;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.modul.alipay2.AliPayUse;

import util.ContentKey;
import util.EidHttpUtil;

/**
 * Created by ASUS on 2017/6/14.
 */

public class RechangeActivity extends BaseActivity {
    RelativeLayout one, two, three, four;
    TextView one_text, two_text, three_text, four_text;
    TextView one_text2, two_text2, three_text2, four_text2;
    LinearLayout wechat_layout, alipay_layout;
    ImageView wechat_iv, alipay_iv, submit_btn;
    int last = 0;
    int current = 0;
    int mPayType = 0;
    double mMoney = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechange);
    }

    @Override
    public void init() {
        one = (RelativeLayout) findViewById(R.id.one_layout);
        two = (RelativeLayout) findViewById(R.id.two_layout);
        three = (RelativeLayout) findViewById(R.id.three_layout);
        four = (RelativeLayout) findViewById(R.id.four_layout);
        wechat_layout = (LinearLayout) findViewById(R.id.wechat_layout);
        alipay_layout = (LinearLayout) findViewById(R.id.alipay_layout);
        wechat_iv = (ImageView) findViewById(R.id.wechat_iv);
        alipay_iv = (ImageView) findViewById(R.id.alipay_iv);
        submit_btn = (ImageView) findViewById(R.id.submit_btn);
        one_text = (TextView) findViewById(R.id.one_text);
        one_text2 = (TextView) findViewById(R.id.one_text2);
        two_text = (TextView) findViewById(R.id.two_text);
        two_text2 = (TextView) findViewById(R.id.two_text2);
        three_text = (TextView) findViewById(R.id.three_text);
        three_text2 = (TextView) findViewById(R.id.three_text2);
        four_text = (TextView) findViewById(R.id.four_text);

    }

    @Override
    public void setListener() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        wechat_layout.setOnClickListener(this);
        alipay_layout.setOnClickListener(this);
        submit_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_layout:
                current = 0;
                onMoneyClick();
                break;
            case R.id.two_layout:
                current = 1;
                onMoneyClick();
                break;
            case R.id.submit_btn:
                if (mPayType == 0) {
                    AliPayUse pay = new AliPayUse(EidHttpUtil.createUUID(), RechangeActivity.this, "余额充值", mMoney, "充值", ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
                        @Override
                        public void SuccessCallBack(String mes) {

                            show("支付成功");

                        }

                        @Override
                        public void failCallBack(String mes) {
                            show("支付失败");
                        }
                    });
                    pay.pay();
                } else {
                    show("暂不支持微信");
                }
                break;
            case R.id.three_layout:
                current = 2;
                onMoneyClick();
                break;
            case R.id.four_layout:
                current = 3;
                onMoneyClick();
                break;
            case R.id.alipay_layout:
                alipay_iv.setBackgroundResource(R.drawable.icon_select);
                wechat_iv.setBackgroundResource(R.drawable.icon_select_nor);
                mPayType = 0;
                break;
            case R.id.wechat_layout:
                alipay_iv.setBackgroundResource(R.drawable.icon_select_nor);
                wechat_iv.setBackgroundResource(R.drawable.icon_select);
                mPayType = 1;
                break;
        }

    }

    public void onMoneyClick() {
        if (last != current) {
            cancleSelect(last);
            setSelect(current);
            last = current;
        }
    }

    public void setSelect(int index) {
        switch (index) {
            case 0:
                one.setBackgroundResource(R.drawable.feedback_select_item_background);
                one_text.setTextColor(getResources().getColor(R.color.white));
                one_text2.setTextColor(getResources().getColor(R.color.white));
                mMoney = 500;
                break;
            case 1:
                two.setBackgroundResource(R.drawable.feedback_select_item_background);
                two_text.setTextColor(getResources().getColor(R.color.white));
                two_text2.setTextColor(getResources().getColor(R.color.white));
                mMoney = 100;
                break;
            case 2:
                three.setBackgroundResource(R.drawable.feedback_select_item_background);
                three_text.setTextColor(getResources().getColor(R.color.white));
                three_text2.setTextColor(getResources().getColor(R.color.white));
                mMoney = 50;
                break;
            case 3:
                four.setBackgroundResource(R.drawable.feedback_select_item_background);
                four_text.setTextColor(getResources().getColor(R.color.white));
                mMoney = 10;
                break;
        }
    }

    public void cancleSelect(int index) {
        switch (index) {
            case 0:
                one.setBackgroundResource(R.drawable.feedback_item_background);
                one_text.setTextColor(getResources().getColor(R.color.black));
                one_text2.setTextColor(getResources().getColor(R.color.purple_text));
                break;
            case 1:
                two.setBackgroundResource(R.drawable.feedback_item_background);
                two_text.setTextColor(getResources().getColor(R.color.black));
                two_text2.setTextColor(getResources().getColor(R.color.purple_text));
                break;
            case 2:
                three.setBackgroundResource(R.drawable.feedback_item_background);
                three_text.setTextColor(getResources().getColor(R.color.black));
                three_text2.setTextColor(getResources().getColor(R.color.purple_text));
                break;
            case 3:
                four.setBackgroundResource(R.drawable.feedback_item_background);
                four_text.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }
}
