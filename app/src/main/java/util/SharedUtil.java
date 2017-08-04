package util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Justinliu on 2017/6/13.
 */

public class SharedUtil {
    private SharedPreferences sp;
    private static SharedUtil sharedUtil;

    public static SharedUtil getInstance(Context context) {
        if (sharedUtil == null) {
            sharedUtil = new SharedUtil(context);
        }
        return sharedUtil;
    }

    private SharedUtil(Context context) {
        if (sp == null) {
            sp = context.getApplicationContext().getSharedPreferences("db", Context.MODE_PRIVATE);
        }
    }

    public void setFirst(){
        setDate(ContentKey.IS_FIRST,"true");
    }

    public void setUserId(String id){
        setDate(ContentKey.USER_ID, id);
    }

    public String getUserId() {
        String value = getData(ContentKey.USER_ID);
        return value;
    }

    public boolean getFirst() {
        String value = getData(ContentKey.IS_FIRST);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }


    public void setDate(String key, String value) {
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key, value);
        ed.commit();
    }

    public String getData(String key) {
        return sp.getString(key, null);
    }
}
