package newair.com.marriagenetwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MySearchResultAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;


/**
 * -----------搜索结果页-----------
 * 应该判断是那个个Fragment跳转过来的
 * 不同的Fragment传递的参数不一样
 * 故，应该判断一下，根据参数的不同，执行不同的操作
 */
public class SearchResultActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {

    private LinearLayout ll_condition_searchResultActivity;//搜索条件
    private PullToRefreshLayout ptrl_refresh_searchResultActivity;//上下拉刷新
    private PullableListView plv_refresh_searchResultActivity;//ListView
    private MySearchResultAdapter mySearchResultAdapter;//适配器

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_search_result);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_resultActivity://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏标题栏



    }

    //初始化View
    private void initView(){
        ptrl_refresh_searchResultActivity = (PullToRefreshLayout) findViewById(R.id.ptrl_refresh_searchResultActivity);
        plv_refresh_searchResultActivity  = (PullableListView) findViewById(R.id.plv_refresh_searchResultActivity);
        View headView = LayoutInflater.from(SearchResultActivity.this).inflate(R.layout.head_search_result_list,null);
        plv_refresh_searchResultActivity.addHeaderView(headView);
        ll_condition_searchResultActivity = (LinearLayout) headView.findViewById(R.id.ll_condition_searchResultActivity);
    }

    //初始化数据
    private void initData(){
        mySearchResultAdapter = new MySearchResultAdapter(SearchResultActivity.this,new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_searchResultActivity.setAdapter(mySearchResultAdapter);
    }

    //初始化事件
    private void initEvent(){
        ll_condition_searchResultActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动该Activity并期望返回结果
                Intent search = new Intent(SearchResultActivity.this,SearchActivity.class);
                startActivityForResult(search,1);
            }
        });

        ptrl_refresh_searchResultActivity.setOnRefreshListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1){       //条件搜索
            if (data.getAction().equals("condition_search")){
                Bundle bundle = data.getBundleExtra("condition_search");
                int age = bundle.getInt("age");//年龄
                int height = bundle.getInt("height");//身高
                String company = bundle.getString("company");//单位
                String area = bundle.getString("area");//地区
                String edu = bundle.getString("edu");//教育
                Toast.makeText(SearchResultActivity.this,age+height+company+area+edu,Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == 1 && resultCode == 2){ //名称搜索
            if (data.getAction().equals("name_search")){
                String name = data.getStringExtra("name");//拿到姓名
                Toast.makeText(SearchResultActivity.this,name,Toast.LENGTH_SHORT).show();
            }
        }else {

        }
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
