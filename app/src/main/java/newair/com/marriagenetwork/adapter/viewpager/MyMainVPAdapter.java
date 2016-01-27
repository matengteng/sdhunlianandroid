package newair.com.marriagenetwork.adapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/1/19.
 * ------------主界面ViewPager的适配器---------
 */
public class MyMainVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> myData;

    public MyMainVPAdapter(FragmentManager fm,List<Fragment> myData) {
        super(fm);
        this.myData = myData;
    }

    @Override
    public Fragment getItem(int i) {
        return myData.get(i);
    }

    @Override
    public int getCount() {
        return myData.size();
    }
}
