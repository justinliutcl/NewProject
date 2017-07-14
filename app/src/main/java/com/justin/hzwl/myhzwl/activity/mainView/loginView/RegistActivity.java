package com.justin.hzwl.myhzwl.activity.mainView.loginView;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

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

public class RegistActivity extends BaseActivity {
    EditText phone, smsCode, passWord;
    ImageView login;
    String phoneNum,sms,psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

    }

    @Override
    public void init() {
        phone = (EditText) findViewById(R.id.phone_nume);
        smsCode = (EditText) findViewById(R.id.sms_code);
        passWord = (EditText) findViewById(R.id.password);
        login = (ImageView) findViewById(R.id.login);
    }

    @Override
    public void setListener() {
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.phone_nume:
//
//                break;
//            case R.id.sms_code:
//
//                break;
//            case R.id.password:
//
//                break;
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
            HashMap<String,String> map =new HashMap<>();
            map.put("appId", ContentKey.APP_ID);
            map.put("email","");
            map.put("idcard","");
            map.put("password",psw);
            map.put("phone",phoneNum);
            map.put("requestTime", EidHttpUtil.getBizTime());
            map.put("username","");
            map.put("verification_code",sms);

            json = EncryptUtil.getJson(map);
            json.put("sign", EncryptUtil.getHmacMd5Str(ContentKey.MD5_KEY, Base64.encodeToString(
                    EncryptUtil.getSign(map).getBytes("UTF-8"), Base64.NO_WRAP)));
            HttpUtil.getHttpUtilInstance(this).send(ContentKey.REGIST_URL,json,new HttpClickCallBack(this,this));
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
            if(code.equals(ContentKey.success)){
                show("注册成功");
            }else{
                show("已存在相同账户");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
