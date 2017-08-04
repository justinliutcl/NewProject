package com.justin.hzwl.myhzwl.modul.alipay2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.justin.hzwl.myhzwl.modul.alipay2.util.OrderInfoUtil2_0;

import java.util.Map;

/**
 * Created by 不爱白菜 on 2016/4/10.
 */
public class AliPayUse {
    /**
     *  重要说明:
     *
     *  这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
     *  真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
     *  防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
     */

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2017061907523253";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088721276012644";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJO4HPKCblnTPPyaBJLer25AG49jgJ0gEgM0xAN90NSAxRzhFl5sNL83b3mn21M0ywO9F9kZLkTwxaYjOwI5AIzdfxff9E/T4HQz6jBNlNk/gmCjV6y8LXms2fTJMvuSglHVq/yJzsHTVmA49CVRrlVroVazdXvq22/tX6KBt9lVAgMBAAECgYAHMdh3F1M4nMlDpFzEqoPw4r505WvGO2GlNlS1ttMNBpiTJ4NnZe3Lwm9zhXFxRAj/TZ5zvH48bV2zEdQ174DPSKkN1LMDNccBqpvXpbHtxMjq/+lUNJSDDd5wxmhUcfqpyrgyKFI4qsiLrgjq51G6Lp+rcycHzP5rfGFzRTBwoQJBAMLVJNRN7DSoHYyK7CnTR4+4SOzs2lz17C0tfVoio1aLkd4dqGEJmM0Zd0JV163NIT5x9vRPfHKbUHartjk8tv8CQQDCGG0hZZgrjbdoDZn9xmyTqgebH2yGTB0G7qkXE1LqjTlwqOKNytr9KIB+6UuyQ+PypovKf5XshgTu7bIBm2OrAkEAgXigy9CoeemKEV40SCsV8ATXuVJAXewysWb8WQyRnK6NFZyBy1JUT19v6A4hkGazg8q6AGpd2c8qbmH3axvUwwJBALajDW1zj7DL9VzwD4sAf4KjZ+zLsO7eCCJRyMJx6H/Uy8v63tPVoiK9Tcwd8qCXyZK2rKurSoP5yAKbn/hrI2kCQQClnkg7Le8NHsx2n+bTkYNp2A7gwOl/pi673J4j1QJHmz2G2fqH3mRA55cZCAZa/l9b/aN7l+vB0vYoy7dcX2pT";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private Context context;
    private String outkey;
    private double money;
    private String title;
    String type;
    String outUri;
    OnPayCall paycall;

    public interface OnPayCall {
        public void SuccessCallBack(String mes);

        public void failCallBack(String mes);
    }


    public AliPayUse(String outkey, Context context, String title, double money, String type, String path, OnPayCall paycall) {
        this.outkey = outkey;
        this.context = context;
        this.money = money;
        this.title = title;
        this.money = money;
        this.type = type;
        outUri = path;
        this.paycall = paycall;
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        paycall.SuccessCallBack("支付成功");
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(context, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            paycall.failCallBack("支付失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    public void pay() {
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,type,money, rsa2,context);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}