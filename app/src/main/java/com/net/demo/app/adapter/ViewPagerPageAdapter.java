package com.net.demo.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;


public class ViewPagerPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public void setList(List<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public ViewPagerPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }
}

//
//public class ViewPagerPageAdapter extends PagerAdapter {
//    private List fragmentList;
//    private List mVIEWList;
//
///*public ViewPagerPageAdapter(List<Fragment> fragments) {
//    fragmentList = fragments;
//}*/
//    public ViewPagerPageAdapter(Context context)
//    {
//        TextView textView = new TextView(context);
//        textView.setText("这是textview");
//        mVIEWList = new ArrayList<>();
//        mVIEWList.add(textView);
//        mVIEWList.add(textView);
//        mVIEWList.add(textView);
//        mVIEWList.add(textView);
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        container.addView( (View)mVIEWList.get(position));
//        return  mVIEWList.get(position);
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View)mVIEWList.get(position));
//    }
//
//    @Override
//    public int getCount() {
//        return mVIEWList.size();
//    }
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view==object;
//    }
//}
//
//
//
//





