package newair.com.marriagenetwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.activity.AttentionMineActivity;
import newair.com.marriagenetwork.activity.MineAttentionActivity;
import newair.com.marriagenetwork.activity.PhotoAlbumActivity;
import newair.com.marriagenetwork.activity.SettingAvtivity;


/**
 * --------我的界面----------
 */
public class MineFragment extends FragmentBase implements View.OnClickListener {


    //回调接口
    public interface OnMineFragmentCallBack {
        public void onFragmentInteraction();
    }
    private OnMineFragmentCallBack mListener;

    private LinearLayout rl_attention_me_mineFragment;//关注我的
    private LinearLayout rl_my_attention_mineFragment;//我关注的
    private LinearLayout rl_photo_album_mineFragment; //相册
    private LinearLayout rl_setting_mineFragment;     //设置

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        initView(view);
        initEvent();

        return view;
    }

    //初始化控件
    private void initView(View view){
        rl_attention_me_mineFragment = (LinearLayout) view.findViewById(R.id.rl_attention_me_mineFragment);
        rl_my_attention_mineFragment = (LinearLayout) view.findViewById(R.id.rl_my_attention_mineFragment);
        rl_photo_album_mineFragment  = (LinearLayout) view.findViewById(R.id.rl_photo_album_mineFragment);
        rl_setting_mineFragment      = (LinearLayout) view.findViewById(R.id.rl_setting_mineFragment);
    }
    //初始化事件
    private void initEvent(){
        rl_attention_me_mineFragment.setOnClickListener(this);
        rl_my_attention_mineFragment.setOnClickListener(this);
        rl_photo_album_mineFragment.setOnClickListener(this);
        rl_setting_mineFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_attention_me_mineFragment://关注我的
                Intent attentionMine = new Intent(getActivity(), AttentionMineActivity.class);
                startActivity(attentionMine);
                break;
            case R.id.rl_my_attention_mineFragment://我关注的
                Intent mineAttention = new Intent(getActivity(), MineAttentionActivity.class);
                startActivity(mineAttention);
                break;
            case R.id.rl_photo_album_mineFragment: //相册
                Intent photoAlbum = new Intent(getActivity(), PhotoAlbumActivity.class);
                startActivity(photoAlbum);
                break;
            case R.id.rl_setting_mineFragment:     //设置
                Intent setting = new Intent(getActivity(), SettingAvtivity.class);
                startActivity(setting);
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
//            mListener = (OnMineFragmentCallBack) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnMineFragmentCallBack");
//        }
//    }
}
