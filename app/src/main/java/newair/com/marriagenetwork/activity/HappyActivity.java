package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyHappyPLVAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;


/**
 * ----------欢乐趴-------------
 */
public class HappyActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {

    private PullToRefreshLayout ptrl_refresh_happyActivity;//下拉刷新
    private PullableListView plv_refresh_happyActivity;//ListView
    private MyHappyPLVAdapter myHappyPLVAdapter;       //适配器

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_happy);

        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_happyActivity://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());
    }

    //初始化控件
    private void initView(){

        ptrl_refresh_happyActivity = (PullToRefreshLayout) findViewById(R.id.ptrl_refresh_happyActivity);
        plv_refresh_happyActivity  = (PullableListView)    findViewById(R.id.plv_refresh_happyActivity);

    }
    //初始化数据
    private void initData(){
        myHappyPLVAdapter = new MyHappyPLVAdapter(HappyActivity.this,new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_happyActivity.setAdapter(myHappyPLVAdapter);//设置适配器
    }
    //初始化事件
    private void initEvent(){
        ptrl_refresh_happyActivity.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        //下拉刷新

    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        //上拉刷新

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };
}
