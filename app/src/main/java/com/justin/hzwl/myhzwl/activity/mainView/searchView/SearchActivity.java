package com.justin.hzwl.myhzwl.activity.mainView.searchView;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eidlink.impl.EidLinkSEImpl;
import com.eidlink.sdk.EidCard;
import com.eidlink.sdk.EidCardException;
import com.eidlink.sdk.EidCardFactory;
import com.eidlink.sdk.EidLinkSE;
import com.justin.hzwl.myhzwl.R;
import com.justin.hzwl.myhzwl.activity.BaseActivity;

import static android.nfc.NfcAdapter.EXTRA_TAG;

public class SearchActivity extends BaseActivity{
    ImageView phone,eid_iv,idCard_iv;
    ValueAnimator v;
    LinearLayout eid_layout,idCard_layout;
    boolean isEIDCard;

    // NFC parts
    protected static NfcAdapter mAdapter;
    private static PendingIntent mPendingIntent;
    private static IntentFilter[] mFilters;
    private static String[][] mTechLists;


    public static final int MESSAGE_VALID_OTGBUTTON=15;
    public static final int MESSAGE_VALID_NFCBUTTON=16;
    public static final int MESSAGE_VALID_BTBUTTON=17;
    public static final int MESSAGE_VALID_PROCESS=1001;
    /**
     *  服务器无法连接
     */
    public final static int SERVER_CANNOT_CONNECT  =  90000001;
    /**
     *  开始读卡
     */
    public final static int READ_CARD_START        =  10000001;
    /**
     *  读卡进度
     */
    public final static int READ_CARD_PROGRESS     =  20000002;
    /**
     *  读卡成功
     */
    public final static int READ_CARD_SUCCESS      =  30000003;
    public final static int READ_CARD_RESULT       =  30000004;

    /**
     *  读照片成功
     */
    public final static int READ_PHOTO_SUCESS      =  40000004;
    /**
     *  读卡失败
     */
    public final static int READ_CARD_FAILED       =  90000009;

    public static final int MESSAGE_VALID_NFCSTART=26;

    private String salt="12345678876543215555555544444444";

