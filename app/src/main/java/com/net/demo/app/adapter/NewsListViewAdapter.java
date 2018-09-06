package com.net.demo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.net.demo.app.bean.News;
import com.net.demo.app.R;
import java.util.List;

/**
 * Created by Administrator on 2018/9/4.
 */

public class NewsListViewAdapter extends BaseAdapter {
    private Context context;
    private List<News> list;

    public NewsListViewAdapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view==null)
        {
            vh = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.list_simple_layout,null);

            vh.img = (ImageView) view.findViewById(R.id.iv);
            vh.title = (TextView) view.findViewById(R.id.tv);
            view.setTag(vh);
        }
        else
        {
            vh = (ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ActivityNewDetail.class);
//                context.startActivity(intent);
//                Intent intent = new Intent(context, ActivityNewDetail.class);
//                context.startActivity(intent);
//
//                News news = list.get();
//                Toast.makeText(context,news.getTitle(),Toast.LENGTH_SHORT).show();
//
//                intent.putExtra("obj",news);
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("arg1","新闻详情");
//                intent.putExtra("bundle",bundle1);
//                context.startActivity(intent);
            }
        });

        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"点击图片",Toast.LENGTH_SHORT).show();
            }
        });

        News news = list.get(i);
        vh.title.setText(news.getTitle());
        vh.img.setImageResource(news.getImg());
        return view;
    }
    public class ViewHolder{
        TextView title;
        ImageView img;
    }

}
