package newair.com.marriagenetwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.activity.FriendMomentActivity;
import newair.com.marriagenetwork.activity.NearbyActivity;
import newair.com.marriagenetwork.activity.RecommentActivity;
import newair.com.marriagenetwork.activity.SearchResultActivity;


/**
 * ---------“发现”-界面-----------
 */
public class FindFragment extends FragmentBase implements View.OnClickListener {


    //回调接口
    public interface OnFindFragmentCallBack {
        public void onFindInteraction();
    }
    private OnFindFragmentCallBack mListener;


    private LinearLayout ll_friends_findFragment;  //朋友圈
    private LinearLayout ll_search_findFragment;   //搜索
    private LinearLayout ll_recommend_findFragment;//近日推荐
    private LinearLayout ll_nearby_findFragment;   //附近的人
    private LinearLayout ll_game_findFragment;     //游戏

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find, container, false);

        initView(view);
        initEvent();

        return view;
    }

    //初始化控件
    private void initView(View view){
        ll_friends_findFragment   = (LinearLayout) view.findViewById(R.id.ll_friends_findFragment);
        ll_search_findFragment    = (LinearLayout) view.findViewById(R.id.ll_search_findFragment);
        ll_recommend_findFragment = (LinearLayout) view.findViewById(R.id.ll_recommend_findFragment);
        ll_nearby_findFragment    = (LinearLayout) view.findViewById(R.id.ll_nearby_findFragment);
        ll_game_findFragment      = (LinearLayout) view.findViewById(R.id.ll_game_findFragment);
    }

    //初始化事件
    private void initEvent(){
        ll_friends_findFragment.setOnClickListener(this);
        ll_search_findFragment.setOnClickListener(this);
        ll_recommend_findFragment.setOnClickListener(this);
        ll_nearby_findFragment.setOnClickListener(this);
        ll_game_findFragment.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_friends_findFragment:  //朋友圈
                Intent friendMoment = new Intent(getActivity(), FriendMomentActivity.class);
                startActivity(friendMoment);
                break;
            case R.id.ll_search_findFragment:   //搜索
                Intent search_result = new Intent(getActivity(), SearchResultActivity.class);
                startActivity(search_result);
                break;
            case R.id.ll_recommend_findFragment://近日推荐
                Intent reComment = new Intent(getActivity(), RecommentActivity.class);
                startActivity(reComment);
                break;
            case R.id.ll_nearby_findFragment:   //附近的人
                Intent nearby = new Intent(getActivity(), NearbyActivity.class);
                startActivity(nearby);
                break;
            case R.id.ll_game_findFragment:     //游戏

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
//            mListener = (OnFindFragmentCallBack) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFindFragmentCallBack");
//        }
//    }

}
