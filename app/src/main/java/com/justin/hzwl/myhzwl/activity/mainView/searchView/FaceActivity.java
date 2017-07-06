package com.justin.hzwl.myhzwl.activity.mainView.searchView;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.MainActivity;
import com.justin.hzwl.myhzwl.activity.mainView.searchView.surface.CameraSurfaceHolder;

public class FaceActivity extends BaseActivity{
    SurfaceView surfaceView;
    CameraSurfaceHolder mCameraSurfaceHolder = new CameraSurfaceHolder();
    ImageView bitmap;
    ImageView face_sure_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
    }

    @Override
    public void init() {
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        mCameraSurfaceHolder.setCameraSurfaceHolder(this,surfaceView);
        bitmap = (ImageView) findViewById(R.id.bitmap);
        face_sure_iv = (ImageView) findViewById(R.id.face_sure_iv);
        face_sure_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bitmap bit = mCameraSurfaceHolder.getBitmap();
//                bitmap.setImageBitmap(bit);
                SearchSuccessActivity.jump(FaceActivity.this,SearchSuccessActivity.FRAM_FACE);
            }
        });
    }

    @Override
    public void setListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
