package newair.com.marriagenetwork.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.interfaces.DataProvider;

/**
 * Created by Administrator on 2016/1/22.
 * ---------“遇见爱”的listView适配器----------
 */
public class MyLovePLVAdapter extends BaseAdapter {

    public MyLovePLVAdapter(Context context, DataProvider provider) {
        super(context, provider);
    }

    @Override
    protected void setLayoutInflater(View convertView) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_love_activity_list,null);
            viewHolder.iv_img_love_item      = (ImageView) convertView.findViewById(R.id.iv_img_love_item);
            viewHolder.tv_title_happy_item   = (TextView)  convertView.findViewById(R.id.tv_title_happy_item);
            viewHolder.tv_activity_love_item = (TextView)  convertView.findViewById(R.id.tv_activity_love_item);
            viewHolder.tv_time_love_item     = (TextView)  convertView.findViewById(R.id.tv_time_love_item);
            viewHolder.tv_address_love_item  = (TextView)  convertView.findViewById(R.id.tv_address_love_item);
            convertView.setTag(viewHolder);
        }
    }

    @Override
    protected void initializeViews(Object entity, Object holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }

    private class ViewHolder{
        private ImageView iv_img_love_item;    //图片
        private TextView tv_title_happy_item;  //标题
        private TextView tv_activity_love_item;//活动
        private TextView tv_time_love_item;    //时间
        private TextView tv_address_love_item; //地点
    }

}
