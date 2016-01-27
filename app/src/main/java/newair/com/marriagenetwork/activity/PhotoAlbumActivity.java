package newair.com.marriagenetwork.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyPhotoAlbumAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.utils.HideActionBar;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;

/**
 * --------“相册”-界面----------------
 */
public class PhotoAlbumActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {

    private PullToRefreshLayout ptrl_refresh_photoAlbumActivity;//下拉刷新
    private PullableListView plv_refresh_photo_album_activity;  //ListViwe
    private MyPhotoAlbumAdapter myPhotoAlbumAdapter;            //适配器

    //头布局控件
    private ImageView iv_back_friendMoment_list_head;           //背景
    private ImageView iv_head_friendMoment_list_head;           //头像
    private TextView  tv_name_friendMoment_list_head;           //名称

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_photo_album);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_photoAlbumActivity://返回按钮
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
        ptrl_refresh_photoAlbumActivity = (PullToRefreshLayout) findViewById(R.id.ptrl_refresh_photoAlbumActivity);

        plv_refresh_photo_album_activity = (PullableListView) findViewById(R.id.plv_refresh_photo_album_activity);
        View headView = View.inflate(PhotoAlbumActivity.this,R.layout.head_friend_moment_list,null);//加载头布局
        plv_refresh_photo_album_activity.addHeaderView(headView);//添加头布局

        iv_back_friendMoment_list_head = (ImageView) headView.findViewById(R.id.iv_back_friendMoment_list_head);
        iv_head_friendMoment_list_head = (ImageView) headView.findViewById(R.id.iv_head_friendMoment_list_head);
        tv_name_friendMoment_list_head = (TextView)  headView.findViewById(R.id.tv_name_friendMoment_list_head);
    }
    //初始化数据
    private void initData(){
        //初始化适配器
        myPhotoAlbumAdapter = new MyPhotoAlbumAdapter(PhotoAlbumActivity.this,new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_photo_album_activity.setAdapter(myPhotoAlbumAdapter);//设置适配器
    }
    //初始化事件
    private void initEvent(){
        ptrl_refresh_photoAlbumActivity.setOnRefreshListener(this);
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
