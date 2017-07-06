package com.justin.hzwl.myhzwl.activity.mainView.searchView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;

import util.AppUtil;
import util.DimensionUtils;
import util.NormalDialog;

public class SearchSuccessActivity extends BaseActivity implements View.OnClickListener{
    Bitmap qrBitmap;
    public static final int FRAM_FACE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_success);
    }

    @Override
    public void init() {
        if(getIntent().getIntExtra("type",0)==FRAM_FACE){
            showfaceDialog();
        }else{
            showEidDialog();
        }

    }

    @Override
    public void setListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    private void showfaceDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_face_value,null);
        ImageView close = (ImageView) view.findViewById(R.id.close);
        ImageView save = (ImageView) view.findViewById(R.id.save_iv);
        ImageView reset = (ImageView) view.findViewById(R.id.reset_iv);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchSuccessActivity.this,"保存成果",Toast.LENGTH_LONG).show();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToFinish(SearchSuccessActivity.this,FaceActivity.class);
            }
        });
        final AlertDialog dialog  = NormalDialog.showSimpleDialog(this,view);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showEidDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_qr_code,null);
        ImageView btn = (ImageView) view.findViewById(R.id.login);
        ImageView close = (ImageView) view.findViewById(R.id.close);

        final AlertDialog dialog  = NormalDialog.showSimpleDialog(this,view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrBitmap = AppUtil.generateBitmap("www.baidu.com", DimensionUtils.dpToPx(90),DimensionUtils.dpToPx(90));
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
    public void showQRdialog(){
        if(qrBitmap!=null){
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_qr_bitmap_show,null);
            ImageView image = (ImageView) view.findViewById(R.id.qrcode_iv);
            ImageView close = (ImageView) view.findViewById(R.id.close_btn);
            ImageView btn = (ImageView) view.findViewById(R.id.next_btn);
            image.setImageBitmap(qrBitmap);
            final AlertDialog dialog  = NormalDialog.showSimpleDialog(this,view);
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

    public static void jump(Activity context,int type){
        Intent intent = new Intent(context,SearchSuccessActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
        context.finish();
    }
}
