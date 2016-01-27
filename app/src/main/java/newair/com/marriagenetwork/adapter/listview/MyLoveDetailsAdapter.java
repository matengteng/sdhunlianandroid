package newair.com.marriagenetwork.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.interfaces.DataProvider;

/**
 * Created by Administrator on 2016/1/22.
 * ---------遇见爱-“活动详情”--适配器--------
 */
public class MyLoveDetailsAdapter extends BaseAdapter {


    public MyLoveDetailsAdapter(Context context, DataProvider provider) {
        super(context, provider);
    }

    @Override
    protected void setLayoutInflater(View convertView) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_love_details_list,null);
            viewHolder.iv_img_loveDetails_item        = (ImageView) convertView.findViewById(R.id.iv_img_loveDetails_item);
            viewHolder.tv_name_loveDetails_item       = (TextView)  convertView.findViewById(R.id.tv_name_loveDetails_item);
            viewHolder.tv_reply_name_loveDetails_item = (TextView)  convertView.findViewById(R.id.tv_reply_name_loveDetails_item);
            viewHolder.tv_content_loveDetails_item    = (TextView)  convertView.findViewById(R.id.tv_content_loveDetails_item);
            viewHolder.tv_time_loveDetails_item       = (TextView)  convertView.findViewById(R.id.tv_time_loveDetails_item);
            viewHolder.ll_container_love_details_item = (LinearLayout) convertView.findViewById(R.id.ll_container_love_details_item);
            convertView.setTag(viewHolder);
        }
    }

    @Override
    protected void initializeViews(Object entity, Object holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }

    private class ViewHolder{
        private ImageView iv_img_loveDetails_item;//头像
        private TextView tv_name_loveDetails_item;//名称
        private TextView tv_reply_name_loveDetails_item;//回复人的姓名
        private TextView tv_content_loveDetails_item;//内容
        private TextView tv_time_loveDetails_item;  //时间
        private LinearLayout ll_container_love_details_item;//盛放回复人的容器
    }
}
