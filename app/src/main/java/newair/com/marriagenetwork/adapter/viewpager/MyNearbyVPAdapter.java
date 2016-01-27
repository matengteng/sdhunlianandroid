package newair.com.marriagenetwork.adapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 * -----------附近的人--ViewPager适配器-----
 */
public class MyNearbyVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> myData;
    private List<String> myTitle;

    public MyNearbyVPAdapter(FragmentManager fm, List<Fragment> myData, List<String> myTitle) {
        super(fm);
        this.myData = myData;
        this.myTitle = myTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return myData.get(position);
    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return myTitle.get(position);
    }
}
