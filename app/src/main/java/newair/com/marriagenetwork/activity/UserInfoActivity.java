package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.utils.HideActionBar;


/**
 * ---------个人信息-----界面-----------
 */
public class UserInfoActivity extends BaseActivity {

    private ImageView iv_head_userInfoActivity;//头像
    private Button btn_edit_userInfoActivity;  //编辑按钮

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_user_info);
        initView();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_userInfoActivity://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏ActionBar
    }

    private void initView(){
        iv_head_userInfoActivity  = (ImageView) findViewById(R.id.iv_head_userInfoActivity);
        btn_edit_userInfoActivity = (Button)    findViewById(R.id.btn_edit_userInfoActivity);
    }

    private void initEvent(){

    }
}
