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
    Context context = FaceActivity.this;
    SurfaceView surfaceView;
    CameraSurfaceHolder mCameraSurfaceHolder = new CameraSurfaceHolder();
    ImageView bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        mCameraSurfaceHolder.setCameraSurfaceHolder(context,surfaceView);
        bitmap = (ImageView) findViewById(R.id.bitmap);
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bit = mCameraSurfaceHolder.getBitmap();
                bitmap.setImageBitmap(bit);
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
