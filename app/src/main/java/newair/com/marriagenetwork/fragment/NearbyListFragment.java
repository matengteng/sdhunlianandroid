package newair.com.marriagenetwork.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyNearbyListAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;


/**
 * -------附近的人---列表-Fragment--------
 */
public class NearbyListFragment extends FragmentBase implements PullToRefreshLayout.OnRefreshListener {

    private PullToRefreshLayout ptrl_refresh_nearbyList_fragment;//下拉刷新
    private PullableListView plv_refresh_nearbyList_fragment;    //listView
    private MyNearbyListAdapter myNearbyListAdapter;             //适配器



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nearby_list, container, false);

        initView(view);
        initData();
        initEvent();

        return view;
    }

    //初始化View
    private void initView(View view){
        ptrl_refresh_nearbyList_fragment = (PullToRefreshLayout) view.findViewById(R.id.ptrl_refresh_nearbyList_fragment);
        plv_refresh_nearbyList_fragment  = (PullableListView)    view.findViewById(R.id.plv_refresh_nearbyList_fragment);
    }

    //初始化数据
    private void initData(){
        myNearbyListAdapter = new MyNearbyListAdapter(getActivity(),new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_nearbyList_fragment.setAdapter(myNearbyListAdapter);//设置适配器
    }
    //初始化事件
    private void initEvent(){
        ptrl_refresh_nearbyList_fragment.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        //下拉刷新
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        //上拉加载
    }



}
