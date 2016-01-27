package newair.com.marriagenetwork.application;

import android.app.Activity;

import java.util.HashMap;

/**
 * Created by mateng on 15/9/18.
 * activity的管理类
 */
public class AppManager {

    //存放所有打开的activity,key为activity的class
    private HashMap<String,Activity> activities = new HashMap<String,Activity>();

    private static AppManager instance = new AppManager();


    public static AppManager getInstance() {
        return instance;
    }

    /**
     * 获取所有打开的activity。
     * @return 存放activity的activities
     */
    public HashMap<String,Activity> getActivities() {

        return activities;

    }

    /**
     * 添加一个activity到activities里
     * @param classKey 要添加的activity的class
     * @param activity 要添加的activity
     */
    public void addActivity(Object classKey ,Activity activity) {

        activities.put(classKey.toString(),activity);

    }

    /**
     * 从activities中移除activity通过key
     * @param classKey key
     */
    public void removeActivity(Object classKey){

        activities.remove(classKey.toString());

    }

    /**
     * 获取activities的长度
     * @return
     */
    public int getActivitiesSize(){
        return activities.size();
    }
}