    private byte TerminalCert[] =  {(byte) 0x30, (byte) 0x82, (byte) 0x02, (byte) 0xF9, (byte) 0x30, (byte) 0x82, (byte) 0x02, (byte) 0x9E, (byte) 0xA0, (byte) 0x03, (byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x02, (byte) 0x08, (byte) 0x1B, (byte) 0x58, (byte) 0xA4, (byte) 0xCF, (byte) 0x14, (byte) 0xF0, (byte) 0x41, (byte) 0x53, (byte) 0x30, (byte) 0x0C, (byte) 0x06, (byte) 0x08, (byte) 0x2A, (byte) 0x81, (byte) 0x1C, (byte) 0xCF, (byte) 0x55, (byte) 0x01, (byte) 0x83, (byte) 0x75, (byte) 0x05, (byte) 0x00, (byte) 0x30, (byte) 0x78, (byte) 0x31, (byte) 0x0B, (byte) 0x30, (byte) 0x09, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x06, (byte) 0x13, (byte) 0x02, (byte) 0x43, (byte) 0x4E, (byte) 0x31, (byte) 0x0F, (byte) 0x30, (byte) 0x0D, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x08, (byte) 0x0C, (byte) 0x06, (byte) 0xE5, (byte) 0x8C, (byte) 0x97, (byte) 0xE4, (byte) 0xBA, (byte) 0xAC, (byte) 0x31, (byte) 0x0F, (byte) 0x30, (byte) 0x0D, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x07, (byte) 0x0C, (byte) 0x06, (byte) 0xE5, (byte) 0x8C, (byte) 0x97, (byte) 0xE4, (byte) 0xBA, (byte) 0xAC, (byte) 0x31, (byte) 0x2D, (byte) 0x30, (byte) 0x2B, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x0A, (byte) 0x0C, (byte) 0x24, (byte) 0xE9, (byte) 0x87, (byte) 0x91, (byte) 0xE8, (byte) 0x81, (byte) 0x94, (byte) 0xE6, (byte) 0xB1, (byte) 0x87, (byte) 0xE9, (byte) 0x80, (byte) 0x9A, (byte) 0xE4, (byte) 0xBF, (byte) 0xA1, (byte) 0xE6, (byte) 0x81, (byte) 0xAF, (byte) 0xE6, (byte) 0x8A, (byte) 0x80, (byte) 0xE6, (byte) 0x9C, (byte) 0xAF, (byte) 0xE6, (byte) 0x9C, (byte) 0x89, (byte) 0xE9, (byte) 0x99, (byte) 0x90, (byte) 0xE5, (byte) 0x85, (byte) 0xAC, (byte) 0xE5, (byte) 0x8F, (byte) 0xB8, (byte) 0x31, (byte) 0x18, (byte) 0x30, (byte) 0x16, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x03, (byte) 0x0C, (byte) 0x0F, (byte) 0x65, (byte) 0x49, (byte) 0x44, (byte) 0x4C, (byte) 0x49, (byte) 0x4E, (byte) 0x4B, (byte) 0x20, (byte) 0x52, (byte) 0x4F, (byte) 0x4F, (byte) 0x54, (byte) 0x20, (byte) 0x43, (byte) 0x41, (byte) 0x30, (byte) 0x1E, (byte) 0x17, (byte) 0x0D, (byte) 0x31, (byte) 0x37, (byte) 0x30, (byte) 0x35, (byte) 0x31, (byte) 0x35, (byte) 0x30, (byte) 0x36, (byte) 0x35, (byte) 0x31, (byte) 0x30, (byte) 0x36, (byte) 0x5A, (byte) 0x17, (byte) 0x0D, (byte) 0x31, (byte) 0x37, (byte) 0x31, (byte) 0x31, (byte) 0x31, (byte) 0x31, (byte) 0x30, (byte) 0x36, (byte) 0x35, (byte) 0x31, (byte) 0x30, (byte) 0x36, (byte) 0x5A, (byte) 0x30, (byte) 0x52, (byte) 0x31, (byte) 0x0B, (byte) 0x30, (byte) 0x09, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x06, (byte) 0x13, (byte) 0x02, (byte) 0x43, (byte) 0x4E, (byte) 0x31, (byte) 0x0B, (byte) 0x30, (byte) 0x09, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x0A, (byte) 0x0C, (byte) 0x02, (byte) 0x43, (byte) 0x41, (byte) 0x31, (byte) 0x0B, (byte) 0x30, (byte) 0x09, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x0B, (byte) 0x0C, (byte) 0x02, (byte) 0x43, (byte) 0x41, (byte) 0x31, (byte) 0x29, (byte) 0x30, (byte) 0x27, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x03, (byte) 0x0C, (byte) 0x20, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x31, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x31, (byte) 0x31, (byte) 0x30, (byte) 0x35, (byte) 0x30, (byte) 0x46, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x30, (byte) 0x41, (byte) 0x30, (byte) 0x59, (byte) 0x30, (byte) 0x13, (byte) 0x06, (byte) 0x07, (byte) 0x2A, (byte) 0x86, (byte) 0x48, (byte) 0xCE, (byte) 0x3D, (byte) 0x02, (byte) 0x01, (byte) 0x06, (byte) 0x08, (byte) 0x2A, (byte) 0x81, (byte) 0x1C, (byte) 0xCF, (byte) 0x55, (byte) 0x01, (byte) 0x82, (byte) 0x2D, (byte) 0x03, (byte) 0x42, (byte) 0x00, (byte) 0x04, (byte) 0x86, (byte) 0xE5, (byte) 0x37, (byte) 0xFA, (byte) 0xEA, (byte) 0xC0, (byte) 0xA4, (byte) 0xE3, (byte) 0xE3, (byte) 0x9B, (byte) 0x86, (byte) 0xCF, (byte) 0xC9, (byte) 0x92, (byte) 0xAD, (byte) 0xD3, (byte) 0x4F, (byte) 0xDB, (byte) 0xD1, (byte) 0xAC, (byte) 0x60, (byte) 0x88, (byte) 0x9A, (byte) 0xAA, (byte) 0x11, (byte) 0xF7, (byte) 0xE2, (byte) 0x89, (byte) 0xF9, (byte) 0x7D, (byte) 0xC3, (byte) 0x0F, (byte) 0xBF, (byte) 0xB9, (byte) 0xE1, (byte) 0x4E, (byte) 0xF2, (byte) 0xBF, (byte) 0xCE, (byte) 0x43, (byte) 0x22, (byte) 0x4A, (byte) 0x35, (byte) 0xA7, (byte) 0xF9, (byte) 0x47, (byte) 0x85, (byte) 0x23, (byte) 0x8E, (byte) 0x4B, (byte) 0x3E, (byte) 0x73, (byte) 0x1A, (byte) 0x1D, (byte) 0xB7, (byte) 0x9C, (byte) 0x1C, (byte) 0xCA, (byte) 0xE3, (byte) 0x1F, (byte) 0x0C, (byte) 0x33, (byte) 0xA6, (byte) 0x19, (byte) 0xA3, (byte) 0x82, (byte) 0x01, (byte) 0x34, (byte) 0x30, (byte) 0x82, (byte) 0x01, (byte) 0x30, (byte) 0x30, (byte) 0x1D, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x1D, (byte) 0x0E, (byte) 0x04, (byte) 0x16, (byte) 0x04, (byte) 0x14, (byte) 0x87, (byte) 0x8A, (byte) 0xE1, (byte) 0x2F, (byte) 0x5C, (byte) 0x90, (byte) 0x75, (byte) 0xFA, (byte) 0x42, (byte) 0x51, (byte) 0xFC, (byte) 0xF0, (byte) 0x98, (byte) 0xC0, (byte) 0xF9, (byte) 0x45, (byte) 0xF6, (byte) 0xA5, (byte) 0x83, (byte) 0x46, (byte) 0x30, (byte) 0x81, (byte) 0xA9, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x1D, (byte) 0x23, (byte) 0x04, (byte) 0x81, (byte) 0xA1, (byte) 0x30, (byte) 0x81, (byte) 0x9E, (byte) 0x80, (byte) 0x14, (byte) 0xB6, (byte) 0x4E, (byte) 0xFD, (byte) 0xC0, (byte) 0xB5, (byte) 0xE1, (byte) 0x0D, (byte) 0xB1, (byte) 0xF9, (byte) 0x2E, (byte) 0x1D, (byte) 0x98, (byte) 0x57, (byte) 0xB9, (byte) 0x20, (byte) 0x81, (byte) 0xF0, (byte) 0xC4, (byte) 0xBC, (byte) 0x17, (byte) 0xA1, (byte) 0x7C, (byte) 0xA4, (byte) 0x7A, (byte) 0x30, (byte) 0x78, (byte) 0x31, (byte) 0x0B, (byte) 0x30, (byte) 0x09, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x06, (byte) 0x13, (byte) 0x02, (byte) 0x43, (byte) 0x4E, (byte) 0x31, (byte) 0x0F, (byte) 0x30, (byte) 0x0D, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x08, (byte) 0x0C, (byte) 0x06, (byte) 0xE5, (byte) 0x8C, (byte) 0x97, (byte) 0xE4, (byte) 0xBA, (byte) 0xAC, (byte) 0x31, (byte) 0x0F, (byte) 0x30, (byte) 0x0D, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x07, (byte) 0x0C, (byte) 0x06, (byte) 0xE5, (byte) 0x8C, (byte) 0x97, (byte) 0xE4, (byte) 0xBA, (byte) 0xAC, (byte) 0x31, (byte) 0x2D, (byte) 0x30, (byte) 0x2B, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x0A, (byte) 0x0C, (byte) 0x24, (byte) 0xE9, (byte) 0x87, (byte) 0x91, (byte) 0xE8, (byte) 0x81, (byte) 0x94, (byte) 0xE6, (byte) 0xB1, (byte) 0x87, (byte) 0xE9, (byte) 0x80, (byte) 0x9A, (byte) 0xE4, (byte) 0xBF, (byte) 0xA1, (byte) 0xE6, (byte) 0x81, (byte) 0xAF, (byte) 0xE6, (byte) 0x8A, (byte) 0x80, (byte) 0xE6, (byte) 0x9C, (byte) 0xAF, (byte) 0xE6, (byte) 0x9C, (byte) 0x89, (byte) 0xE9, (byte) 0x99, (byte) 0x90, (byte) 0xE5, (byte) 0x85, (byte) 0xAC, (byte) 0xE5, (byte) 0x8F, (byte) 0xB8, (byte) 0x31, (byte) 0x18, (byte) 0x30, (byte) 0x16, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x03, (byte) 0x0C, (byte) 0x0F, (byte) 0x65, (byte) 0x49, (byte) 0x44, (byte) 0x4C, (byte) 0x49, (byte) 0x4E, (byte) 0x4B, (byte) 0x20, (byte) 0x52, (byte) 0x4F, (byte) 0x4F, (byte) 0x54, (byte) 0x20, (byte) 0x43, (byte) 0x41, (byte) 0x82, (byte) 0x08, (byte) 0x61, (byte) 0xBE, (byte) 0x98, (byte) 0xDF, (byte) 0xC1, (byte) 0xA2, (byte) 0x7D, (byte) 0x49, (byte) 0x30, (byte) 0x55, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x1D, (byte) 0x1F, (byte) 0x04, (byte) 0x4E, (byte) 0x30, (byte) 0x4C, (byte) 0x30, (byte) 0x4A, (byte) 0xA0, (byte) 0x48, (byte) 0xA0, (byte) 0x46, (byte) 0x81, (byte) 0x44, (byte) 0x68, (byte) 0x74, (byte) 0x74, (byte) 0x70, (byte) 0x3A, (byte) 0x2F, (byte) 0x2F, (byte) 0x31, (byte) 0x39, (byte) 0x32, (byte) 0x2E, (byte) 0x31, (byte) 0x36, (byte) 0x38, (byte) 0x2E, (byte) 0x31, (byte) 0x2E, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x3A, (byte) 0x38, (byte) 0x30, (byte) 0x2F, (byte) 0x63, (byte) 0x61, (byte) 0x2F, (byte) 0x63, (byte) 0x72, (byte) 0x6C, (byte) 0x2F, (byte) 0x63, (byte) 0x72, (byte) 0x6C, (byte) 0x44, (byte) 0x6F, (byte) 0x77, (byte) 0x6E, (byte) 0x6C, (byte) 0x6F, (byte) 0x61, (byte) 0x64, (byte) 0x45, (byte) 0x78, (byte) 0x74, (byte) 0x2E, (byte) 0x64, (byte) 0x6F, (byte) 0x3F, (byte) 0x63, (byte) 0x53, (byte) 0x69, (byte) 0x67, (byte) 0x6E, (byte) 0x41, (byte) 0x6C, (byte) 0x67, (byte) 0x3D, (byte) 0x53, (byte) 0x4D, (byte) 0x33, (byte) 0x57, (byte) 0x69, (byte) 0x74, (byte) 0x68, (byte) 0x53, (byte) 0x4D, (byte) 0x32, (byte) 0x30, (byte) 0x0C, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x1D, (byte) 0x13, (byte) 0x01, (byte) 0x01, (byte) 0xFF, (byte) 0x04, (byte) 0x02, (byte) 0x30, (byte) 0x00, (byte) 0x30, (byte) 0x0C, (byte) 0x06, (byte) 0x08, (byte) 0x2A, (byte) 0x81, (byte) 0x1C, (byte) 0xCF, (byte) 0x55, (byte) 0x01, (byte) 0x83, (byte) 0x75, (byte) 0x05, (byte) 0x00, (byte) 0x03, (byte) 0x47, (byte) 0x00, (byte) 0x30, (byte) 0x44, (byte) 0x02, (byte) 0x20, (byte) 0x49, (byte) 0x1A, (byte) 0x87, (byte) 0xC6, (byte) 0x0B, (byte) 0x9F, (byte) 0x40, (byte) 0xD5, (byte) 0x0C, (byte) 0xFA, (byte) 0xB3, (byte) 0x37, (byte) 0xBF, (byte) 0x51, (byte) 0x73, (byte) 0x2F, (byte) 0x61, (byte) 0xC5, (byte) 0x65, (byte) 0xD2, (byte) 0x2F, (byte) 0x49, (byte) 0x1B, (byte) 0x96, (byte) 0x02, (byte) 0x0B, (byte) 0x0B, (byte) 0x61, (byte) 0xF4, (byte) 0x70, (byte) 0xA4, (byte) 0x69, (byte) 0x02, (byte) 0x20, (byte) 0x3E, (byte) 0xB6, (byte) 0x2D, (byte) 0xD2, (byte) 0x5D, (byte) 0x48, (byte) 0x36, (byte) 0x0D, (byte) 0xA5, (byte) 0xA3, (byte) 0xD9, (byte) 0xB9, (byte) 0xD5, (byte) 0x2C, (byte) 0x6D, (byte) 0x54, (byte) 0xC1, (byte) 0x75, (byte) 0x87, (byte) 0x79, (byte) 0xB4, (byte) 0x88, (byte) 0x9F, (byte) 0x9F, (byte) 0xA4, (byte) 0x5C, (byte) 0x7E, (byte) 0xC1, (byte) 0xA9, (byte) 0xA5, (byte) 0x64, (byte) 0x86};
    private byte TerminalPrikey[]={(byte) 0x7E, (byte) 0x06, (byte) 0x38, (byte) 0xA1, (byte) 0x28, (byte) 0x24, (byte) 0x61, (byte) 0x9C, (byte) 0x5C, (byte) 0xA2, (byte) 0x1A, (byte) 0xDC, (byte) 0xB7, (byte) 0xF9, (byte) 0x23, (byte) 0xD5, (byte) 0x23, (byte) 0xDE, (byte) 0x47, (byte) 0x7B, (byte) 0x00, (byte) 0xCD, (byte) 0x36, (byte) 0x2D, (byte) 0x22, (byte) 0xB3, (byte) 0x0C, (byte) 0x9B, (byte) 0x24, (byte) 0x02, (byte) 0x1A, (byte) 0x46};

    private EidLinkSE eid;
    private Intent inintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        phone = (ImageView) findViewById(R.id.phone);
        eid_layout = (LinearLayout) findViewById(R.id.eid_layout);
        idCard_layout = (LinearLayout) findViewById(R.id.idcard_layout);
        eid_iv = (ImageView) findViewById(R.id.eid_iv);
        idCard_iv = (ImageView) findViewById(R.id.idcard_iv);
        eid = EidLinkSEImpl.newInstance(mHandler, this, "0000000");
        eid.setSEInfo(TerminalCert,TerminalPrikey);
        initAnim();
        initNfc();
    }

