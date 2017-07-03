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
    public static final String APP_ID="01JR1502021030342868";
    public static final String REGIST_APP_KEY="6817372066116487858187c8bcf02025";
    public static final String VERSION="2.0.0";

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
