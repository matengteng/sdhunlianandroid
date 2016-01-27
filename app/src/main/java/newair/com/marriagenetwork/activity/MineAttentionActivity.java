package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyMineAttentionAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;


/**
 * ------------"我关注的"-界面--------------
 */
public class MineAttentionActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {

    private PullToRefreshLayout ptrl_refresh_mineAttentionActivity;//下拉刷新
    private PullableListView plv_refresh_me_attention_activity;//ListViwe
    private MyMineAttentionAdapter myMineAttentionAdapter;     //适配器


    @Override
    public void initWidget() {
        setContentView(R.layout.activity_mine_attention);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_mineAttentionActivity://返回按钮
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
        ptrl_refresh_mineAttentionActivity = (PullToRefreshLayout) findViewById(R.id.ptrl_refresh_mineAttentionActivity);
        plv_refresh_me_attention_activity  = (PullableListView)    findViewById(R.id.plv_refresh_me_attention_activity);
    }
    //初始化数据源
    private void initData(){
        //初始化 适配器
        myMineAttentionAdapter = new MyMineAttentionAdapter(MineAttentionActivity.this,new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_me_attention_activity.setAdapter(myMineAttentionAdapter);//设置适配器
    }
    //初始化事件
    private void initEvent(){
        ptrl_refresh_mineAttentionActivity.setOnRefreshListener(this);
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