    private void initAnim(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int right = phone.getWidth();
                v = ValueAnimator.ofInt(0, right);
                v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int p = (int) animation.getAnimatedValue();
                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) phone.getLayoutParams();
                        layoutParams.setMargins(0, 0, p, layoutParams.bottomMargin);
                        phone.setLayoutParams(layoutParams);

                    }
                });
                v.setDuration(1700);
                v.setInterpolator(new AccelerateDecelerateInterpolator());
                v.setRepeatCount(ValueAnimator.INFINITE);
                v.start();
            }
        }, 300);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())||
                NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            inintent=intent;
            if(isEIDCard){
                initNFC(inintent);
            }else{
                mHandler.sendEmptyMessageDelayed(MESSAGE_VALID_NFCBUTTON, 0);
            }
        }
    }

    @Override
    public void init() {
        mBackView.getTitleRightWrapper().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(SearchActivity.this, HelpActivity.class);
            }
        });

    }

    @Override
    public void setListener() {
        eid_layout.setOnClickListener(this);
        idCard_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eid_layout:
                phone.setImageResource(R.drawable.icon_eidcard);
                eid_iv.setImageResource(R.drawable.icon_eid_card_select);
                idCard_iv.setImageResource(R.drawable.icon_id_card_nor);
                isEIDCard = true;
                break;
            case R.id.idcard_layout:
                phone.setImageResource(R.drawable.icon_idcard);
                eid_iv.setImageResource(R.drawable.icon_eid_card_nor);
                idCard_iv.setImageResource(R.drawable.icon_id_card_select);
                isEIDCard = false;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        v.cancel();
    }

    @SuppressLint("NewApi")
    private void initNfc() {

        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            return;
        }

        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this.getApplicationContext(), getClass())
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tech = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter tag = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        try {

            ndef.addDataType("*/*");

        } catch (IntentFilter.MalformedMimeTypeException e) {

            throw new RuntimeException("fail", e);

        }
        mFilters = new IntentFilter[] { tech, ndef, tag };

        // Setup a teach list for all NfcF tags
        mTechLists = new String[][] { new String[] { IsoDep.class.getName(),
                NfcA.class.getName(),NfcB.class.getName(),NfcF.class.getName(),NfcV.class.getName()} };

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdapter != null && mAdapter.isEnabled())
            mAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null)
            mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
                    mTechLists);
    }

    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int tt;
            switch (msg.what) {
                case MESSAGE_VALID_NFCSTART:
                    Boolean enablenfcs=eid.EnableSystemNFCMessage();
                    Log.i("asd","    NFC初始化失败     ");
                    break;
                case SERVER_CANNOT_CONNECT:
                    Log.i("asd","    没有连接到服务器     ");
                    Log.i("asd","         ");
                    break;
                case READ_CARD_START:
                    Log.i("asd","     开始读卡    ");
                    break;
                case READ_CARD_PROGRESS:
                    int progress_value = (Integer) msg.obj;
                    Log.i("asd","   正在读卡，请稍候...    ");


                    break;
                case READ_CARD_SUCCESS:
                    Log.i("asd","  读卡成功     "+eid.getSalt());
                    break;
                case READ_PHOTO_SUCESS:
                    break;
                case READ_CARD_FAILED:
                    int error=msg.arg1;
                    Log.i("asd","  error     "+error);

                    break;
                case MESSAGE_VALID_BTBUTTON:
                    break;
                case MESSAGE_VALID_NFCBUTTON:
                    eid.NFCreadCard(inintent);
                    break;
                case MESSAGE_VALID_OTGBUTTON:

                    break;
            }
        }
    };


    private void initNFC(Intent intent){
        //NFC获取IsoDep
        Tag tag = (Tag) intent.getParcelableExtra(EXTRA_TAG);
        IsoDep isoDep = IsoDep.get(tag);

        //获取EidCard
        try {
            EidCard eidCard = EidCardFactory.getEidCardInstanceForNfc(isoDep);

        } catch (EidCardException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
