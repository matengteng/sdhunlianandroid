package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyFriendPLVAdapter;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableExpandableListView;


/**
 * --------“朋友圈”-界面-----------
 */
public class FriendMomentActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {


    private PullToRefreshLayout ptrl_refresh_friendMoment_activity;//下拉刷新
    private PullableExpandableListView plv_refresh_friend_activity;//拓展ListView
    private MyFriendPLVAdapter myFriendPLVAdapter;                 //适配器
    //list头部的控件
    private ImageView iv_back_friendMoment_list_head;              //list Head的背景
    private TextView  tv_name_friendMoment_list_head;              //名称
    private ImageView iv_head_friendMoment_list_head;              //头像


    @Override
    public void initWidget() {
        setContentView(R.layout.activity_friend_moment);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_friendMomentActivity://返回按钮
                finish();
                break;
            case R.id.iv_edit_friendMoment_activity: //编辑按钮
                Toast.makeText(this,"编辑",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏actionbar
    }

    //初始化控件
    private void initView(){
        ptrl_refresh_friendMoment_activity = (PullToRefreshLayout) findViewById(R.id.ptrl_refresh_friendMoment_activity);

        plv_refresh_friend_activity = (PullableExpandableListView) findViewById(R.id.plv_refresh_friend_activity);
        View headView = View.inflate(FriendMomentActivity.this,R.layout.head_friend_moment_list,null);
        plv_refresh_friend_activity.addHeaderView(headView);

        //list头部控件
        iv_back_friendMoment_list_head = (ImageView) headView.findViewById(R.id.iv_back_friendMoment_list_head);
        tv_name_friendMoment_list_head = (TextView)  headView.findViewById(R.id.tv_name_friendMoment_list_head);
        iv_head_friendMoment_list_head = (ImageView) headView.findViewById(R.id.iv_head_friendMoment_list_head);
    }
    //初始化数据
    private void initData(){
        //初始化适配器
        myFriendPLVAdapter = new MyFriendPLVAdapter(FriendMomentActivity.this);
        plv_refresh_friend_activity.setAdapter(myFriendPLVAdapter);//设置 适配器
    }
    //初始化事件
    private void initEvent(){
        ptrl_refresh_friendMoment_activity.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        //下
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        //上
    }
}
