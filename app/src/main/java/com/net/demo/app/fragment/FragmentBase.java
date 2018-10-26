package com.net.demo.app.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.net.demo.app.R;
import com.net.demo.app.adapter.NewsAdapter;
import com.net.demo.app.bean.NewsBean;
import com.net.demo.app.utils.NewsUtils;
import com.net.demo.app.utils.Utils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/4.
 */

public class FragmentBase extends Fragment{
    private TextView tv;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<NewsBean> mList;
    private ListView mListView;
    private NewsAdapter mNewsAdapter;

    //
    private LinearLayoutManager mLayoutManager;
    private static boolean hasMore = false; // 是否有下一页
    private static int currentPage ;
    // 若是上拉加载更多的网络请求 则不需要删除数据
    private boolean isLoadingMore = false;
    // 最后一个条目位置
    private static int lastVisibleItem = 0;


    //
    public  RefreshLayout refreshLayout;

    public static FragmentBase newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        FragmentBase fragment = new FragmentBase();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("name").toString();
            //tv.setText(name);
            Log.d(Utils.TAG, "onViewCreated:" + name);
        }

        mList = NewsUtils.getAllNews(getActivity());
        mListView = (ListView) view.findViewById(R.id.listview);
        mNewsAdapter = new NewsAdapter(getActivity(), mList);
        mListView.setAdapter(mNewsAdapter);
        Log.d(Utils.TAG, "mList size:" + mList.size());

        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Log.d(Utils.TAG, "刷新:" );
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//                Log.d(Utils.TAG, "刷新111:" );

                //在这里执行上拉刷新时的具体操作(网络请求、更新UI等)

                for(int i = 0 ;i <4;i++)
                {

                    NewsBean newsBean = new NewsBean();
                    newsBean.title ="火箭发射成功" + i + i +  new Date().toString();
                    newsBean.des= "搜索算法似懂非懂三分得手房贷首付第三方的手";
                    newsBean.news_url= "http://www.sina.cn";
                    newsBean.icon = getActivity().getDrawable(R.drawable.mei02);; //通过context对象将一个资源id转换成一个Drawable对象。
                    mList.add(newsBean);

                    NewsBean newsBean1 = new NewsBean();
                    newsBean1.title ="似懂非懂瑟瑟发抖速度" + i + i +  new Date().toString();
                    newsBean1.des= "地方上的房贷首付读书首付第三方的手房贷首付第三方的手负担";
                    newsBean1.news_url= "http://www.baidu.cn";
                    newsBean1.icon = getActivity().getDrawable(R.drawable.mei05);;//通过context对象将一个资源id转换成一个Drawable对象。
                    mList.add(newsBean1);

                    NewsBean newsBean2 = new NewsBean();
                    newsBean2.title ="豆腐皮人热舞" + i + i +  new Date().toString();
                    newsBean2.des= "费解的是离开房间打扫李开复离开独守空房迪斯科浪费电锋克劳利分级恐龙快打";
                    newsBean2.news_url= "http://www.qq.com";
                    newsBean2.icon = getActivity().getDrawable(R.drawable.mei02);;//通过context对象将一个资源id转换成一个Drawable对象。
                    mList.add(newsBean2);
                }
                //模拟网络请求到的数据
                mNewsAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000/*,false*/);
                //不传时间则立即停止刷新    传入false表示刷新失败
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Log.d(Utils.TAG, "加载:" );
//                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
//                Log.d(Utils.TAG, "加载111:" );
//在这里执行下拉加载时的具体操作(网络请求、更新UI等)
                for(int i = 0 ;i <4;i++)
                {

                    NewsBean newsBean = new NewsBean();
                    newsBean.title ="火箭发射成功" + i + i + i +  new Date().toString();
                    newsBean.des= "搜索算法似懂非懂三分得手房贷首付第三方的手";
                    newsBean.news_url= "http://www.sina.cn";
                    newsBean.icon = getActivity().getDrawable(R.drawable.mei02);; //通过context对象将一个资源id转换成一个Drawable对象。
                    mList.add(newsBean);

                    NewsBean newsBean1 = new NewsBean();
                    newsBean1.title ="似懂非懂瑟瑟发抖速度"+ i + i + i+  new Date().toString();
                    newsBean1.des= "地方上的房贷首付读书首付第三方的手房贷首付第三方的手负担";
                    newsBean1.news_url= "http://www.baidu.cn";
                    newsBean1.icon = getActivity().getDrawable(R.drawable.mei05);;//通过context对象将一个资源id转换成一个Drawable对象。
                    mList.add(newsBean1);

                    NewsBean newsBean2 = new NewsBean();
                    newsBean2.title ="豆腐皮人热舞"+ i + i + i+  new Date().toString();
                    newsBean2.des= "费解的是离开房间打扫李开复离开独守空房迪斯科浪费电锋克劳利分级恐龙快打";
                    newsBean2.news_url= "http://www.qq.com";
                    newsBean2.icon = getActivity().getDrawable(R.drawable.mei02);;//通过context对象将一个资源id转换成一个Drawable对象。
                    mList.add(newsBean2);
                }
                //模拟网络请求到的数据
                mNewsAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000/*,false*/);
                //不传时间则立即停止刷新    传入false表示刷新失败
            }
        });
//         //使用指定的 Header 和 Footer  方法三 Java代码设置
//        //设置 Header 为 Material样式
//        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(true));
//        //设置 Footer 为球脉冲
//        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

    }



//        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//            }
//        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
//            }
//        });
//
//
////        使用指定的 Header 和 Footer 方法三 Java代码设置
//        final RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
////设置 Header 为 贝塞尔雷达 样式
//        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
////设置 Footer 为 球脉冲 样式
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

    //tv = (TextView) view.findViewById(R.id.fragment_test_tv);

//    重写下拉刷新方法
//    @Override
//    public void onRefresh() {
//        // 网络请求
////        okHttp.getHandler(handlerForGenJin);
//        // 这里用sortWay变量 这样即使下拉刷新也能保持用户希望的排序方式
////        askForOkHttp(sortWay);
//        Log.d(Utils.TAG,"onRefresh:网络请求" );
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Log.d(Utils.TAG,"onRefresh:网络请求结束" );
//        handlerForRefresh.sendEmptyMessage(0x93);
//    }

//    当网络请求完 获取并解析了数据，通知结束下拉刷新
//     handlerForRefresh.sendEmptyMessage(0x93) // 通知结束下拉刷新

//    Handler handlerForRefresh = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0x93: {
//                    Log.d(Utils.TAG,"handlerForRefresh:结束下拉刷新" );
//                    swipeRefreshLayout.setRefreshing(false);
//                }
//            }
//        }
//    };






}