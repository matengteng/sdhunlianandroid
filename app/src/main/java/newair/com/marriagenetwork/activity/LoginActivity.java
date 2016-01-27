package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.viewpager.MyLoginVPAdapter;
import newair.com.marriagenetwork.fragment.CompanyLoginFragment;
import newair.com.marriagenetwork.fragment.PersonLoginFragment;
import newair.com.marriagenetwork.utils.HideActionBar;


/**
 * ----------"登录"-界面------------
 */
public class LoginActivity extends BaseActivity implements PersonLoginFragment.PeopleLoginFragmentListener {

    private PagerSlidingTabStrip psts_choose_loginActivity;//导航栏
    private ViewPager vp_container_loginActivity;          //盛放Fragment的容器
    private MyLoginVPAdapter myLoginVPAdapter;             //ViewPager适配器
    private List<Fragment> myData;                         //Fragment数据源
    private List<String>   myTitle;                        //标题

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_login);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_loginActivity://返回按钮
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

        psts_choose_loginActivity  = (PagerSlidingTabStrip) findViewById(R.id.psts_choose_loginActivity);
        psts_choose_loginActivity.setTextSize(18);           //选中前字体大小
        psts_choose_loginActivity.setSelectedTabTextSize(18);//选中后字体大小
        vp_container_loginActivity = (ViewPager) findViewById(R.id.vp_container_loginActivity);

    }
    //初始化数据
    private void initData(){
        //Fragment数据源
        myData = new ArrayList<>();
        PersonLoginFragment person   = new PersonLoginFragment();
        CompanyLoginFragment company = new CompanyLoginFragment();
        myData.add(person);
        myData.add(company);
        //标题
        myTitle = new ArrayList<>();
        myTitle.add("个人");
        myTitle.add("单位");

        myLoginVPAdapter = new MyLoginVPAdapter(getSupportFragmentManager(),myData,myTitle);
        vp_container_loginActivity.setAdapter(myLoginVPAdapter);
        psts_choose_loginActivity.setViewPager(vp_container_loginActivity);
    }
    //初始化事件
    private void initEvent(){

    }

    //个人登陆的回调接口
    @Override
    public void peopleLoginCallBack() {
        finish();
    }
}
