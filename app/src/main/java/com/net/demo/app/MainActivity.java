package com.net.demo.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.net.demo.app.fragment.FragmentBase;
import com.net.demo.app.adapter.ViewPagerPageAdapter;
import com.net.demo.app.fragment.FragmentNews2;
import com.net.demo.app.fragment.FragmentNews;
import com.net.demo.app.utils.BottomNavigationViewHelper;
import com.net.demo.app.R;
import java.util.ArrayList;
import java.util.List;

import com.net.demo.app.fragment.FragmentAudio;

public class MainActivity extends AppCompatActivity {
//    private TextView mTextMessage;
    private ViewPager viewPager;
    private MenuItem  menuItem;
    private BottomNavigationView navigation;
    private ViewPagerPageAdapter viewPagerAdapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_test_01:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_test_02:
                    viewPager.setCurrentItem(4);
                    return true;
//                case R.id.navigation_test_03:
//                    viewPager.setCurrentItem(5);
//                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

//        mTextMessage = (TextView) findViewById(R.id.message);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(navigation);
//        细心的朋友可能发现了，上面的代码为什么用了反射呢？
//        原因就是官方的BottomNavigationView默认有个放大的ShiftingMode效果，但是尚未支持代码层级的切换。
//        在3个menu item及以下时默认关闭，而到了4个及以上时就懵逼了，因为我们是要做ViewPager的侧滑啊！


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
    }


   // 需要注意的地方：取消位移动画,如果你的菜单数大于3个，则界面是这样的。
   //这个效果可定不是我们想要的效果，可以通过反射解决。
   //新建一个BottomNavigationViewHelper.class:
    private void setupViewPager(ViewPager viewPager) {
        List<Fragment> list = new ArrayList<>();
        list.add(FragmentBase.newInstance("首页"));
        list.add(FragmentAudio.newInstance("降噪"));
        list.add(FragmentNews.newInstance("测试0"));
        list.add(FragmentNews2.newInstance("测试1"));
        list.add(FragmentBase.newInstance("测试2"));
//        list.add(FragmentBase.newInstance("测试3"));
        viewPagerAdapter.setList(list);


    }



}
