package newair.com.marriagenetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.adapter.listview.MyChatListAdapter;
import newair.com.marriagenetwork.interfaces.DataProvider;
import newair.com.marriagenetwork.widget.pullableview.PullToRefreshLayout;
import newair.com.marriagenetwork.widget.pullableview.PullableListView;
/**
 * -------爱聊界面------------
 */
public class ChatFragment extends FragmentBase implements PullToRefreshLayout.OnRefreshListener {


    //回调接口
    public interface OnChatFragmentCallBack {
        public void onChatInteraction();
    }
    private OnChatFragmentCallBack mListener;

    private PullToRefreshLayout ptrl_refresh_chatFragment;//下拉刷新
    private PullableListView plv_refresh_chatFragment;    //ListView
    private MyChatListAdapter myChatListAdapter;          //适配器

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        initView(view);
        initData();
        initEvent();

        return view;
    }

    private void initView(View view){
        ptrl_refresh_chatFragment = (PullToRefreshLayout) view.findViewById(R.id.ptrl_refresh_chatFragment);
        plv_refresh_chatFragment  = (PullableListView) view.findViewById(R.id.plv_refresh_chatFragment);
    }

    private void initData(){
        myChatListAdapter = new MyChatListAdapter(getActivity(),new DataProvider(){
            @Override
            public List getDataList() {
                return null;
            }
        });
        plv_refresh_chatFragment.setAdapter(myChatListAdapter);
    }

    private void initEvent(){
        ptrl_refresh_chatFragment.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        //下拉刷新
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        //上拉加载
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
//            mListener = (OnChatFragmentCallBack) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnChatFragmentCallBack");
//        }
//    }


}
