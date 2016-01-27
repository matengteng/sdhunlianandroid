package newair.com.marriagenetwork.api;


import newair.com.marriagenetwork.entity.LoginModel;
import newair.com.marriagenetwork.entity.RegisterModel;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by mateng on 16/1/18.
 * 网络请求接口定义类
 */
public interface ServerAPI {

    /**
     * -------------------注册接口------------------
     * @param platform 平台信息(ios/android)
     * @param version  版本号
     * @param user     加密后的串
     */
    @FormUrlEncoded
    @POST("/user/register/")
    void register(@Field("platform") String platform,@Field("version") String version,@Field("user") String user, Callback<RegisterModel> result);

    /**
     * -----------------登陆接口--------------------
     * @param platform 平台信息(iso/android)
     * @param version  版本号
     * @param user     加密后的串
     */
    @FormUrlEncoded
    @POST("/user/login/")
    void login(@Field("platform") String platform,@Field("version") String version,@Field("user") String user,Callback<LoginModel> result);

}
