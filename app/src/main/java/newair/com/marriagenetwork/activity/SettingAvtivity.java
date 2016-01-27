package newair.com.marriagenetwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.utils.HideActionBar;


public class SettingAvtivity extends BaseActivity implements View.OnClickListener {


    private RelativeLayout rl_user_info_settingActivity;    //编辑用户资料
    private RelativeLayout rl_alter_pwd_settingActivity;    //修改密码
    private RelativeLayout rl_alert_phone_settingActivity;  //变更手机
    private RelativeLayout rl_alert_company_settingActivity;//变更单位
    private RelativeLayout rl_msg_warn_settingActivity;     //消息提醒
    private RelativeLayout rl_feed_back_settingActivity;    //用户反馈
    private RelativeLayout rl_about_my_settingActivity;     //关于我们

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_setting_avtivity);

        initView();
        initEvent();

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_settingActivity://返回按钮
                finish();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏标题栏
    }

    //初始化控件
    private void initView(){

        rl_user_info_settingActivity     = (RelativeLayout) findViewById(R.id.rl_user_info_settingActivity);
        rl_alter_pwd_settingActivity     = (RelativeLayout) findViewById(R.id.rl_alter_pwd_settingActivity);
        rl_alert_phone_settingActivity   = (RelativeLayout) findViewById(R.id.rl_alert_phone_settingActivity);
        rl_alert_company_settingActivity = (RelativeLayout) findViewById(R.id.rl_alert_company_settingActivity);
        rl_msg_warn_settingActivity      = (RelativeLayout) findViewById(R.id.rl_msg_warn_settingActivity);
        rl_feed_back_settingActivity     = (RelativeLayout) findViewById(R.id.rl_feed_back_settingActivity);
        rl_about_my_settingActivity      = (RelativeLayout) findViewById(R.id.rl_about_my_settingActivity);
    }
    //初始化事件
    private void initEvent(){


        rl_user_info_settingActivity.setOnClickListener(this);
        rl_alter_pwd_settingActivity.setOnClickListener(this);
        rl_alert_phone_settingActivity.setOnClickListener(this);
        rl_alert_company_settingActivity.setOnClickListener(this);
        rl_msg_warn_settingActivity.setOnClickListener(this);
        rl_feed_back_settingActivity.setOnClickListener(this);
        rl_about_my_settingActivity.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_user_info_settingActivity:    //个人资料编辑
                Intent user_info = new Intent(SettingAvtivity.this,UserInfoActivity.class);
                startActivity(user_info);
                break;
            case R.id.rl_alter_pwd_settingActivity:    //修改密码
                break;
            case R.id.rl_alert_phone_settingActivity:  //变更手机号
                break;
            case R.id.rl_alert_company_settingActivity://变更单位
                break;
            case R.id.rl_msg_warn_settingActivity:     //消息提醒
                break;
            case R.id.rl_feed_back_settingActivity:    //用户反馈
                break;
            case R.id.rl_about_my_settingActivity:     //关于我们
                break;
        }
    }
}
