package com.justin.hzwl.myhzwl.activity.mainView.loginView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.modul.callbackInterface.HttpClickCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import okhttp3.Call;
import util.ContentKey;
import util.EidHttpUtil;
import util.EncryptUtil;
import util.HttpUtil;

public class ForgetActivity extends BaseActivity {
    private static final int TIME_GO = 1;
    ImageView login;
    ImageView pic_iv;
    EditText phone, smsCode, passWord, pic_bitmap;
    int time = 60;
    boolean isDistory;
    TextView send_SMS;
    private boolean isFirst;
    String uuid;
    String phoneNum, sms, psw;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME_GO:
                    time--;
                    if (time > 0 && !isDistory) {
                        send_SMS.setText(String.valueOf(time));
                        handpostTime();
                    } else {
                        send_SMS.setText("发送");
                        send_SMS.setClickable(true);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
    }

    @Override
    public void init() {
        phone = (EditText) findViewById(R.id.phone_nume);
        smsCode = (EditText) findViewById(R.id.sms_code);
        passWord = (EditText) findViewById(R.id.password);
        pic_bitmap = (EditText) findViewById(R.id.pic_bitmap);
        login = (ImageView) findViewById(R.id.login);
        pic_iv = (ImageView) findViewById(R.id.pic_iv);
        send_SMS = (TextView) findViewById(R.id.send_SMS);
    }

    @Override
    public void setListener() {
        login.setOnClickListener(this);
        send_SMS.setOnClickListener(this);
        pic_iv.setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isFirst) {
            isFirst = true;
            getPic();
        }
    }

    public void getPic() {
        HttpUtil.getHttpUtilInstance(this).sendGet(ContentKey.SMS_PIC, new HttpClickCallBack(new HttpClickCallBack.ClickSuccessCallBack() {
            @Override
            public void onSuccess(String json) {
                Log.i("asd", json);
                JSONObject object = null;
                try {
                    object = new JSONObject(json);
                    uuid = (String) object.get("uuid");
                    JSONArray byteArray = object.getJSONArray("picCheckCode");
                    byte[] array = new byte[byteArray.length()];
                    for (int i = 0; i < byteArray.length(); i++) {
                        array[i] = Byte.parseByte(byteArray.get(i).toString());
                    }
                    Bitmap map = BitmapFactory.decodeByteArray(array, 0, array.length);
                    pic_iv.setImageBitmap(map);
//                    Glide.with(RegistActivity.this).load(array).into(pic_iv);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new HttpClickCallBack.FailureCallBack() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
        }));
    }

    public void sendSMSCode(String phone, String uuid, String code) {
        String url = ContentKey.SMS_CODE + "phone=" + phone + "&uuid=" + uuid + "&code=" + code;
        HttpUtil.getHttpUtilInstance(this).sendGet(url, new HttpClickCallBack(new HttpClickCallBack.ClickSuccessCallBack() {
            @Override
            public void onSuccess(String json) {
                Log.i("asd", json);
                JSONObject object = null;
                try {
                    object = new JSONObject(json);
                    String code = (String) object.get("code");
                    if (code.equals("00")) {
                        show("发送成功");
                        send_SMS.setClickable(false);
                        send_SMS.setText(String.valueOf(time));
                        handpostTime();
                    } else {
                        show("验证码输入错误");
                        getPic();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new HttpClickCallBack.FailureCallBack() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
        }));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pic_iv:
                getPic();
                break;
            case R.id.send_SMS:
                if (uuid == null) {
                    show("请填写正确验证码");
                }
                if (phone.getText().toString().isEmpty()) {
                    show("请填写手机号");
                    return;
                }
                if (pic_bitmap.getText().toString().isEmpty()) {
                    show("请填写验证码");
                    return;
                }
                sendSMSCode(phone.getText().toString(), uuid, pic_bitmap.getText().toString());
                break;
            case R.id.login:
                phoneNum = phone.getText().toString();
                sms = smsCode.getText().toString();
                psw = passWord.getText().toString();
                login();
                break;
        }
    }

    private void login() {
        JSONObject json = new JSONObject();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("appId", ContentKey.APP_ID);
            map.put("email", "");
            map.put("idcard", "");
            map.put("password", psw);
            map.put("phone", phoneNum);
            map.put("requestTime", EidHttpUtil.getBizTime());
            map.put("username", "");
            map.put("verification_code", sms);

            json = EncryptUtil.getJson(map);
            json.put("sign", EncryptUtil.getHmacMd5Str(ContentKey.MD5_KEY, Base64.encodeToString(
                    EncryptUtil.getSign(map).getBytes("UTF-8"), Base64.NO_WRAP)));
            HttpUtil.getHttpUtilInstance(this).send(ContentKey.REGIST_URL, json, new HttpClickCallBack(this, this));
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void handpostTime() {
        handler.sendEmptyMessageDelayed(TIME_GO, 1000);
    }
}
