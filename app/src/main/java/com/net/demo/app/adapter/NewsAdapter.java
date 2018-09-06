package com.net.demo.app.adapter;

/**
 * Created by Administrator on 2018/9/4.
 */

import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.net.demo.app.MyApp;
import com.net.demo.app.bean.NewsBean;
import com.net.demo.app.R;
import com.net.demo.app.utils.ImageLoaderManager;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

public class NewsAdapter extends BaseAdapter {

    private ArrayList<NewsBean> list;
    private Context context;

    //通过构造方法接受要显示的新闻数据集合
    public NewsAdapter(Context context, ArrayList<NewsBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        //1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
        if(convertView != null){
            view = convertView;
        }else
        {
            //方法一：推荐
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
            view = View.inflate(context, R.layout.item_news_layout, null);//将一个布局文件转换成一个view对象

            //方法二
            //通过LayoutInflater将布局转换成view对象
            //view =  LayoutInflater.from(context).inflate(R.layout.item_news_layout, null);

            //方法三：系统级开发
            //通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
            //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view = layoutInflater.inflate(R.layout.item_news_layout, null);

        }
        //2.获取view上的子控件对象
        ImageView item_img_icon = (ImageView) view.findViewById(R.id.item_img_icon);
        TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
        TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);

        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        NewsBean newsBean = list.get(position);
        //4.将数据设置给这些子控件做显示
//        item_img_icon.setImageDrawable(newsBean.icon);//设置imageView的图片
//        String imageUri = "drawable://" + R.drawable.mei02; // from drawables (only images, non-9patch)
//        String drawableUrl = ImageDownloader.Scheme.DRAWABLE.wrap("R.drawable.mei02");
        Drawable drawable = MyApp.getInstance().imageLoaderManager.zoomDrawable(newsBean.icon,100,75);
        item_img_icon.setImageDrawable(drawable);//设置imageView的图片
//        MyApp.getInstance().imageLoaderManager.displayImage(item_img_icon,drawableUrl);


        item_tv_title.setText(newsBean.title);
        item_tv_des.setText(newsBean.des);
        return view;
    }


}
