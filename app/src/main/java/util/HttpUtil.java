package util;

/**
 * Created by Justinliu on 2017/6/15.
 */

public class HttpUtil {
    private static class HttpUtilBean {
        public static HttpUtil util = new HttpUtil();
    }

    private HttpUtil() {
    }

    public HttpUtil getHttpUtilInstance() {
        return HttpUtilBean.util;
    }

    public void getExecute(String url){

    }
}
