package util;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Justinliu on 2017/6/15.
 */

public class HttpUtil {
    private OkHttpClient client;
    private static class HttpUtilBean {
        public static HttpUtil util = new HttpUtil();
    }

    private HttpUtil() {
        client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    public static HttpUtil getHttpUtilInstance() {
        return HttpUtilBean.util;
    }

    public void getExecute(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .method("GET",null)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("asd",response.body().toString());
                Log.i("asd",response.networkResponse().toString());
            }
        });
    }
}
