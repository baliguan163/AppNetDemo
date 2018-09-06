package com.net.demo.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.net.demo.app.R;
import com.net.demo.app.adapter.NewsAdapter;
import com.net.demo.app.bean.NewsBean;
import com.net.demo.app.utils.NewsUtils;
import java.util.ArrayList;
public  class FragmentNews extends Fragment {

    //    private TextView tv;
    private ArrayList<NewsBean> mlist;
    ListView lv1;

    public static FragmentNews newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);

        FragmentNews fragment = new FragmentNews();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mlist = NewsUtils.getAllNews(getActivity());
        //创建了个ListView变量用来获取layout中的ListView
        ListView lv1 = (ListView) view.findViewById(R.id.lv1);
        //建一个适配的变量，将上下文和list加载到ListVIew的适配器中，然后放到适配器变量里

        //        ArrayList<NewsBean> list
        NewsAdapter ma = new NewsAdapter(getActivity(), mlist);
        //将适配器变量的内容加载到List里(也就是把那一堆新闻都放了进去)
        lv1.setAdapter(ma);

        //        for(int i = 1;i<20;i++){
        //            mlist.add(new News("暴走大事件",
        //                    "刚刚",
        //                    R.drawable.mei02,
        //                    R.drawable.mei02,
        //                    R.drawable.mei02,
        //                    R.drawable.mei05));
        //        }
        //        for(int i = 1;i<20;i++){
        //            mlist.add(new News("暴走大事件","刚刚",
        //                    R.drawable.mei02,
        //                    R.drawable.mei02,
        //                    R.drawable.mei02,
        //                    R.drawable.mei05));
        //        }
        //        ma.notifyDataSetChanged();

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsBean news = mlist.get(i);
                Toast.makeText(getActivity(), news.getTitle(), Toast.LENGTH_SHORT).show();
                //                Intent intent = new Intent(getActivity(), ActivityNewDetail.class);
                //                intent.putExtra("obj",news);
                //                Bundle bundle1 = new Bundle();
                //                bundle1.putString("arg1","新闻详情");
                //                intent.putExtra("bundle",bundle1);
                //                startActivity(intent);
            }
        });

        //        tv = (TextView) view.findViewById(R.id.fragment_test_tv);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("name").toString();
            //            tv.setText(name);
        }

    }
}
