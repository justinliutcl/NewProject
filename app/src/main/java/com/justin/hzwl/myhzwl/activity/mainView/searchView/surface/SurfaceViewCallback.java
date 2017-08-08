package com.justin.hzwl.myhzwl.activity.mainView.searchView.surface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.ByteArrayOutputStream;

/**
 * Created by zhousong on 2016/9/19.
 * 相机界面SurfaceView的回调类
 */
public final class SurfaceViewCallback implements SurfaceHolder.Callback, Camera.PreviewCallback {

    Context context;
    static final String TAG = "Camera";
    FrontCamera mFrontCamera = new FrontCamera();
    boolean previewing = mFrontCamera.getPreviewing();
    Camera mCamera;
    Bitmap bitmap = null;
    Thread thread;
    int mWidth, mHeight;

    public void setContext(Context context) {
        this.context = context;
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        if (previewing) {
            mCamera.stopPreview();
            Log.i(TAG, "停止预览");
        }

        try {
            mCamera.setPreviewDisplay(arg0);
            mCamera.startPreview();
            mCamera.setPreviewCallback(this);
            Log.i(TAG, "开始预览");
            //调用旋转屏幕时自适应
            //setCameraDisplayOrientation(MainActivity.this, mCurrentCamIndex, mCamera);
        } catch (Exception e) {
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        //初始化前置摄像头
        mFrontCamera.setCamera(mCamera);
        mCamera = mFrontCamera.initCamera();
        mCamera.setPreviewCallback(this);
        //适配竖排固定角度
        Log.i(TAG, "context: " + context.toString());
        Log.i(TAG, "mFrontCamera: " + mFrontCamera.toString());
        Log.i(TAG, "mCamera: " + mCamera.toString());
        FrontCamera.setCameraDisplayOrientation((Activity) context, mFrontCamera.getCurrentCamIndex(), mCamera);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        mFrontCamera.StopCamera(mCamera);
    }

    /**
     * 相机实时数据的回调
     *
     * @param data   相机获取的数据，格式是YUV
     * @param camera 相应相机的对象
     */
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (thread != null) {
            if (thread.getState() == Thread.State.RUNNABLE) {
                return;
            }
        }
        goFaceTask(data);
//        mFaceTask = new FaceTask(data, camera, bitmap);
//        mFaceTask.execute((Void) null);
        //Log.i(TAG, "onPreviewFrame: 启动了Task");

    }

    private void goFaceTask(final byte[] data) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Camera.Parameters parameters = mCamera.getParameters();
                int imageFormat = parameters.getPreviewFormat();
                int w = parameters.getPreviewSize().width;
                int h = parameters.getPreviewSize().height;

                Rect rect = new Rect(0, 0, w, h);
                YuvImage yuvImg = new YuvImage(data, imageFormat, w, h, null);
                try {
                    ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
                    yuvImg.compressToJpeg(rect, 100, outputstream);
                    Bitmap _bitmap = BitmapFactory.decodeByteArray(outputstream.toByteArray(), 0, outputstream.size());
                    if (_bitmap != null) {
                        bitmap = _bitmap;
                    }
                    Log.i(TAG, "onPreviewFrame: rawbitmap:" + bitmap.toString());

                } catch (Exception e) {
                    Log.e(TAG, "onPreviewFrame: 获取相机实时数据失败" + e.getLocalizedMessage());
                }
            }
        });
        thread.start();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setWH(int w, int h) {
        mWidth = w;
        mHeight = h;
    }

}