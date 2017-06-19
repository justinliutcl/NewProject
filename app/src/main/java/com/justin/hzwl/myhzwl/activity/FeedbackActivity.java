package com.justin.hzwl.myhzwl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;

public class FeedbackActivity extends BaseActivity {
    TextView no_nfc,can_card,more_safe,too_low,submit;
    boolean nfc,card,safe,low;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void init() {
        no_nfc = (TextView) findViewById(R.id.no_nfc);
        can_card = (TextView) findViewById(R.id.can_card);
        more_safe = (TextView) findViewById(R.id.more_safe);
        too_low = (TextView) findViewById(R.id.too_low);
        submit = (TextView) findViewById(R.id.submit_btn);

    }

    @Override
    public void setListener() {
        no_nfc.setOnClickListener(this);
        can_card.setOnClickListener(this);
        more_safe.setOnClickListener(this);
        too_low.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_btn:

                break;
            case R.id.no_nfc:
                if(nfc){
                    no_nfc.setBackgroundResource(R.drawable.feedback_item_background);
                    no_nfc.setTextColor(getResources().getColor(R.color.black));
                }else{
                    no_nfc.setBackgroundResource(R.drawable.feedback_select_item_background);
                    no_nfc.setTextColor(getResources().getColor(R.color.white));
                }
                nfc = !nfc;
                break;
            case R.id.can_card:
                if(card){
                    can_card.setBackgroundResource(R.drawable.feedback_item_background);
                    can_card.setTextColor(getResources().getColor(R.color.black));
                }else{
                    can_card.setBackgroundResource(R.drawable.feedback_select_item_background);
                    can_card.setTextColor(getResources().getColor(R.color.white));
                }
                card = !card;
                break;
            case R.id.more_safe:
                if(safe){
                    more_safe.setBackgroundResource(R.drawable.feedback_item_background);
                    more_safe.setTextColor(getResources().getColor(R.color.black));
                }else{
                    more_safe.setBackgroundResource(R.drawable.feedback_select_item_background);
                    more_safe.setTextColor(getResources().getColor(R.color.white));
                }
                safe = !safe;
                break;
            case R.id.too_low:
                if(low){
                    too_low.setBackgroundResource(R.drawable.feedback_item_background);
                    too_low.setTextColor(getResources().getColor(R.color.black));
                }else{
                    too_low.setBackgroundResource(R.drawable.feedback_select_item_background);
                    too_low.setTextColor(getResources().getColor(R.color.white));
                }
                low = !low;
                break;
        }
    }
}
