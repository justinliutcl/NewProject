package util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.justin.hzwl.myhzwl.R;

/**
 * Created by ASUS on 2017/6/11.
 */

public class NormalDialog {
    public static void showSimpleDialog(Context context, int id){
        AlertDialog builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Dialog).create();
        builder.show();
        builder.setContentView(id);
    }
}
