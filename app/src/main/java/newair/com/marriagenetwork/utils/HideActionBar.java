package newair.com.marriagenetwork.utils;


import android.support.v7.app.ActionBar;

/**
 * Created by Administrator on 2016/1/19.
 */
public class HideActionBar {
    /**
     * *********隐藏ActionBar********
     * @param actionBar
     */
    public static void hideActionBar(ActionBar actionBar){
        if (actionBar != null){
            actionBar.hide();
        }
    }
}
