package com.net.demo.appnetdemo;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.astuetz.PagerSlidingTabStrip;

        import java.util.ArrayList;
        import java.util.List;

public class VpFragmentLvfragment extends Fragment
{
      private ViewPager vp;
      private PagerSlidingTabStrip pst;

      public static VpFragmentLvfragment newInstance(String name)
      {
        Bundle args = new Bundle();
        args.putString("name", name);

        VpFragmentLvfragment fragment = new VpFragmentLvfragment();
        fragment.setArguments(args);
        return fragment;
       }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_vp_fragment_lv, container, false);
            return view;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

//            getSupportActionBar().hide();
            vp=(ViewPager)view.findViewById(R.id.vp);
            pst= (PagerSlidingTabStrip) view.findViewById(R.id.pst);
            List<Fragment> list=new ArrayList<>();
            List<String> titles=new ArrayList<>();
            titles.add("推荐");
            titles.add("热点");
            titles.add("烟台");
            titles.add("视频");
            titles.add("订阅");
            titles.add("体育");
            titles.add("娱乐");
            titles.add("奥运");
            titles.add("财经");
            titles.add("国际");
            for (int i=1;i<=10;i++){
                Fragment fragment=new VpFragmentLv();
                Bundle bundle=new Bundle();
                bundle.putInt("arg",i);
                fragment.setArguments(bundle);
                list.add(fragment);
            }
            MyVpFragmentAdapter ma = new MyVpFragmentAdapter(getFragmentManager(),list,titles);
            vp.setAdapter(ma);

            pst.setIndicatorColor(getResources().getColor(R.color.colorPrimary));
            pst.setIndicatorHeight(5);
            pst.setAllCaps(true);
            pst.setShouldExpand(true);//平分
            pst.setTextColor(getResources().getColor(R.color.colorAccent));
            pst.setViewPager(vp);
        }


}

