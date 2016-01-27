package newair.com.marriagenetwork.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import newair.com.marriagenetwork.R;


/**
 * ---搜索------条件搜索界面----------
 */
public class ConditionSearchFragment extends FragmentBase implements View.OnClickListener {

    //回调接口
    public interface ConditionSearchCallBackListener{
        public void transConditions(Bundle condition);
    }
    private ConditionSearchCallBackListener backListener;

    private Button btn_sure_conditionFragment;          //确定按钮
    private CheckBox cb_checked_conditionFragment;      //选择按钮

    private RelativeLayout rl_age_conditionFragment;    //年龄
    private RelativeLayout rl_height_conditionFragment; //身高
    private RelativeLayout rl_company_conditionFragment;//工作单位
    private RelativeLayout rl_area_conditionFragment;   //地区
    private RelativeLayout rl_edu_conditionFragment;    //学历

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_condition_search, container, false);

        initView(view);
        initEvent();

        return view;

    }

    //初始化View
    private void initView(View view){
        btn_sure_conditionFragment   = (Button)         view.findViewById(R.id.btn_sure_conditionFragment);
        cb_checked_conditionFragment = (CheckBox)       view.findViewById(R.id.cb_checked_conditionFragment);

        rl_age_conditionFragment     = (RelativeLayout) view.findViewById(R.id.rl_age_conditionFragment);
        rl_height_conditionFragment  = (RelativeLayout) view.findViewById(R.id.rl_height_conditionFragment);
        rl_company_conditionFragment = (RelativeLayout) view.findViewById(R.id.rl_company_conditionFragment);
        rl_area_conditionFragment    = (RelativeLayout) view.findViewById(R.id.rl_area_conditionFragment);
        rl_edu_conditionFragment     = (RelativeLayout) view.findViewById(R.id.rl_edu_conditionFragment);
    }
    //初始化事件
    private void initEvent(){
        btn_sure_conditionFragment.setOnClickListener(this);

        rl_age_conditionFragment.setOnClickListener(this);
        rl_height_conditionFragment.setOnClickListener(this);
        rl_company_conditionFragment.setOnClickListener(this);
        rl_area_conditionFragment.setOnClickListener(this);
        rl_edu_conditionFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sure_conditionFragment:  //确定按钮
                //此处应该携带参数
                Bundle condition = new Bundle();
                condition.putInt("age",22);//年龄
                condition.putInt("height",177);//身高
                condition.putString("company","山东新之航软件");
                condition.putString("area","山东省");
                condition.putString("edu","本科");
                backListener.transConditions(condition);
                break;
            case R.id.rl_age_conditionFragment:    //年龄

                break;
            case R.id.rl_height_conditionFragment: //身高

                break;
            case R.id.rl_company_conditionFragment://工作单位

                break;
            case R.id.rl_area_conditionFragment:   //地区

                break;
            case R.id.rl_edu_conditionFragment:    //学历

                break;
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            backListener = (ConditionSearchCallBackListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ConditionSearchCallBackListener");
        }
    }
}
