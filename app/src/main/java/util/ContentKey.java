package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2017/6/20.
 */

public class ContentKey {
    public static final String IS_FIRST="is_first";
    public static final String APP_ID="02JR1610081555371526";
    public static final String REGIST_APP_KEY="6817372066116487858187c8bcf02025";
    public static final String MD5_KEY="994EB0BC820FBAF80AC0FF2B41DB9115";
    public static final String VERSION="2.0.0";

    public static final String LOGIN_URL        = "http://101.201.56.23:8888/authenti-eidapi/asserver/identity/check";
    public static final String REGIST_URL       = "http://101.201.56.23:8888/authenti-eidapi/asserver/identity/register";

    public static final String success = "00";

    public static Map getDefuleMap(String bizNum){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = dateFormat.format(new Date());
        HashMap<String,String> map = new HashMap<>();
        map.put("version",VERSION);
        map.put("app_id",APP_ID);
        map.put("return_url","");
        map.put("biz_type",bizNum);
        map.put("biz_time",time);
        map.put("biz_sequence_id",VERSION);
        map.put("security_factor","{\"encrypt_factor\":\"encryptfactor\",\"sign_factor\":\"signfactor\"}");
        map.put("encrypt_type","1");
        map.put("sign_type","1");
        map.put("sign","1");
        return map;
    }
}
