package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;

import java.util.ArrayList;
import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.viewpager.MyMainVPAdapter;
import newair.com.marriagenetwork.application.ApplicationController;
import newair.com.marriagenetwork.fragment.ChatFragment;
import newair.com.marriagenetwork.fragment.FindFragment;
import newair.com.marriagenetwork.fragment.HomeFragment;
import newair.com.marriagenetwork.fragment.MineFragment;
import newair.com.marriagenetwork.utils.HideActionBar;


/**
 * ---------------主界面---------------
 */
public class MainActivity extends BaseActivity {

    private ViewPager vp_container_mainActivity; //盛放四个Fragment
    private MyMainVPAdapter myMainVPAdapter;     //ViewPager适配器
    private List<Fragment>  myData;              //数据源

    private RadioGroup rg_container_mainActivity;//底部四个按钮的容器
    private RadioButton rbtn_home_mainActivity;  //首页
    private RadioButton rbtn_find_mainActivity;  //发现
    private RadioButton rbtn_chat_mainActivity;  //爱聊
    private RadioButton rbtn_mine_mainActivity;  //我的

    //上传位置信息--所需
    public LocationClient mLocationClient = null;
    private MyBDLocationListener myBDLocationListener = new MyBDLocationListener();
    private LatLng myLatLng;//记录我的位置

