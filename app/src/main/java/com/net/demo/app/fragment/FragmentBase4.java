package com.net.demo.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.net.demo.app.R;
import com.net.demo.app.adapter.NewsAdapter;
import com.net.demo.app.bean.NewsBean;
import com.net.demo.app.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/4.
 */

public class FragmentBase4 extends Fragment{
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

    //
    private int mOffset = 0;
    private int mScrollY = 0;
    private ViewPager view_pager;

    public static FragmentBase4 newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        FragmentBase4 fragment = new FragmentBase4();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test4, container, false);
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
//
//        mList = NewsUtils.getAllNews(getActivity());
//        mListView = (ListView) view.findViewById(R.id.listview);
//        mNewsAdapter = new NewsAdapter(getActivity(), mList);
//        mListView.setAdapter(mNewsAdapter);
//        Log.d(Utils.TAG, "mList size:" + mList.size());
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                NewsBean news = mList.get(i);
//                Toast.makeText(getActivity(), news.getTitle(), Toast.LENGTH_SHORT).show();
//                //                Intent intent = new Intent(getActivity(), ActivityNewDetail.class);
//                //                intent.putExtra("obj",news);
//                //                Bundle bundle1 = new Bundle();
//                //                bundle1.putString("arg1","新闻详情");
//                //                intent.putExtra("bundle",bundle1);
//                //                startActivity(intent);
//            }
//        });

        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                Log.d(Utils.TAG, "刷新:" );
////                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//                Log.d(Utils.TAG, "刷新111:" );
//
//            }
//        });
//
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                Log.d(Utils.TAG, "加载:" );
//                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
//                Log.d(Utils.TAG, "加载111:" );
//
//            }
//        });


        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener(){
//       厨房放电饭锅和
        });

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                // 做刷新操作
//            }
//
//            @Override
//            public void onHeaderMoving(RefreshHeader header, boolean isDragging,
//                                       float percent, int offset, int headerHeight, int maxDragHeight) {
//                //下拉后的操作，这里只是做了平移效果`setTranslationY`,当然你可以做很多酷炫的效果
//                mOffset = offset / 2;
//                parallax.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
//            }

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