package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.viewpager.MyAuditVPAdapter;
import newair.com.marriagenetwork.fragment.AlreadyAuditFragment;
import newair.com.marriagenetwork.fragment.WaitAuditFragment;
import newair.com.marriagenetwork.utils.HideActionBar;

public class StaffAuditActivity extends BaseActivity {

    private PagerSlidingTabStrip psts_choose_auditActivity;//导航栏
    private ViewPager vp_container_auditActivity;          //容器
    private MyAuditVPAdapter myAuditVPAdapter;             //适配器
    private List<Fragment> myData;
    private List<String> myTtitle;

    @Override
    public void initWidget() {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_auditActivity://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_audit);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏ActionBar

        initView();
        initData();
        initEvent();

    }

    //初始化View
    private void initView(){

        psts_choose_auditActivity = (PagerSlidingTabStrip) findViewById(R.id.psts_choose_auditActivity);
        psts_choose_auditActivity.setTextSize(18);           //选中前字体大小
        psts_choose_auditActivity.setSelectedTabTextSize(18);//选中后字体大小

        vp_container_auditActivity = (ViewPager) findViewById(R.id.vp_container_auditActivity);
    }
    //初始化数据
    private void initData(){
        myData = new ArrayList<>();
        WaitAuditFragment wati       = new WaitAuditFragment();
        AlreadyAuditFragment already = new AlreadyAuditFragment();
        myData.add(wati);
        myData.add(already);

        myTtitle = new ArrayList<>();
        myTtitle.add("待审核");
        myTtitle.add("已审核");

        myAuditVPAdapter = new MyAuditVPAdapter(getSupportFragmentManager(),myData,myTtitle);
        vp_container_auditActivity.setAdapter(myAuditVPAdapter);
        psts_choose_auditActivity.setViewPager(vp_container_auditActivity);
    }
    //注册监听
    private void initEvent(){

    }
}
