package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyLoveDetailsAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;


/**
 * --------”遇见爱“-活动详情-页--------
 */
public class LoveDetailsActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {

    private PullToRefreshLayout ptrl_refresh_loveDetails_activity;//下拉刷新
    private PullableListView plv_refresh_loveDetails_activity;    //ListView
    private MyLoveDetailsAdapter myLoveDetailsAdapter;            //适配器

    private EditText  et_content_loveDetails_activity;            //文本编辑
    private ImageView iv_push_love_details_activity;              //发送按钮

    private ImageView iv_head_love_details_list_head;              //头像
    private TextView  tv_name_love_details_list_head;              //姓名
    private TextView  tv_age_love_details_list_head;               //年龄
    private TextView  tv_height_love_details_list_head;            //身高
    private Button    btn_apply_love_details_list_head;            //报名
    private TextView  tv_title_love_details_list_head;             //标题
    private TextView  tv_time_love_details_list_head;              //时间
    private TextView  tv_address_love_details_list_head;           //地点
    private TextView  tv_aim_love_details_list_head;               //邀请谁（男or女）
    private TextView  tv_count_love_details_list_head;             //多少人报名
    private TextView  tv_content_love_details_list_head;           //活动详情

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_love_details);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_detailsActivity://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());
    }

    private void initView(){
        ptrl_refresh_loveDetails_activity = (PullToRefreshLayout) findViewById(R.id.ptrl_refresh_loveDetails_activity);

        plv_refresh_loveDetails_activity = (PullableListView) findViewById(R.id.plv_refresh_loveDetails_activity);
        View view = View.inflate(this,R.layout.head_love_details_list,null);//加载头布局
        plv_refresh_loveDetails_activity.addHeaderView(view);//设置头布局

        et_content_loveDetails_activity = (EditText)  findViewById(R.id.et_content_loveDetails_activity);
        iv_push_love_details_activity   = (ImageView) findViewById(R.id.iv_push_love_details_activity);

        //头布局的一些东西
        iv_head_love_details_list_head    = (ImageView) view.findViewById(R.id.iv_head_love_details_list_head);
        tv_name_love_details_list_head    = (TextView)  view.findViewById(R.id.tv_name_love_details_list_head);
        tv_age_love_details_list_head     = (TextView)  view.findViewById(R.id.tv_age_love_details_list_head);
        tv_height_love_details_list_head  = (TextView)  view.findViewById(R.id.tv_height_love_details_list_head);
        btn_apply_love_details_list_head  = (Button)    view.findViewById(R.id.btn_apply_love_details_list_head);
        tv_title_love_details_list_head   = (TextView)  view.findViewById(R.id.tv_title_love_details_list_head);
        tv_time_love_details_list_head    = (TextView)  view.findViewById(R.id.tv_time_love_details_list_head);
        tv_address_love_details_list_head = (TextView)  view.findViewById(R.id.tv_address_love_details_list_head);
        tv_aim_love_details_list_head     = (TextView)  view.findViewById(R.id.tv_aim_love_details_list_head);
        tv_count_love_details_list_head   = (TextView)  view.findViewById(R.id.tv_count_love_details_list_head);
        tv_content_love_details_list_head = (TextView)  view.findViewById(R.id.tv_content_love_details_list_head);
    }

    private void initData(){
        //初始化Adapter
        myLoveDetailsAdapter = new MyLoveDetailsAdapter(LoveDetailsActivity.this,new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_loveDetails_activity.setAdapter(myLoveDetailsAdapter);//设置适配器
    }

    private void initEvent(){
        ptrl_refresh_loveDetails_activity.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        //下拉刷新
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        //上拉加载
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

            }
        }
    };
}
