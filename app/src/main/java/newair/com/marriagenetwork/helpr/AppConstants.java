package newair.com.marriagenetwork.helpr;

/**
 * Created by mateng on 16/1/18.
 */
public class AppConstants {


    /**
     *  SharedPreferences 保存的key
     */
    public static class CONSTANT_SHARED{

        //保存token的key
        public static final String TOKEN_SHARED = "token";

    }
    /**
     * 网络请求地址
     */
    public static class CONSTANT_URLS {

        //测试连接
        public static final String LOCAL_URL = "http://map.google.com";
        //线上连接
        public static final String LIVE_URL = "https://api.google.com";

        public static final String LIVE_BASIC_URL = LIVE_URL;

    }

    /**
     * 网络请求接口预留参数
     */
    public static class RESERVE_PARAMETER {

        //版本号
        public static final String APP_VERSION = "1.0";
        //客户端平台
        public static final String APP_PLATFORM = "android";
        //APP语言
        public static final String APP_LAN = "zh_cn";

    }
    /**
     * 网络请求接口参数
     */
    public static class CONSTANT_PARAMETER{

        //用户token
        public static final String USER_TOKEN = "token";

    }

}