    //用于周边雷达上传
    private RadarSearchManager mManager;//周边雷达核心
    private MyRadarSearchListener listener;//周边雷达功能模块监听

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_main);
        initView(); //初始化View
        initData(); //初始化数据
        initEvent();//初始化事件
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏标题栏

        //定位相关
        mLocationClient = new LocationClient(ApplicationController.getInstance().getApplicationContext());//声明LocationClient类
        mLocationClient.registerLocationListener(myBDLocationListener);//注册监听函数
        initLocation();//初始化定位设置
        mLocationClient.start();//开启定位

        //雷达相关
        mManager = RadarSearchManager.getInstance();//初始化雷达模块
        listener = new MyRadarSearchListener();  //初始化监听


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除监听
        mManager.removeNearbyInfoListener(listener);
        //清除用户信息
        mManager.clearUserInfo();
        //释放资源
        mManager.destroy();
        mManager = null;
    }

    //初始化控件
    private void initView(){
        vp_container_mainActivity = (ViewPager)   findViewById(R.id.vp_container_mainActivity);

        rg_container_mainActivity = (RadioGroup)  findViewById(R.id.rg_container_mainActivity);
        rbtn_home_mainActivity    = (RadioButton) findViewById(R.id.rbtn_home_mainActivity);
        rbtn_find_mainActivity    = (RadioButton) findViewById(R.id.rbtn_find_mainActivity);
        rbtn_chat_mainActivity    = (RadioButton) findViewById(R.id.rbtn_chat_mainActivity);
        rbtn_mine_mainActivity    = (RadioButton) findViewById(R.id.rbtn_mine_mainActivity);
    }
    //初始化数据
    private void initData(){
        myData = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        FindFragment findFragment = new FindFragment();
        ChatFragment chatFragment = new ChatFragment();
        MineFragment mineFragment = new MineFragment();
        myData.add(homeFragment);
        myData.add(findFragment);
        myData.add(chatFragment);
        myData.add(mineFragment);
        myMainVPAdapter = new MyMainVPAdapter(getSupportFragmentManager(),myData);
        vp_container_mainActivity.setAdapter(myMainVPAdapter);
        //默认选择第一个
        vp_container_mainActivity.setCurrentItem(0);
        rg_container_mainActivity.check(R.id.rbtn_home_mainActivity);
    }
    //初始化事件
    private void initEvent(){
        //RadioGroup四个按钮状态改变时的监听
        rg_container_mainActivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_home_mainActivity://首页
                        vp_container_mainActivity.setCurrentItem(0,true);
                        break;
                    case R.id.rbtn_find_mainActivity://返现
                        vp_container_mainActivity.setCurrentItem(1,true);
                        break;
                    case R.id.rbtn_chat_mainActivity://爱聊
                        vp_container_mainActivity.setCurrentItem(2,true);
                        break;
                    case R.id.rbtn_mine_mainActivity://我的
                        vp_container_mainActivity.setCurrentItem(3,true);
                        break;
                }
            }
        });
        //ViewPager滑动时监听
        vp_container_mainActivity.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0://首页
                        rg_container_mainActivity.check(R.id.rbtn_home_mainActivity);
                        break;
                    case 1://发现
                        rg_container_mainActivity.check(R.id.rbtn_find_mainActivity);
                        break;
                    case 2://爱聊
                        rg_container_mainActivity.check(R.id.rbtn_chat_mainActivity);
                        break;
                    case 3://我的
                        rg_container_mainActivity.check(R.id.rbtn_mine_mainActivity);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }



    //初始化定位信息
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(2000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
    //上传用户信息
    private void upLoadUserInfo(LatLng myLatLng){
        mManager.addNearbyInfoListener(listener);//设置监听
        mManager.setUserID("56789");//设置上传的用户标识（null为设置ID）
        //上传位置
        RadarUploadInfo info = new RadarUploadInfo();
        String jiami = "iMkRTwdhyLynSBXDFpEDJMlObx5HR6aNmyEFydTzw0Pw4x9wxPNkhuvrpNH69j%2BRjdtzn6ORDbklnAubFPFWMw9CN9eFn%2BWDqtzm1I7rq6q5w8iznb7fP4Fy0C5GGg%2BWmz6%2FSszSyxEzxj55LwC4vx5c9Dhx21k%2Fb5Be%2B3TWKLGW3G8xbQkxLW6wf4lAUh3wfAaRM8UC0Lg%3D";
        info.comments = jiami;//拓展信息
        info.pt = myLatLng;   //我的位置LatLng
        mManager.uploadInfoRequest(info);
    }
    //定位监听回调
    private class MyBDLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(bdLocation.getTime());
            sb.append("\nerror code : ");
            sb.append(bdLocation.getLocType());
            sb.append("\nlatitude : ");
            sb.append(bdLocation.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(bdLocation.getLongitude());
            sb.append("\nradius : ");
            sb.append(bdLocation.getRadius());
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(bdLocation.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(bdLocation.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(bdLocation.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(bdLocation.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(bdLocation.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

                myLatLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());//设置我的坐标
                mLocationClient.stop();//停止定位
//                upLoadUserInfo(myLatLng);

            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
                sb.append("\naddr : ");
                sb.append(bdLocation.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(bdLocation.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

                myLatLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());//设置我的坐标
                mLocationClient.stop();//停止定位
//                upLoadUserInfo(myLatLng);

            } else if (bdLocation.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

                myLatLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());//设置我的坐标
                mLocationClient.stop();//停止定位
//                upLoadUserInfo(myLatLng);

            } else if (bdLocation.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (bdLocation.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(bdLocation.getLocationDescribe());// 位置语义化信息
            List<Poi> list = bdLocation.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }
    //周边雷达上传监听
    private class MyRadarSearchListener implements RadarSearchListener{

        //获取用户信息
        @Override
        public void onGetNearbyInfoList(RadarNearbyResult radarNearbyResult, RadarSearchError radarSearchError) {

        }
        //上传是否成功
        @Override
        public void onGetUploadState(RadarSearchError radarSearchError) {
            if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
                //上传成功
                Toast.makeText(MainActivity.this, "单次上传位置成功", Toast.LENGTH_LONG).show();
            } else {
                //上传失败
                Toast.makeText(MainActivity.this, "单次上传位置失败", Toast.LENGTH_LONG).show();
            }
        }
        //清除用户信息
        @Override
        public void onGetClearInfoState(RadarSearchError radarSearchError) {

        }
    }
}
