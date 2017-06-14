package util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Justinliu on 2017/6/13.
 */

public class SharedUtil {
    private SharedPreferences sp;
    private SharedUtil sharedUtil;

    public SharedUtil getInstance(Context context) {
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

    public void setDate(Context context, String key, String value) {
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key, value);
        ed.commit();
    }

    public String getData(Context context, String key) {
        return sp.getString(key, null);
    }
}
