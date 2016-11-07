package com.fuicuiedu.idedemo.interview_banner;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private List<ImageView> images;//存放图片view的集合
    private List<View> dots;//存放小点点的集合
    private TextView title;//图片的标题

    private String[] titles = new String[]{
            "向来情深奈何缘浅",
            "AB君",
            "再见青春",
            "程序的奴隶",
            "红枣稀饭"
    };

    private int[] imageIds = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };
    private ScheduledExecutorService scheduledExecutorService;//线程池，用来定时轮播
    private int currentItem;
    private int oldPosition;//记录上一个点的位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.vp);

        //显示图片的集合
        images = new ArrayList<>();
        for (int i = 0;i < imageIds.length;i++){
            //初始化imagerView
            ImageView imageView = new ImageView(this);
            //给imageView设置资源
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }

        //显示小点点的集合
        dots = new ArrayList<>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));

        title = (TextView) findViewById(R.id.tv);//显示图片标题
        title.setText(titles[0]);//刚进来显示第一个标题

        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);

    }


    private class ViewPagerAdapter extends PagerAdapter{
        //是获取当前窗体界面数量
        @Override
        public int getCount() {
            return images.size();
        }

        //用于判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //return一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放入当前的Viewpager中
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));
            return images.get(position);
        }

        //是从viewgroup中移除当前View
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }
    }
}
