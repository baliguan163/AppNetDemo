package com.net.demo.app.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.net.demo.app.utils.CallbackNew2View;
import com.net.demo.app.bean.News2;
import com.net.demo.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VpFragmentLv extends Fragment implements AdapterView.OnItemClickListener,CallbackNew2View {

    List<News2> list;

    public VpFragmentLv() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vp_fragment_lv, container, false);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
//        TextView tv=(TextView)getView().findViewById(R.id.tv);

        ListView lv = (ListView) getView().findViewById(R.id.lv);
        list = new ArrayList<News2>();
        News2ListAdapter ma = new News2ListAdapter(getActivity(),list,this);
        lv.setAdapter(ma);
        lv.setOnItemClickListener(this);

        final Bundle bundle = getArguments();


//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                News2 news = list.get(i);
//                Intent intent = new Intent(getActivity(), ActivityNewDetail.class);
//                intent.putExtra("obj", news);
//                Bundle b1 = new Bundle();
//                b1.putString("arg1", "今天七月七");
//                intent.putExtra("bundle", b1);
//                startActivity(intent);
//            }
//        });
        if (bundle != null) {
            int arg = bundle.getInt("arg");
            // tv.setText("我是Fagment"+arg);
            switch (arg) {
                case 1:
                    for (int i = 0; i < 20; i++) {
                        list.add(new News2("体育新闻" + i, "今天", "0001", R.drawable.mei02, "新浪网"));
                        ma.notifyDataSetChanged();
                    }
                    break;
                case 2:
                    for (int i = 0; i < 20; i++) {
                        list.add(new News2("烟台新闻" + i, "今天", "0002", R.drawable.mei05, "新浪网"));
                        ma.notifyDataSetChanged();
                    }
                    break;

                case 3:
                    for (int i = 0; i < 20; i++) {
                        list.add(new News2("奥运新闻" + i, "今天", "0002", R.drawable.mei02, "新浪网"));
                        ma.notifyDataSetChanged();
                    }
                    break;

            }


        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
          News2 news = list.get(i);
          Toast.makeText(getActivity(), "得到的位置是"+i + " " + news.getTitle(), Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(getActivity(), ActivityNewDetail.class);
//                intent.putExtra("obj", news);
//                Bundle b1 = new Bundle();
//                b1.putString("arg1", "今天七月七");
//                intent.putExtra("bundle", b1);
//                startActivity(intent);

    }

    @Override
    public void click(View view) {
//        count=0;//清零
        switch (view.getId())
        {
            case R.id.tv:
                int position=(Integer)view.getTag();//得到点击的位置
                News2 news2= list.get(position);//得到item的数据

                Toast.makeText(getActivity(), "得到的位置是"+position, Toast.LENGTH_SHORT).show();
//                mAdapter.notifyDataSetChanged();


//                Intent intent = new Intent(getActivity(), ActivityNewDetail.class);
//                intent.putExtra("obj",news2);
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("arg1","新闻详情");
//                intent.putExtra("bundle",bundle1);
//                startActivity(intent);
                break;
//            case R.id.imageview_item_course_add:
//                int positionAdd=(Integer)view.getTag();//得到点击的位置
//                CoursePay coursePayAdd=mData.get(positionAdd);//得到item的数据
//                int countAdd=coursePayAdd.getNum();//得到当前数量
//                if (countAdd>=0){
//                    countAdd+=1;
//                }
//                coursePayAdd.setNum(countAdd);//设置最新的数量
//                mAdapter.notifyDataSetChanged();
//                Toast.makeText(this, "得到的位置是  "+positionAdd, Toast.LENGTH_SHORT).show();
//                break;
        }
        //价格总数
//        for (int i=0;i<mData.size();i++){
//            count+=mData.get(i).getPrice()*mData.get(i).getNum();
//        }
//        mTextViewPrice.setText(""+count);


//                  Intent intent = new Intent(context, ActivityNewDetail.class);
//                context.startActivity(intent);


    }

}
