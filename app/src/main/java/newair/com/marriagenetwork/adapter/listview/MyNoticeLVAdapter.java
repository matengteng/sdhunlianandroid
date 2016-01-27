package newair.com.marriagenetwork.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.interfaces.DataProvider;

/**
 * Created by Administrator on 2016/1/25.
 * -----------首页-公告的适配器-----------
 */
public class MyNoticeLVAdapter extends BaseAdapter {


    public MyNoticeLVAdapter(Context context, DataProvider provider) {
        super(context, provider);
    }

    @Override
    protected void setLayoutInflater(View convertView) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_notice_home_fragment_list,null);
            viewHolder.iv_notice_img_homeFragment_list = (ImageView) convertView.findViewById(R.id.iv_notice_img_homeFragment_list);
            viewHolder.tv_notice_title_homeFragment_list = (TextView) convertView.findViewById(R.id.tv_notice_title_homeFragment_list);
            viewHolder.tv_notice_time_homeFragment_list = (TextView) convertView.findViewById(R.id.tv_notice_time_homeFragment_list);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
    }

    //填放数据
    @Override
    protected void initializeViews(Object entity, Object holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }

    //
    private class ViewHolder{
        private ImageView iv_notice_img_homeFragment_list;//公告图片
        private TextView tv_notice_title_homeFragment_list;//公告名称
        private TextView tv_notice_time_homeFragment_list;//公告时间
    }

    //加载图片
    private void LoaderImages(String url,ImageView imageView){
        ImageLoader.getInstance().displayImage(url,imageView);
    }
}
