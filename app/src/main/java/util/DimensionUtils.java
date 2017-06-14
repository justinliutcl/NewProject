package util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

public class DimensionUtils {

    public static float DENSITY;
    public static int DENSITY_DPI;
    public static int WIDTH_PIXELS;
    public static float WIDTH_DP;
    public static int HEIGHT_PIXELS;
    public static float HEIGHT_DP;
    public static int PRODUCT_STATUS_BAR_MARGIN;
    public static int STATUS_BAR_HEIGHT;
    public static float SCALED_DENSITY;


    public static int dpToPx(float dp) {
        return (int) (DENSITY * dp + 0.5);
    }

    public static float pxToDp(int px) {
        return px / DENSITY;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        return (int) (pxValue / SCALED_DENSITY + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        return (int) (spValue * SCALED_DENSITY + 0.5f);
    }

    /**
     * do it when app start up
     *
     * @param context
     */
    public static void initDimension(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        DENSITY = dm.density;
        DENSITY_DPI = dm.densityDpi;
        WIDTH_PIXELS = dm.widthPixels;
        HEIGHT_PIXELS = dm.heightPixels;
        SCALED_DENSITY = dm.scaledDensity;
        WIDTH_DP = pxToDp(WIDTH_PIXELS);
        HEIGHT_DP = pxToDp(HEIGHT_PIXELS);
        STATUS_BAR_HEIGHT = getStatusBarHeight(context);
        PRODUCT_STATUS_BAR_MARGIN = 0;
    }

    public static final String getDpiString(DisplayMetrics dm) {
        if (dm.xdpi <= 120) {
            return "ldpi";
        }
        if (dm.xdpi > 120 && dm.xdpi <= 160) {
            return "mdpi";
        }
        if (dm.xdpi > 160 && dm.xdpi <= 240) {
            return "hdpi";
        }
        if (dm.xdpi > 240 && dm.xdpi <= 320) {
            return "xdpi";
        }
        if (dm.xdpi > 320 && dm.xdpi <= 480) {
            return "xxdpi";
        }
        if (dm.xdpi > 480 && dm.xdpi <= 640) {
            return "xxxdpi";
        }
        return "boom";
    }

    public static int getNavigationBarHeight(Context context) {
        try {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            return resources.getDimensionPixelSize(resourceId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    // 获取手机状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {

        }
        return statusBarHeight;
    }
}
