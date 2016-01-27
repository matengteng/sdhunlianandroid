package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyAttentionMineAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;


/**
 * ----------"关注我的"--界面-------
 */
public class AttentionMineActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {

    private PullToRefreshLayout ptrl_refresh_attentionMineActivity;//下拉刷新
    private PullableListView plv_refresh_attentionMineActivity;    //ListView
    private MyAttentionMineAdapter myAttentionMineAdapter;         //适配器

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_attention_mine);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_attentionMineActivity://返回按钮
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
        ptrl_refresh_attentionMineActivity = (PullToRefreshLayout) findViewById(R.id.ptrl_refresh_attentionMineActivity);
        plv_refresh_attentionMineActivity  = (PullableListView)    findViewById(R.id.plv_refresh_attentionMineActivity);
    }
    //初始化数据
    private void initData(){
        myAttentionMineAdapter = new MyAttentionMineAdapter(AttentionMineActivity.this,new DataProvider(){

            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_attentionMineActivity.setAdapter(myAttentionMineAdapter);
    }
    //初始化事件
    private void initEvent(){

        ptrl_refresh_attentionMineActivity.setOnRefreshListener(this);
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
