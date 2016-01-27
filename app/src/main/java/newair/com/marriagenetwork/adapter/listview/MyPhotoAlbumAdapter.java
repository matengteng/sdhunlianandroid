package newair.com.marriagenetwork.adapter.listview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.interfaces.DataProvider;

/**
 * Created by Administrator on 2016/1/22.
 * -----------“相册”适配器-------------
 */
public class MyPhotoAlbumAdapter extends BaseAdapter {



    public MyPhotoAlbumAdapter(Context context, DataProvider provider) {
        super(context, provider);
    }

    @Override
    protected void setLayoutInflater(View convertView) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_photo_album_list,null);
            viewHolder.tv_day_photo_album_item   = (TextView)  convertView.findViewById(R.id.tv_day_photo_album_item);
            viewHolder.tv_month_photo_album_item = (TextView)  convertView.findViewById(R.id.tv_month_photo_album_item);
            viewHolder.tv_title_photo_album_item = (TextView)  convertView.findViewById(R.id.tv_title_photo_album_item);
            viewHolder.iv_img_photo_album_item   = (ImageView) convertView.findViewById(R.id.iv_img_photo_album_item);
        }
    }

    @Override
    protected void initializeViews(Object entity, Object holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }

    private class ViewHolder{
        private TextView tv_day_photo_album_item;  //号（26号）
        private TextView tv_month_photo_album_item;//月份
        private ImageView iv_img_photo_album_item; //缩略图
        private TextView tv_title_photo_album_item;//标题(若无，则显示多少张图片)
    }
}
