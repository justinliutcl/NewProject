package util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;

/**
 * Created by ASUS on 2017/6/11.
 */

public class WindowUtils {
    private WindowUtils windowUtils = null;
    private int mStatusBar;
    public WindowUtils getInstance(Context context){
        if(windowUtils == null){
            windowUtils = new WindowUtils(context);
        }
        return windowUtils;
    }
    private WindowUtils(Context context){
        Rect frame = new Rect();
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        mStatusBar = statusBarHeight;
    }

    public int getStatusBar() {
        return mStatusBar;
    }

}
