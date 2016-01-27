package newair.com.marriagenetwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.application.ApplicationController;
import newair.com.marriagenetwork.entity.RegisterModel;
import newair.com.marriagenetwork.helpr.AppConstants;
import newair.com.marriagenetwork.helpr.ProgressDialog;
import newair.com.marriagenetwork.threedes.EncryptionUtils;
import newair.com.marriagenetwork.usermanager.LoginUtils;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.utils.T;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * -------注册--界面-----------
 */
public class RegisterActivity extends BaseActivity {


    private EditText et_username_registerActivity;//用户名
    private EditText et_pwd_registerActivity;     //密码
    private EditText et_sure_pwd_registerActivity;//确认密码
    private CheckBox cb_checked_registerActivity; //是否选中

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish_registerActivity:   //返回按钮
                finish();
                break;
            case R.id.btn_register_registerActivity://注册按钮
                if (cb_checked_registerActivity.isChecked()) {
                    if (!et_username_registerActivity.getText().toString().equals("")) {//用户名不能为空
                        if (et_pwd_registerActivity.getText().toString().equals("") || et_sure_pwd_registerActivity.getText().toString().equals("")) {
                            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            if (et_pwd_registerActivity.getText().toString().equals(et_sure_pwd_registerActivity.getText().toString())) {
                                //用于加密
                                User user = new User();
                                user.setUsername(et_username_registerActivity.getText().toString());
                                user.setPassword(et_pwd_registerActivity.getText().toString());

                                String user_des = EncryptionUtils.threeDESEncode(new Gson().toJson(user));//加密后的串

                                http_register(user_des);//执行 注册请求

                            } else {
                                Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else {
                    T.show(RegisterActivity.this,"请同意本协议",Toast.LENGTH_LONG);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏ActionBar
    }

    private void initView() {
        et_username_registerActivity = (EditText) findViewById(R.id.et_username_registerActivity);
        et_pwd_registerActivity = (EditText) findViewById(R.id.et_pwd_registerActivity);
        et_sure_pwd_registerActivity = (EditText) findViewById(R.id.et_sure_pwd_registerActivity);
        cb_checked_registerActivity = (CheckBox) findViewById(R.id.cb_checked_registerActivity);
    }

    private void initEvent() {

    }

    //注册的网络请求
    private void http_register(final String user) {
        ProgressDialog.getInstance().startProgressDialog(this);
        ApplicationController.getInstance().api.register(AppConstants.RESERVE_PARAMETER.APP_PLATFORM, AppConstants.RESERVE_PARAMETER.APP_VERSION, user, new Callback<RegisterModel>() {
            @Override
            public void success(RegisterModel registerModel, Response response) {
                if (registerModel.getStatus() != 1) {
                    T.show(RegisterActivity.this, registerModel.getMsg(), Toast.LENGTH_LONG);
                    ProgressDialog.getInstance().cancelProgressDialog(RegisterActivity.this);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",registerModel.getData());
                Message msg = mHandler.obtainMessage();
                msg.setData(bundle);
                msg.what = 1;
                mHandler.sendMessage(msg);//注册成功
            }

            @Override
            public void failure(RetrofitError error) {
                mHandler.sendEmptyMessage(0);//注册失败
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:   //注册失败
                    ProgressDialog.getInstance().cancelProgressDialog(RegisterActivity.this);
                    T.show(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT);
                    break;
                case 1:   //注册成功
                    ProgressDialog.getInstance().cancelProgressDialog(RegisterActivity.this);
                    T.show(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT);
                    //获取传过来的参数
                    Bundle bundle = msg.getData();
                    RegisterModel.DataEntity data = bundle.getParcelable("data");
                    String user = data.getUser();
                    String token = EncryptionUtils.threeDESDecode(user);//获得token
                    LoginUtils.login(token);//保存token
                    //跳转完善用户信息界面
                    Intent complete = new Intent(RegisterActivity.this, CompleteInfoActivity.class);
                    startActivity(complete);
                    break;
            }
        }
    };


    //加密user
    private class User {
        private String username; //用户名
        private String password;//密码

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
