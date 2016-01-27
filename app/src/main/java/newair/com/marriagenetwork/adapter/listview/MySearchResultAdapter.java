package newair.com.marriagenetwork.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.interfaces.DataProvider;

/**
 * Created by Administrator on 2016/1/25.
 * ---------搜索结果List适配器--------
 */
public class MySearchResultAdapter extends BaseAdapter {


    public MySearchResultAdapter(Context context, DataProvider provider) {
        super(context, provider);
    }

    @Override
    protected void setLayoutInflater(View convertView) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_search_result_list,null);
            viewHolder.iv_head_search_result_list   = (ImageView) convertView.findViewById(R.id.iv_head_search_result_list);
            viewHolder.tv_name_search_result_list   = (TextView)  convertView.findViewById(R.id.tv_name_search_result_list);
            viewHolder.tv_age_search_result_list    = (TextView)  convertView.findViewById(R.id.tv_age_search_result_list);
            viewHolder.tv_height_search_result_list = (TextView)  convertView.findViewById(R.id.tv_height_search_result_list);
            viewHolder.tv_company_search_result_list= (TextView)  convertView.findViewById(R.id.tv_company_search_result_list);
            viewHolder.tv_edu_search_result_list    = (TextView)  convertView.findViewById(R.id.tv_edu_search_result_list);
            viewHolder.tv_address_search_result_list= (TextView)  convertView.findViewById(R.id.tv_address_search_result_list);
            convertView.setTag(viewHolder);
        }
    }

    @Override
    protected void initializeViews(Object entity, Object holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }

    private class ViewHolder{
        private ImageView iv_head_search_result_list;  //头像
        private TextView tv_name_search_result_list;   //名称
        private TextView tv_age_search_result_list;    //年龄
        private TextView tv_height_search_result_list; //身高
        private TextView tv_company_search_result_list;//单位
        private TextView tv_edu_search_result_list;    //学历
        private TextView tv_address_search_result_list;//地址
    }
}
