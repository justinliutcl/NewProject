package util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ASUS on 2017/6/19.
 */

public class AppUtil {

    public static Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("InlinedApi")
    protected void openNFCSettings(Activity context) {

        Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

//    public static JSONObject mac(EidCard eidCard) throws Exception {
//        JSONObject reqJson = null;
//
//        if (!eidCard.isEidCard()) {
//            throw new EidCardException(100, "");
//        }
//        String biz_sequence_id = UUID.randomUUID().toString();
//        String data_to_sign = Utils.getBizTime() + ":" + Utils.getRandom16Number() + ":" + biz_sequence_id;
//        byte[] data_to_sign_bs = data_to_sign.getBytes("UTF-8");
//
//        String idCarrier = eidCard.getCarrierId();
//        String eidCert = eidCard.getEidCertId();
//
//        byte[] eidSign = eidCard.mac(data_to_sign_bs, 10);
//
//        if (Utils.isAllNotNull(idCarrier) && eidSign != null) {
//            reqJson = new JSONObject();
//
//            reqJson.put("sequenceId", biz_sequence_id);
//            reqJson.put("appId", ContentKey.APP_ID);
//            reqJson.put("idType", "01");
//            reqJson.put("idCarrier", idCarrier); // eid载体标识
//            reqJson.put("dataToSign", Base64.encodeToString(data_to_sign_bs, Base64.NO_WRAP));
//            reqJson.put("eidSign", Base64.encodeToString(eidSign, Base64.NO_WRAP));
//            reqJson.put("eidSignAlgorithm", "10");
//            reqJson.put("eidCertId", eidCert);// eid载体证书信息
//            reqJson.put("extension", "");
//        } else {
//            throw new Exception("参数为空");
//        }
//
//        return reqJson;
//    }
//
//    private static JSONObject sign(EidCard eidCard, String pin) throws Exception {
//        JSONObject reqJson = null;
//
//        if (!eidCard.isEidCard()) {
//            throw new EidCardException(100, "");
//        }
//
//        String biz_sequence_id = UUID.randomUUID().toString();
//        String data_to_sign = Utils.getBizTime() + ":" + Utils.getRandom16Number() + ":" + biz_sequence_id;
//        byte[] data_to_sign_bs = data_to_sign.getBytes("UTF-8");
//
//        String idCarrier = eidCard.getCarrierId();
//        String eidCert = eidCard.getEidCertId();
//        eidCard.verifyPIN(pin);
//
//        byte[] eidSign = eidCard.sign(pin, data_to_sign_bs, 20);
//        if (Utils.isAllNotNull(idCarrier, eidCert) && eidSign != null) {
//            reqJson = new JSONObject();
//
//            reqJson.put("sequenceId", biz_sequence_id);
//            reqJson.put("appId", ContentKey.APP_ID);
//            reqJson.put("idType", "01");
//            reqJson.put("idCarrier", idCarrier);// eid载体标识
//            reqJson.put("dataToSign",Base64.encodeToString(data_to_sign_bs, Base64.NO_WRAP));
//            reqJson.put("eidSign", Base64.encodeToString(eidSign, Base64.NO_WRAP));
//            reqJson.put("eidSignAlgorithm", "20");
//            reqJson.put("eidCertId", eidCert);
//            reqJson.put("extension", "");
//        } else {
//            throw new Exception("参数为空");
//        }
//        return reqJson;
//    }

    public static String Bitmap2StrByBase64(Bitmap bit, ImageView imageView, Context context) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        long size = bit.getByteCount() / 1024;
//        int qua = (int) (200f / size * 100);
        bit.compress(Bitmap.CompressFormat.JPEG, 70, bos);
        byte[] bytes = bos.toByteArray();
        imageView.setDrawingCacheEnabled(true);
        Glide.with(context).load(bytes).into(imageView);
        Bitmap drawable = imageView.getDrawingCache();

        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        long size2 = drawable.getByteCount() / 1024;
        int qua2 = (int) (97f / size2 * 100);
        if (qua2 >= 100)
            qua2 = 100;
        drawable.compress(Bitmap.CompressFormat.JPEG, qua2, bos2);
        bytes = bos2.toByteArray();
        imageView.setDrawingCacheEnabled(false);
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static String getURLencode(String text) {
        try {
            return java.net.URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }
}
