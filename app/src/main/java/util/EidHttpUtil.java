package util;

import android.util.Base64;

import com.google.zxing.common.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ASUS on 2017/7/11.
 */

public class EidHttpUtil {
    public static JSONObject getConfig(){
        JSONObject sig_json=new JSONObject();
        try {
            sig_json.put("version", "1.0.0");
            sig_json.put("return_url", "");
            sig_json.put("appid", "02JR1610081555371526");
            sig_json.put("biz_time", getBizTime());
            sig_json.put("biz_sequence_id", UUID.randomUUID().toString().replace("-", ""));
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String encrypt_factor = uuid.substring(uuid.length() - 16);
            String uuid2 = UUID.randomUUID().toString().replace("-", "");
            String sign_factor = uuid2.substring(uuid2.length() - 16);
            Map factor=new HashMap();
            factor.put("encrypt_factor", encrypt_factor);
            factor.put("sign_factor", sign_factor);
            sig_json.put("security_factor",new JSONObject(factor));
            sig_json.put("encrypt_type", "1");
            sig_json.put("sign_type", "1");
            sig_json.put("security_type", "10");
            sig_json.put("attach", "");
            sig_json.put("extension", new JSONObject()); // eid扩展信息
            //biz_type
        }catch (Exception e){

        }
        return sig_json;
    }

    public static JSONObject getConfig2(String encrypt_factor,String sign_factor,String biz_sequence,String time){
        JSONObject sig_json=new JSONObject();
        try {
            sig_json.put("version", "1.0.0");
            sig_json.put("return_url", "");
            sig_json.put("appId", "02JR1610081555371526");
            sig_json.put("biz_time", time);
            sig_json.put("biz_sequence_id", biz_sequence);
            Map factor=new HashMap();
            factor.put("encrypt_factor", encrypt_factor);
            factor.put("sign_factor", sign_factor);
            sig_json.put("security_factor",new JSONObject(factor));
            sig_json.put("encrypt_type", "1");
            sig_json.put("sign_type", "1");
            sig_json.put("security_type", "10");
            sig_json.put("attach", "");
            sig_json.put("extension", new JSONObject()); // eid扩展信息
            //biz_type
        }catch (Exception e){

        }
        return sig_json;
    }

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getBizTime(){
        Date d = new Date();
        return format.format(d);
    }

    public static String getRandom16Number(){
        byte[] b16 = new byte[16];
        Random r = new Random();
        r.nextBytes(b16);
        return Base64.encodeToString(b16, Base64.NO_WRAP);
    }

    public static String createUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String byte2Hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2Byte(String str) {
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer
                        .decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

}
