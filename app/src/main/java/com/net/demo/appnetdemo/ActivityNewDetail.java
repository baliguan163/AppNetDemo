package com.net.demo.appnetdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/9/4.
 */


public class ActivityNewDetail extends AppCompatActivity {
    TextView tv;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_detail);

         tv = (TextView) findViewById(R.id.tv);
//         iv = (ImageView) findViewById(R.id.iv);

//        Toast.makeText(ActivityNewDetail.this, "ActivityNewDetail", Toast.LENGTH_SHORT).show();

//        News2 news = (News2)getIntent().getSerializableExtra("obj");


//
//        Bundle bundle = intent.getBundleExtra("bundle");
//        String arg = bundle.getString("arg1");
//
//        StringBuilder sbld = new StringBuilder();
//        sbld.append(arg + "\n");
//        sbld.append(news.getTitle() + "\n");
//        sbld.append(news.getPubDate() + "\n");

        tv.setText("dddddddddddddddddd");
//        iv.setImageResource(news.getImg());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }


}
