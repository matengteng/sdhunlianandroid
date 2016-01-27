package newair.com.marriagenetwork.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import newair.com.marriagenetwork.R;


/**
 * -----搜索----姓名搜索界面----------
 */
public class NameSearchFragment extends FragmentBase {

    //回调接口
    public interface NameSearchCallBackListener {
        public void transName(String name);
    }

    private NameSearchCallBackListener backListener;

    private Button btn_sure_nameFragment; //确定按钮
    private EditText et_name_nameFragment;//名字

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_name_search, container, false);

        initView(view);
        initEvent();

        return view;

    }

    //初始化View
    private void initView(View view) {
        btn_sure_nameFragment = (Button) view.findViewById(R.id.btn_sure_nameFragment);
        et_name_nameFragment = (EditText) view.findViewById(R.id.et_name_nameFragment);
    }

    //初始化事件
    private void initEvent() {
        btn_sure_nameFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//此处应该传参数
                String name = et_name_nameFragment.getText().toString();
                if (name == null || name.equals("")){
                    Toast.makeText(getActivity(),"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    backListener.transName(et_name_nameFragment.getText().toString());
                }

            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            backListener = (NameSearchCallBackListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NameSearchCallBackListener");
        }
    }
}
