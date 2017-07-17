package util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.justin.hzwl.myhzwl.R;

/**
 * Created by ASUS on 2017/6/11.
 */

public class NormalDialog {
    public static void showSimpleDialog(Context context, int id,String text){
        AlertDialog builder = new AlertDialog.Builder(context, R.style.dialog).create();
        builder.show();
        View view = View.inflate(context,id,null);
        TextView t = (TextView) view.findViewById(R.id.text);
        t.setText(text);
        builder.setContentView(view);
    }
    public static AlertDialog showSimpleDialog(Context context, View view){
        AlertDialog builder = new AlertDialog.Builder(context, R.style.dialog).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        builder.setContentView(view);
        return builder;
    }
    public static void showSimpleDialog(Context context, String text){
        AlertDialog builder = new AlertDialog.Builder(context, R.style.dialog).create();
        builder.show();
        View view = View.inflate(context,R.layout.dialog_success,null);
        TextView t = (TextView) view.findViewById(R.id.text);
        t.setText(text);
        builder.setContentView(view);
    }
}
