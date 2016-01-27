package newair.com.marriagenetwork.helpr;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by mateng on 16/1/18.
 */
public class ProgressDialog {


    private static ProgressDialog instance = null;

    public static ProgressDialog getInstance() {
        if (instance == null) {
            synchronized (ProgressDialog.class) {
                if (instance == null) {
                    instance = new ProgressDialog();
                }
            }
        }
        return instance;
    }

    private ProgressDialog() {
    }

    //网络加载progress的实例
    private SweetAlertDialog swDialog;

    /**
     * 获取网络加载Dialog实例
     *
     * @return
     */
    private SweetAlertDialog getSweetAlertDialog(Context context) {
        synchronized (context) {
            if (swDialog != null)
                return swDialog;
            //初始化网络加载dialog
            swDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            //设置加载条颜色
            swDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            //动画下方显示的标题
            swDialog.setTitleText("Loading");
            //dialog是否可以按返回键关闭
            swDialog.setCancelable(false);
            return swDialog;
        }
    }

    /**
     * 开始网络加载动画
     */
    public void startProgressDialog(Context context) {
        getSweetAlertDialog(context).show();
    }

    /**
     * 关闭网络加载动画
     */
    public void cancelProgressDialog(Context context) {
        if (getSweetAlertDialog(context).isShowing())
            getSweetAlertDialog(context).dismiss();
    }

}
