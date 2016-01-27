package newair.com.marriagenetwork.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import newair.com.marriagenetwork.interfaces.DataProvider;

/**
 * Created by mateng on 16/1/20.
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter {

    protected Context context;
    protected DataProvider provider;
    protected LayoutInflater layoutInflater;

    protected BaseAdapter(Context context, DataProvider provider) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.provider = provider;
    }

    @Override
    public int getCount() {
        return provider.getDataList() != null ? provider.getDataList().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return provider.getDataList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        setLayoutInflater(convertView);
        initializeViews(getItem(position), convertView.getTag());
        return convertView;
    }

    /**
     * 设置adapter的LayoutInflater和给ViewHolder对象属性赋值
     *
     * @param convertView
     */
    protected abstract void setLayoutInflater(View convertView);

    /**
     * 设置ViewHolder对象属性的
     * @param entity
     * @param holder
     */
    protected abstract void initializeViews(Object entity, Object holder);

}
