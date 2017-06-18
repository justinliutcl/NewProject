package util;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.widget.Toast;

/**
 * Created by ASUS on 2017/6/18.
 */

public class NfcUtils {
    public static final int NFC_TYPE_NOT = 1;
    public static final int NFC_TYPE_CLOSE = 2;
    public static final int NFC_TYPE_HAVE = 3;

    public static int initNFCData(Context context) {
        // 得到默认nfc适配器
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context.getApplicationContext());
        // 判定设备是否支持NFC或启动NFC
        if (nfcAdapter == null) {
            return NFC_TYPE_NOT;
        }
        if (!nfcAdapter.isEnabled()) {
            return NFC_TYPE_CLOSE;
        }
        return NFC_TYPE_HAVE;
    }
}
