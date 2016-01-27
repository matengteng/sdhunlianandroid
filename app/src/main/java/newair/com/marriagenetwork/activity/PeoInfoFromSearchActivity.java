package newair.com.marriagenetwork.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.utils.HideActionBar;


/**
 * ------点击搜索结果跳到的页面----------
 * ---------展示搜索人详情-------
 */
public class PeoInfoFromSearchActivity extends BaseActivity {

    private ImageView iv_head_infoFromSearch;   //头像
    private TextView tv_name_infoFromSearch;    //姓名
    private TextView tv_age_infoFromSearch;     //年龄
    private TextView tv_height_infoFromSearch;  //身高
    private TextView tv_company_infoFromSearch; //单位
    private TextView tv_content_infoFromSearch; //自我介绍

    private ImageView iv_finish_infoFromSearch; //返回按钮
    private Button btn_attention_infoFromSearch;//关注按钮
    private Button btn_invite_infoFromSearch;   //邀请按钮

    private PopupWindow popupWindow;            //弹窗

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_peo_info_from_search);
        initView();
        initEvent();
        initPopupWindow();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_infoFromSearch:    //返回按钮
                finish();
                break;
            case R.id.btn_attention_infoFromSearch://关注按钮

                break;
            case R.id.btn_invite_infoFromSearch:   //邀请按钮
                popupWindow.showAtLocation(btn_invite_infoFromSearch, Gravity.CENTER,0,0);
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
        iv_head_infoFromSearch       = (ImageView) findViewById(R.id.iv_head_infoFromSearch);
        tv_name_infoFromSearch       = (TextView)  findViewById(R.id.tv_name_infoFromSearch);
        tv_age_infoFromSearch        = (TextView)  findViewById(R.id.tv_age_infoFromSearch);
        tv_height_infoFromSearch     = (TextView)  findViewById(R.id.tv_height_infoFromSearch);
        tv_company_infoFromSearch    = (TextView)  findViewById(R.id.tv_company_infoFromSearch);
        tv_content_infoFromSearch    = (TextView)  findViewById(R.id.tv_content_infoFromSearch);

        iv_finish_infoFromSearch     = (ImageView) findViewById(R.id.iv_finish_infoFromSearch);
        btn_attention_infoFromSearch = (Button)    findViewById(R.id.btn_attention_infoFromSearch);
        btn_invite_infoFromSearch    = (Button)    findViewById(R.id.btn_invite_infoFromSearch);
    }

    //初始化事件
    private void initEvent(){
        iv_finish_infoFromSearch.setOnClickListener(this);
        btn_attention_infoFromSearch.setOnClickListener(this);
        btn_invite_infoFromSearch.setOnClickListener(this);
    }

    //初始化PopupWindow
    private void initPopupWindow(){
        View view = LayoutInflater.from(PeoInfoFromSearchActivity.this).inflate(R.layout.popup_window_search_info_layout,null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }




}
