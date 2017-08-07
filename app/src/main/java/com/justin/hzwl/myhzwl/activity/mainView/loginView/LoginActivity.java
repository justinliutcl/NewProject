package com.justin.hzwl.myhzwl.activity.mainView.loginView;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;
import com.justin.hzwl.myhzwl.activity.MainActivity;
import com.justin.hzwl.myhzwl.modul.callbackInterface.HttpClickCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import util.ContentKey;
import util.EidHttpUtil;
import util.EncryptUtil;
import util.HttpUtil;
import util.SharedUtil;

public class LoginActivity extends BaseActivity {
    ImageView login;
    TextView regist, forget;
    EditText phone, password;
    String psw, phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (SharedUtil.getInstance(this).getPhoneNum() != null &&
                SharedUtil.getInstance(this).getUserId() != null) {
            jumpToFinish(this, MainActivity.class);
        }
    }

    @Override
    public void init() {
        login = (ImageView) findViewById(R.id.login);
        regist = (TextView) findViewById(R.id.regist);
        forget = (TextView) findViewById(R.id.forget);
        phone = (EditText) findViewById(R.id.phone_nume);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void setListener() {
        login.setOnClickListener(this);
        regist.setOnClickListener(this);
        forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                psw = password.getText().toString();
                phoneNum = phone.getText().toString();
                login();
                break;
            case R.id.regist:
                jumpTo(this, RegistActivity.class);
                break;
            case R.id.forget:
                jumpTo(this, ForgetActivity.class);
                break;
        }
    }

    private void login() {
        JSONObject json;
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("appId", ContentKey.APP_ID);
            map.put("password", psw);
            map.put("phone", phoneNum);
            map.put("requestTime", EidHttpUtil.getBizTime());
            map.put("username", "");

            json = EncryptUtil.getJson(map);
            json.put("sign", EncryptUtil.getHmacMd5Str(ContentKey.MD5_KEY, Base64.encodeToString(
                    EncryptUtil.getSign(map).getBytes("UTF-8"), Base64.NO_WRAP)));
            HttpUtil.getHttpUtilInstance(this).send(ContentKey.LOGIN_URL, json, new HttpClickCallBack(this, this));
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(String json) {
        super.onSuccess(json);
        try {
            JSONObject object = new JSONObject(json);
            String code = (String) object.get("code");

            if (code.equals(ContentKey.success)) {
                String id = (String) object.getJSONObject("user").get("id");
                SharedUtil.getInstance(LoginActivity.this).setUserId(id);
                SharedUtil.getInstance(LoginActivity.this).setPhoneNum(phoneNum);
                jumpToFinish(this, MainActivity.class);
                show("登录成功");
            } else {
                show("用户名或密码错误");
                jumpToFinish(this,MainActivity.class);
                //测试用
                SharedUtil.getInstance(LoginActivity.this).setUserId("51e8522e662147c69040066e7b2b4e12");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
