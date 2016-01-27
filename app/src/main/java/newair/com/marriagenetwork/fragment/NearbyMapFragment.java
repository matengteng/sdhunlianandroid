package newair.com.marriagenetwork.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.application.ApplicationController;


/**
 * ------附近的人--地图--Fragment-------
 */
public class NearbyMapFragment extends FragmentBase {

    private MapView mv_showInfo_mapFragment = null;
    private BaiduMap mBaiduMap = null;

    public LocationClient mLocationClient = null;
    private MyBDLocationListener myBDLocationListener = new MyBDLocationListener();

    //用于周边雷达上传
    private RadarSearchManager mManager;//周边雷达核心
    private MyRadarSearchListener listener;//周边雷达功能模块监听

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nearby_map, container, false);

        initView(view);      //初始化控件
        initMapViewSetting();//设置地图显示的UI

        mLocationClient = new LocationClient(ApplicationController.getInstance().getApplicationContext());//声明LocationClient类
        mLocationClient.registerLocationListener(myBDLocationListener);//注册监听函数
        initLocation();         //设置定位信息
        mLocationClient.start();//开启定位


        mManager = RadarSearchManager.getInstance();
        listener = new MyRadarSearchListener();
        mManager.addNearbyInfoListener(listener);

        return view;
    }

    //初始化控件
    private void initView(View view){
        mv_showInfo_mapFragment = (MapView) view.findViewById(R.id.mv_showInfo_mapFragment);
    }
    //设置地图显示的UI
    private void initMapViewSetting(){
        mBaiduMap = mv_showInfo_mapFragment.getMap();//获得地图控制类
        mv_showInfo_mapFragment.showZoomControls(false);//不显示缩放按钮
        //设置缩放等级
        MapStatusUpdate zoomLevel = MapStatusUpdateFactory.zoomTo(16);
        mBaiduMap.animateMapStatus(zoomLevel);
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }
    //地图显示用户位置
    private void showUserLocation(BDLocation bdLocation){
        //构造定位数据
        MyLocationData locationData = new  MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude())
                .build();
        mBaiduMap.setMyLocationEnabled(true);//开启定位图层
        mBaiduMap.setMyLocationData(locationData);//设置定位数据

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.rbtn_checked_true_main);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);

        // 当不需要定位图层时关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mv_showInfo_mapFragment.onDestroy();

    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mv_showInfo_mapFragment.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mv_showInfo_mapFragment.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mLocationClient.stop();
    }

    //初始化定位监听
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

    //定位监听回调
    private class MyBDLocationListener implements BDLocationListener{

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

                showUserLocation(bdLocation);//地图显示用户位置
                mLocationClient.stop();
                showUserLocation(bdLocation);//地图显示用户位置

                //构造请求参数，其中centerPt是自己的位置坐标
                RadarNearbySearchOption option = new RadarNearbySearchOption()
                        .centerPt(new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude()))
                        .pageNum(0)
                        .radius(8000);
                //发起查询请求
                mManager.nearbyInfoRequest(option);

            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
                sb.append("\naddr : ");
                sb.append(bdLocation.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(bdLocation.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

                mLocationClient.stop();
                showUserLocation(bdLocation);//地图显示用户位置

                Log.d("aaaaaaaa","A");
                //构造请求参数，其中centerPt是自己的位置坐标
                RadarNearbySearchOption option = new RadarNearbySearchOption()
                        .centerPt(new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude()))
                        .pageNum(0)
                        .radius(8000);
                Log.d("aaaaaaaa","B");
                //发起查询请求
                mManager.nearbyInfoRequest(option);
                Log.d("aaaaaaaa","C");

            } else if (bdLocation.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

                showUserLocation(bdLocation);//地图显示用户位置

                mLocationClient.stop();
                showUserLocation(bdLocation);//地图显示用户位置

                //构造请求参数，其中centerPt是自己的位置坐标
                RadarNearbySearchOption option = new RadarNearbySearchOption()
                        .centerPt(new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude()))
                        .pageNum(0)
                        .radius(8000);
                //发起查询请求
                mManager.nearbyInfoRequest(option);

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
    private class MyRadarSearchListener implements RadarSearchListener {

        //获取用户信息
        @Override
        public void onGetNearbyInfoList(RadarNearbyResult radarNearbyResult, RadarSearchError radarSearchError) {
            Log.d("aaaaaaaa","进来了");
            if (radarSearchError == RadarSearchError.RADAR_NO_ERROR){
                for (int i = 0;i < radarNearbyResult.infoList.size();i++){
                    Log.d("aaaaaaaa",radarNearbyResult.infoList.get(i).comments.replace("0.000000","/"));
                }
            }else {
                Log.d("aaaaaaaa","结果查询失败");
            }
        }
        //上传是否成功
        @Override
        public void onGetUploadState(RadarSearchError radarSearchError) {
            if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
                //上传成功
                Toast.makeText(getActivity(), "单次上传位置成功", Toast.LENGTH_LONG).show();
            } else {
                //上传失败
                Toast.makeText(getActivity(), "单次上传位置失败", Toast.LENGTH_LONG).show();
            }
        }
        //清除用户信息
        @Override
        public void onGetClearInfoState(RadarSearchError radarSearchError) {

        }
    }
}
