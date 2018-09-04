package com.net.demo.appnetdemo;

/**
 * Created by Administrator on 2018/9/4.
 */

import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MyVpFragmentAdapter  extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> titles;
    public MyVpFragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.list= list;
        this.titles= titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
