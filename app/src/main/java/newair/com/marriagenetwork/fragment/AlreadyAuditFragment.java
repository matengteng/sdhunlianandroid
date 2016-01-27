package newair.com.marriagenetwork.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import newair.com.marriagenetwork.R;

/**
 * ----人员审批-----已审核Fragment-----
 */
public class AlreadyAuditFragment extends FragmentBase {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_already_audit, container, false);
    }


}
