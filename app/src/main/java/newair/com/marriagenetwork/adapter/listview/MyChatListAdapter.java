package newair.com.marriagenetwork.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.interfaces.DataProvider;

/**
 * Created by Administrator on 2016/1/26.
 * ----------爱聊界面list适配器---------
 */
public class MyChatListAdapter extends BaseAdapter {



    public MyChatListAdapter(Context context, DataProvider provider) {
        super(context, provider);
    }

    @Override
    protected void setLayoutInflater(View convertView) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_chat_fragment_list,null);
            viewHolder.iv_head_chat_list = (ImageView) convertView.findViewById(R.id.iv_head_chat_list);
            viewHolder.tv_name_chat_list = (TextView) convertView.findViewById(R.id.tv_name_chat_list);
            viewHolder.tv_time_chat_list = (TextView) convertView.findViewById(R.id.tv_time_chat_list);
            viewHolder.tv_content_chat_list = (TextView) convertView.findViewById(R.id.tv_content_chat_list);
            convertView.setTag(viewHolder);
        }
    }

    @Override
    protected void initializeViews(Object entity, Object holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }



    private class ViewHolder{
        private ImageView iv_head_chat_list;   //头像
        private TextView  tv_name_chat_list;   //姓名
        private TextView  tv_time_chat_list;   //时间
        private TextView  tv_content_chat_list;//内容
    }
}
