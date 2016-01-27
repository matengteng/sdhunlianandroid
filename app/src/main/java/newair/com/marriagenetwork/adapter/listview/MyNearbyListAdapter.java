package newair.com.marriagenetwork.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.interfaces.DataProvider;

/**
 * Created by Administrator on 2016/1/22.
 * ----------附近的人--列表--适配器----------
 */
public class MyNearbyListAdapter extends BaseAdapter {


    public MyNearbyListAdapter(Context context, DataProvider provider) {
        super(context, provider);
    }

    @Override
    protected void setLayoutInflater(View convertView) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_recomment_activity_list,null);
            viewHolder.iv_img_recomment_item  = (ImageView) convertView.findViewById(R.id.iv_img_recomment_item);
            viewHolder.iv_sex_recomment_item  = (ImageView) convertView.findViewById(R.id.iv_sex_recomment_item);
            viewHolder.tv_name_recomment_item = (TextView)  convertView.findViewById(R.id.tv_name_recomment_item);
            viewHolder.tv_from_recomment_item = (TextView)  convertView.findViewById(R.id.tv_from_recomment_item);
            viewHolder.tv_time_recomment_item = (TextView)  convertView.findViewById(R.id.tv_time_recomment_item);
            convertView.setTag(viewHolder);
        }
    }

    @Override
    protected void initializeViews(Object entity, Object holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }

    private class ViewHolder{
        private ImageView iv_img_recomment_item;//图片
        private TextView tv_name_recomment_item;//姓名
        private ImageView iv_sex_recomment_item;//性别
        private TextView tv_from_recomment_item;//单位
        private TextView tv_time_recomment_item;//距离
    }
}
