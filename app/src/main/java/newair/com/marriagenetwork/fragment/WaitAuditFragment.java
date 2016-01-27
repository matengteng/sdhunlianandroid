package newair.com.marriagenetwork.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import newair.com.marriagenetwork.R;


/**
 * -----人员审批---待审核Fragment-----------
 */
public class WaitAuditFragment extends FragmentBase {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wait_audit, container, false);
    }


}
