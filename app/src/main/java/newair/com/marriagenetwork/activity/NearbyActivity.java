package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.viewpager.MyNearbyVPAdapter;
import newair.com.marriagenetwork.fragment.NearbyListFragment;
import newair.com.marriagenetwork.fragment.NearbyMapFragment;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.autoscrollviewpager.ViewPagerCompat;


/**
 * -------“附近的人”-界面---------
 */
public class NearbyActivity extends BaseActivity {


    private PagerSlidingTabStrip psts_choose_nearbyActivity;//导航栏
    private ViewPagerCompat vp_container_nearbyActivity;          //容器
    private MyNearbyVPAdapter myNearbyVPAdapter;            //适配器
    private List<Fragment> myData;
    private List<String> myTitle;



    @Override
    public void initWidget() {
        setContentView(R.layout.activity_nearby);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_nearbyActivity://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏ActionBar

    }

    //初始化View
    private void initView(){

        psts_choose_nearbyActivity = (PagerSlidingTabStrip) findViewById(R.id.psts_choose_nearbyActivity);
        psts_choose_nearbyActivity.setTextSize(18);           //选中前字体大小
        psts_choose_nearbyActivity.setSelectedTabTextSize(18);//选中后字体大小

        vp_container_nearbyActivity = (ViewPagerCompat) findViewById(R.id.vp_container_nearbyActivity);
    }
    //初始化事件
    private void initData(){
        myData = new ArrayList<>();
        NearbyListFragment nearbyListFragment = new NearbyListFragment();
        NearbyMapFragment nearbyMapFragment  = new NearbyMapFragment();
        myData.add(nearbyListFragment);
        myData.add(nearbyMapFragment);

        myTitle = new ArrayList<>();
        myTitle.add("列表");
        myTitle.add("地图");

        myNearbyVPAdapter = new MyNearbyVPAdapter(getSupportFragmentManager(),myData,myTitle);
        vp_container_nearbyActivity.setAdapter(myNearbyVPAdapter);
        psts_choose_nearbyActivity.setViewPager(vp_container_nearbyActivity);
    }
    //初始化事件
    private void initEvent(){

    }


}
