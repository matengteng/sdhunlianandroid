package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyRecommentPLVAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;

/**
 * ------------“近日推荐”-界面------------
 */
public class RecommentActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {


    private PullToRefreshLayout ptrl_refresh_recomment_activity;//上下拉刷新
    private PullableListView plv_refresh_recommentActivity;     //ListView
    private MyRecommentPLVAdapter myRecommentAdapter;           //适配器

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_recomment);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_reCommentActivity://返回按钮
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
        ptrl_refresh_recomment_activity = (PullToRefreshLayout) findViewById(R.id.ptrl_refresh_recomment_activity);

        plv_refresh_recommentActivity   = (PullableListView)    findViewById(R.id.plv_refresh_recommentActivity);
    }
    //初始化数据
    private void initData(){
        //初始化适配器
        myRecommentAdapter = new MyRecommentPLVAdapter(RecommentActivity.this,new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_recommentActivity.setAdapter(myRecommentAdapter);//设置适配器
    }
    //初始化事件
    private void initEvent(){
        ptrl_refresh_recomment_activity.setOnRefreshListener(this);
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
