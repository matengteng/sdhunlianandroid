package newair.com.marriagenetwork.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import newair.com.marriagenetwork.api.ServerAPI;
import newair.com.marriagenetwork.utils.L;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


/**
 * å
 * Created by mateng on 15/9/17.
 * Application控制器
 */
public class ApplicationController extends Application {

    private String API = "http://192.168.1.108";  // BASE URL
    public ServerAPI api;
    private static ApplicationController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API)
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        // 通过RestAdapter生成一个serverapi接口的实现
        api = restAdapter.create(ServerAPI.class);
        instance = this;
        //是否打开调试
        L.isDebug = true;

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);

        //初始化百度地图
        SDKInitializer.initialize(getApplicationContext());
    }


    /**
     * 获取application实例
     *
     * @return ApplicationController
     */
    public static ApplicationController getInstance() {
        return instance;
    }

}
