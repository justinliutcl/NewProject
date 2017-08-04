package com.justin.hzwl.myhzwl.activity.mainView.searchView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;

import util.AppUtil;
import util.DimensionUtils;
import util.NormalDialog;

public class SearchSuccessActivity extends BaseActivity implements View.OnClickListener {
    Bitmap qrBitmap;
    public static final int FRAM_FACE = 1;
    TextView safe_city, safe_people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_success);
    }

    @Override
    public void init() {
        safe_city = (TextView) findViewById(R.id.safe_city);
        safe_people = (TextView) findViewById(R.id.safe_people);

        String text_city = "<font>打败了</font><big>80%</big><font>附近的人</font>";
        String text_people = "<font>您的账户安全指数</font><big>90%</big>";
        safe_city.setText(Html.fromHtml(text_city));
        safe_people.setText(Html.fromHtml(text_people));
        if (getIntent().getIntExtra("type", 0) == FRAM_FACE) {
            showfaceDialog();
        } else {
            showEidDialog();
        }

//        LinearLayout layout = mBackView.getTitleRightWrapper();
//        ImageView imageView = (ImageView) layout.findViewById(R.id.title_back);
//        imageView.setImageResource(R.drawable.icon_share);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void setListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    private void showfaceDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_face_value, null);
        ImageView close = (ImageView) view.findViewById(R.id.close);
        ImageView save = (ImageView) view.findViewById(R.id.save_iv);
        ImageView reset = (ImageView) view.findViewById(R.id.reset_iv);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchSuccessActivity.this, "保存成果", Toast.LENGTH_LONG).show();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToFinish(SearchSuccessActivity.this, FaceActivity.class);
            }
        });
        final AlertDialog dialog = NormalDialog.showSimpleDialog(this, view);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showEidDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_qr_code, null);
        ImageView btn = (ImageView) view.findViewById(R.id.login);
        ImageView close = (ImageView) view.findViewById(R.id.close);

        final AlertDialog dialog = NormalDialog.showSimpleDialog(this, view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrBitmap = AppUtil.generateBitmap("www.baidu.com", DimensionUtils.dpToPx(90), DimensionUtils.dpToPx(90));
                showQRdialog();
                dialog.dismiss();

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void showQRdialog() {
        if (qrBitmap != null) {
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_qr_bitmap_show, null);
            ImageView image = (ImageView) view.findViewById(R.id.qrcode_iv);
            ImageView close = (ImageView) view.findViewById(R.id.close_btn);
            ImageView btn = (ImageView) view.findViewById(R.id.next_btn);
            image.setImageBitmap(qrBitmap);
            final AlertDialog dialog = NormalDialog.showSimpleDialog(this, view);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
        }
    }

    public static void jump(Activity context, int type) {
        Intent intent = new Intent(context, SearchSuccessActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
        context.finish();
    }
}
