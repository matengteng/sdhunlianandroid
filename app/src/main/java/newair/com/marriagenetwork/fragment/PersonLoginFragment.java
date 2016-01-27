package newair.com.marriagenetwork.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.activity.MainActivity;
import newair.com.marriagenetwork.activity.RegisterActivity;
import newair.com.marriagenetwork.application.ApplicationController;
import newair.com.marriagenetwork.entity.LoginModel;
import newair.com.marriagenetwork.helpr.AppConstants;
import newair.com.marriagenetwork.helpr.ProgressDialog;
import newair.com.marriagenetwork.threedes.EncryptionUtils;
import newair.com.marriagenetwork.usermanager.LoginUtils;
import newair.com.marriagenetwork.utils.T;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * --------个人登陆的Fragment---------
 */
public class PersonLoginFragment extends FragmentBase {

    //回调接口
    public interface PeopleLoginFragmentListener{
        public void peopleLoginCallBack();
    }
    private PeopleLoginFragmentListener listener;

    private Button btn_login_personFragment;   //登录按钮
    private Button btn_register_personFragment;//注册按钮
    private EditText et_name_loginFragment;    //用户名
    private EditText et_pwd_loginFragment;     //密码


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_login, container, false);

        initView(view);
        initEvent();

        return view;
    }


    //初始化控件
    private void initView(View view){
        btn_login_personFragment    = (Button)   view.findViewById(R.id.btn_login_personFragment);
        btn_register_personFragment = (Button)   view.findViewById(R.id.btn_register_personFragment);

        et_name_loginFragment       = (EditText) view.findViewById(R.id.et_name_loginFragment);
        et_pwd_loginFragment        = (EditText) view.findViewById(R.id.et_pwd_loginFragment);
    }
    //初始化事件
    private void initEvent(){
        //注册按钮
        btn_register_personFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getActivity(), RegisterActivity.class);
                startActivity(register);
            }
        });
        //登陆按钮
        btn_login_personFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_name_loginFragment.getText().toString().length() < 1 || et_pwd_loginFragment.getText().toString().length() < 1){
                    T.show(getActivity(),"用户名和密码不为能为空",Toast.LENGTH_LONG);
                }else {
                    User user = new User();
                    user.setUsername(et_name_loginFragment.getText().toString());//用户名
                    user.setPassword(et_pwd_loginFragment.getText().toString()); //密码

                    http_login(EncryptionUtils.threeDESEncode(new Gson().toJson(user)));//登陆请求
                }
            }
        });
    }

    private void http_login(String user){
        ApplicationController.getInstance().api.login(AppConstants.RESERVE_PARAMETER.APP_PLATFORM,AppConstants.RESERVE_PARAMETER.APP_VERSION,user,new Callback<LoginModel>() {
            @Override
            public void success(LoginModel loginModel, Response response) {
                if (loginModel.getStatus() != 1) {
                    T.show(getActivity(), loginModel.getMsg(), Toast.LENGTH_LONG);
                    mHandler.sendEmptyMessage(0);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",loginModel.getData());
                Message msg = mHandler.obtainMessage();
                msg.setData(bundle);
                msg.what = 1;//成功
                mHandler.sendMessage(msg);//登陆成功
            }

            @Override
            public void failure(RetrofitError error) {
                mHandler.sendEmptyMessage(0);
            }
        });
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:   //失败
                    ProgressDialog.getInstance().cancelProgressDialog(getActivity());
                    T.show(getActivity(),"登录失败，请重试",Toast.LENGTH_LONG);
                    break;
                case 1:   //成功
                    ProgressDialog.getInstance().cancelProgressDialog(getActivity());
                    Bundle bundle = msg.getData();
                    LoginModel.DataEntity dataEntity = bundle.getParcelable("data");
                    String user = dataEntity.getUser();
                    String token = EncryptionUtils.threeDESDecode(user);//解密得到token
                    LoginUtils.login(token);//保存token
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                    listener.peopleLoginCallBack();//回调，销毁LoginActivity
                    break;
            }
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (PeopleLoginFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement PeopleLoginFragmentListener");
        }
    }


    //加密要用
    private class User{
        private String username;
        private String password;

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
