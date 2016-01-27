package newair.com.marriagenetwork.usermanager;

import android.content.Context;
import android.content.Intent;

import newair.com.marriagenetwork.activity.LoginActivity;
import newair.com.marriagenetwork.application.ApplicationController;
import newair.com.marriagenetwork.helpr.AppConstants;
import newair.com.marriagenetwork.utils.SPUtils;
import newair.com.marriagenetwork.utils.ValidatorUtils;

/**
 * 登录工具类
 * Created by mateng on 15/10/13.
 */
public class LoginUtils {

    /**
     * 登录
     *
     * @param token
     */
    public static void login(String token) {

        //记住登录状态
        SPUtils.put(ApplicationController.getInstance().getApplicationContext(), AppConstants.CONSTANT_SHARED.TOKEN_SHARED, token);
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        if (ValidatorUtils.isEmptyIgnoreBlank(SPUtils.get(ApplicationController.getInstance().getApplicationContext(), AppConstants.CONSTANT_SHARED.TOKEN_SHARED, "") + "")) {
            return false;
        }
        return true;
    }

    /**
     * 判断如果未登录到登录界面
     * @param context
     * @return
     */
    public static boolean isLoginAndTogoLogin(Context context) {
        if (ValidatorUtils.isEmptyIgnoreBlank(SPUtils.get(context, AppConstants.CONSTANT_SHARED.TOKEN_SHARED, "") + "")) {
            context.startActivity(new Intent(context, LoginActivity.class));
            return false;
        }
        return true;
    }

    /**
     * 登出
     *
     * @param context
     */
    public static void logout(Context context) {

        SPUtils.remove(context, AppConstants.CONSTANT_SHARED.TOKEN_SHARED);

    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() {
        if (isLogin())
            return SPUtils.get(ApplicationController.getInstance().getApplicationContext(), AppConstants.CONSTANT_SHARED.TOKEN_SHARED, "") + "";
        return null;
    }

}
