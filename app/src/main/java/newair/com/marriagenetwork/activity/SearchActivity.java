package newair.com.marriagenetwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.viewpager.MySearchVPAdapter;
import newair.com.marriagenetwork.fragment.ConditionSearchFragment;
import newair.com.marriagenetwork.fragment.NameSearchFragment;
import newair.com.marriagenetwork.utils.HideActionBar;


/**
 * --------“搜索”-界面---------
 */
public class SearchActivity extends BaseActivity implements NameSearchFragment.NameSearchCallBackListener,ConditionSearchFragment.ConditionSearchCallBackListener {

    private PagerSlidingTabStrip psts_choose_searchActivity;//导航栏
    private ViewPager vp_container_searchActivity;          //盛放Fragment的容器
    private MySearchVPAdapter mySearchVPAdapter;            //适配器
    private List<Fragment> myData;                          //界面Fragment
    private List<String> myTitle;                           //标题


    @Override
    public void initWidget() {
        setContentView(R.layout.activity_search);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_searchActivity://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏actionbar



    }

    //初始化View
    private void initView(){

        psts_choose_searchActivity = (PagerSlidingTabStrip) findViewById(R.id.psts_choose_searchActivity);
        psts_choose_searchActivity.setTextSize(18);           //选中前字体大小
        psts_choose_searchActivity.setSelectedTabTextSize(18);//选中后字体大小

        vp_container_searchActivity = (ViewPager) findViewById(R.id.vp_container_searchActivity);
    }
    //初始化数据
    private void initData(){
        myData = new ArrayList<>();
        ConditionSearchFragment condition = new ConditionSearchFragment();
        NameSearchFragment name = new NameSearchFragment();
        myData.add(condition);
        myData.add(name);

        myTitle = new ArrayList<>();
        myTitle.add("条件搜索");
        myTitle.add("姓名搜索");

        mySearchVPAdapter = new MySearchVPAdapter(getSupportFragmentManager(),myData,myTitle);
        vp_container_searchActivity.setAdapter(mySearchVPAdapter);
        psts_choose_searchActivity.setViewPager(vp_container_searchActivity);

    }
    //初始化事件
    private void initEvent(){

    }

    //姓名搜索的回调函数
    @Override
    public void transName(String name) {
        Intent name_search = new Intent("name_search");
        name_search.putExtra("name",name);
        setResult(2,name_search);
        finish();
    }

    //条件搜索的回调函数
    @Override
    public void transConditions(Bundle condition) {
        Intent condition_search = new Intent("condition_search");
        condition_search.putExtra("condition_search",condition);
        setResult(1,condition_search);
        finish();
    }
}
