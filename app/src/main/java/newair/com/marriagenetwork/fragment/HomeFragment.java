package newair.com.marriagenetwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.activity.HappyActivity;
import newair.com.marriagenetwork.activity.LoveActivity;
import newair.com.marriagenetwork.adapter.listview.MyNoticeLVAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.widget.helper.ListViewForScrollView;


/**
 * -------------首页界面--------------
 */
public class HomeFragment extends FragmentBase implements View.OnClickListener {


    //回调接口
    public interface OnHomePageCallBack {
        public void onHomePageInteraction();
    }
    private OnHomePageCallBack mListener;

    private ListViewForScrollView lv_notice_homeFragment;//公告
    private MyNoticeLVAdapter myNoticeLVAdapter;         //适配器


    private RelativeLayout rl_happy_homeFragment; //欢乐趴
    private RelativeLayout rl_love_homeFragment;  //遇见爱

    private ViewPager vp_slide_container_homeFragment;//幻灯片

    private ImageView iv_happy_img_homeFragment;      //欢乐趴展示图
    private TextView tv_happy_title_homeFragment;     //标题
    private TextView tv_happy_time_homeFragment;      //时间

    private ImageView iv_love_img_homeFragment;       //遇见爱展示图
    private TextView tv_love_title_homeFragment;      //标题
    private TextView tv_love_time_homeFragment;       //时间


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);
        initData();
        initEvent();

        return view;
    }

    //初始化控件
    private void initView(View view){

        lv_notice_homeFragment = (ListViewForScrollView) view.findViewById(R.id.lv_notice_homeFragment);
        rl_happy_homeFragment  = (RelativeLayout) view.findViewById(R.id.rl_happy_homeFragment);
        rl_love_homeFragment   = (RelativeLayout) view.findViewById(R.id.rl_love_homeFragment);

        vp_slide_container_homeFragment = (ViewPager) view.findViewById(R.id.vp_slide_container_homeFragment);

        iv_happy_img_homeFragment       = (ImageView) view.findViewById(R.id.iv_happy_img_homeFragment);
        tv_happy_title_homeFragment     = (TextView)  view.findViewById(R.id.tv_happy_title_homeFragment);
        tv_happy_time_homeFragment      = (TextView)  view.findViewById(R.id.tv_happy_time_homeFragment);

        iv_love_img_homeFragment        = (ImageView) view.findViewById(R.id.iv_love_img_homeFragment);
        tv_love_title_homeFragment      = (TextView)  view.findViewById(R.id.tv_love_title_homeFragment);
        tv_love_time_homeFragment       = (TextView)  view.findViewById(R.id.tv_love_time_homeFragment);
    }
    //初始化数据
    private void initData(){
        myNoticeLVAdapter = new MyNoticeLVAdapter(getActivity(),new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        lv_notice_homeFragment.setAdapter(myNoticeLVAdapter);//设置适配器
    }
    //初始化事件
    private void initEvent(){
        rl_happy_homeFragment.setOnClickListener(this);
        rl_love_homeFragment.setOnClickListener(this);
        //Item的点击事件
        lv_notice_homeFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_happy_homeFragment: //欢乐趴
                Intent happy = new Intent(getActivity(), HappyActivity.class);
                startActivity(happy);
                break;
            case R.id.rl_love_homeFragment:  //遇见爱
                Intent love = new Intent(getActivity(), LoveActivity.class);
                startActivity(love);
                break;
        }
    }





    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnHomePageCallBack) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnHomePageCallBack");
//        }
//    }



}
