package com.justin.hzwl.myhzwl.activity.mainView.searchView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.mainView.searchView.surface.CameraSurfaceHolder;
import com.justin.hzwl.myhzwl.modul.callbackInterface.HttpClickCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import okhttp3.Call;
import util.AppUtil;
import util.ContentKey;
import util.EncryptUtil;
import util.HttpUtil;
import util.NormalDialog;

public class FaceActivity extends BaseActivity {
    SurfaceView surfaceView;
    CameraSurfaceHolder mCameraSurfaceHolder = new CameraSurfaceHolder();
    ImageView face_sure_iv;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
    }

    @Override
    public void init() {
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        mCameraSurfaceHolder.setCameraSurfaceHolder(this, surfaceView);
        face_sure_iv = (ImageView) findViewById(R.id.face_sure_iv);
        face_sure_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bit = mCameraSurfaceHolder.getBitmap();
                if (bit != null) {
                    dialog = NormalDialog.showPassDialog(FaceActivity.this);
                    String imageBase = AppUtil.Bitmap2StrByBase64(bit);
                    send(imageBase);
                } else {
                    show("请从新拍摄");
                }
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

    public void send(String imageBase) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject();
            jsonObj.put("username", getIntent().getStringExtra("name"));
            jsonObj.put("number", getIntent().getStringExtra("idCard"));
            jsonObj.put("feature_code", imageBase);
            jsonObj.put("appId", "02JR1610081555371526");
            jsonObj.put("biz_sequence_id", UUID.randomUUID().toString());
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = format.format(new Date());
            jsonObj.put("biz_time", date);
            jsonObj.put("sign_type", "3");
            String dataToSign = "appId=" + jsonObj.getString("appId") + "&biz_sequence_id=" +
                    jsonObj.getString("biz_sequence_id") + "&biz_time=" + jsonObj.getString("biz_time")
                    + "&feature_code=" + jsonObj.getString("feature_code") +
                    "&number=" + jsonObj.getString("number") + "&sign_type=3&username=" + jsonObj.getString("username");
            String sign = EncryptUtil.getHmacMd5Str("994EB0BC820FBAF80AC0FF2B41DB9115"
                    , Base64.encodeToString(dataToSign.getBytes(), Base64.NO_WRAP));
            jsonObj.put("sign", sign);
            HttpUtil.getHttpUtilInstance(FaceActivity.this).send(ContentKey.FACE_URL, jsonObj, new HttpClickCallBack(FaceActivity.this, FaceActivity.this));
        } catch (Exception e) {

        }
    }

    @Override
    public void onSuccess(String json) {
        super.onSuccess(json);
        dialog.dismiss();
        try {
            JSONObject object = new JSONObject(json);
            String code = String.valueOf(object.get("status"));
            if (code.equals(ContentKey.faceCode)) {
                SearchSuccessActivity.jump(FaceActivity.this, SearchSuccessActivity.FRAM_FACE);
            } else {
                show("匹配失败" + code);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            show("匹配错误");
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        super.onFailure(call, e);
        show("错误： " + e.getMessage());
    }
}
