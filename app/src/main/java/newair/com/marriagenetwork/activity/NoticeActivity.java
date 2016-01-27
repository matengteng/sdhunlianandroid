package newair.com.marriagenetwork.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.utils.HideActionBar;


/**
 * ---------"公告"的界面-------------
 */
public class NoticeActivity extends BaseActivity {

    private String link = "";
    private WebView wv_content_noticeActivity;//WebView

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_notice);
        initView();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_noticeActivity://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());//隐藏ActionBar
        getIntentData();//获取传过来的数据
        setUpWebView(); //设置WebView
    }

    //初始化控件
    private void initView(){
        wv_content_noticeActivity = (WebView) findViewById(R.id.wv_content_noticeActivity);
    }
    //初始化事件
    private void initEvent(){

    }
    //获取传过来的数据
    private void getIntentData(){
        Intent intent = getIntent();
        if (intent != null){
            link = intent.getStringExtra("link");
        }
    }
    //初始化WebView
    @SuppressLint("SetJavaScriptEnabled")
    private void setUpWebView(){
        //打开页面时，自适应屏幕
        wv_content_noticeActivity.getSettings().setUseWideViewPort(true);//设置此属性可以任意比例缩放
        wv_content_noticeActivity.getSettings().setLoadWithOverviewMode(true);
        //设置WebView隐藏缩放控制按钮
        wv_content_noticeActivity.getSettings().setDisplayZoomControls(false);
        //设置WebView支持手势缩放
        wv_content_noticeActivity.getSettings().setBuiltInZoomControls(true);
        //WebView加载web资源
        wv_content_noticeActivity.loadUrl(this.link);
        //启用支持javascript
        wv_content_noticeActivity.getSettings().setJavaScriptEnabled(true);
        //设置优先使用缓存
        wv_content_noticeActivity.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wv_content_noticeActivity.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
