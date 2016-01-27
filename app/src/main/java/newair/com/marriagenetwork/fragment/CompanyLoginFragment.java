package newair.com.marriagenetwork.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import newair.com.marriagenetwork.R;


/**
 * --------企业登录的fragment--------
 */
public class CompanyLoginFragment extends FragmentBase {

    private String name = "";
    private Button btn_login_companyFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            this.name = bundle.getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_company_login, container, false);

        initView(view);
        initEvent();

        return view;

    }

    //初始化View
    private void initView(View view){
        btn_login_companyFragment = (Button) view.findViewById(R.id.btn_login_companyFragment);
    }

    //初始化事件
    private void initEvent(){
        btn_login_companyFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent company = new Intent(getActivity(), StaffAuditActivity.class);
//                startActivity(company);
            }
        });
    }
}
